package com.bergerkiller.bukkit.common.nbt;

import com.bergerkiller.bukkit.common.collections.CollectionBasics;
import com.bergerkiller.bukkit.common.conversion.Conversion;
import com.bergerkiller.bukkit.common.conversion.DuplexConversion;
import com.bergerkiller.bukkit.common.utils.CommonUtil;
import com.bergerkiller.bukkit.common.utils.LogicUtil;
import com.bergerkiller.mountiplex.conversion.util.ConvertingIterator;
import com.bergerkiller.mountiplex.conversion.util.ConvertingListIterator;
import com.bergerkiller.reflection.net.minecraft.server.NMSNBT;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

/**
 * <b>Data</b> represents actual data stored by the tag. This can be:<br>
 * <u>List<CommonTag>, Map<String, CommonTag>, byte, short, int, long, float,
 * double, byte[], int[], String</u><br><br>
 * <p/>
 * Data can be retrieved using <b>getValue()</b>, set using <b>setValue()</b>
 * and added using <b>addValue()</b><br>
 * The value setting and adding methods also accept tags, both NBTBase and
 * CommonTag<br>
 * The <b>set/get/add</b> methods operate on {@link CommonTag} instances only
 * and can generally be ignored
 */
@SuppressWarnings("unchecked")
public class CommonTagList extends CommonTag implements List<CommonTag> {

    public CommonTagList() {
        this(new ArrayList<CommonTag>());
    }

    public CommonTagList(Object... values) {
        this(new ArrayList<>(Arrays.asList(values)));
    }

    public CommonTagList(List<?> data) {
        this((Object) data);
    }

    public CommonTagList(Object value) {
        super(value);
    }

    private void updateListType(Object elementHandle) {
        NMSNBT.List.type.set(getRawHandle(), NMSNBT.getTypeId(elementHandle));
    }

    @Override
    public List<CommonTag> getData() {
        return (List<CommonTag>) super.getData();
    }

    @Override
    protected List<Object> getRawData() {
        return (List<Object>) super.getRawData();
    }

    @Override
    public CommonTagList clone() {
        return (CommonTagList) super.clone();
    }

    @Override
    public int size() {
        return NMSNBT.List.size.invoke(getRawHandle());
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        getRawData().clear();
        updateListType(null);
    }

    private Object prepElement(Object element) {
        if (element == null) {
            throw new IllegalArgumentException("Can not store null elements");
        } else {
            try {
                Object handle = commonToNbt(element);
                if (!NMSNBT.Base.T.isInstance(handle)) {
                    handle = NMSNBT.createHandle(handle);
                }
                if (handle != null) {
                    updateListType(handle);
                    return handle;
                }
            } catch (Exception ex) {
            }
            throw new IllegalArgumentException("Unable to store an element of type " + element.getClass().getName());
        }
    }

    /**
     * Gets the value contained at a given index. Possible returned types:<br>
     * <u>List<CommonTag>, Map<String, CommonTag>, byte, short, int, long,
     * float, double, byte[], int[], String</u>
     *
     * @param index of the element value to get
     * @return element value
     */
    public Object getValue(int index) {
        return nbtToCommon(NMSNBT.getData(NMSNBT.List.get.invoke(getRawHandle(), index)), false);
    }

    /**
     * Gets the value contained at a given index. Possible returned types:<br>
     * <u>List<CommonTag>, Map<String, CommonTag>, byte, short, int, long,
     * float, double, byte[], int[], String</u>
     *
     * @param index of the element value to get
     * @param type of element value to get
     * @return element value, or null if type conversion fails
     */
    public <T> T getValue(int index, Class<T> type) {
        return Conversion.convert(getValue(index), type, null);
    }

    /**
     * Gets the value contained at a given index. Possible returned types:<br>
     * <u>List<CommonTag>, Map<String, CommonTag>, byte, short, int, long,
     * float, double, byte[], int[], String</u>
     *
     * @param index of the element value to get
     * @param def value to return if type conversion fails (can not be null)
     * @return element value, or null if type conversion fails
     */
    public <T> T getValue(int index, T def) {
        return Conversion.convert(getValue(index), def);
    }

    /**
     * Gets the value contained at a given index. Possible returned types:<br>
     * <u>List<CommonTag>, Map<String, CommonTag>, byte, short, int, long,
     * float, double, byte[], int[], String</u>
     *
     * @param index of the element value to get
     * @param type of element value to get
     * @param def value to return if type conversion fails
     * @return element value, or def if type conversion fails
     */
    public <T> T getValue(int index, Class<T> type, T def) {
        return Conversion.convert(getValue(index), type, def);
    }

    /**
     * Sets a single tag. Supported element types:<br>
     * <u>NBTBase, CommonTag, byte, short, int, long, float, double, byte[],
     * int[], String</u>
     *
     * @param index to set at
     * @param element to set to
     */
    public void setValue(int index, Object element) {
        getRawData().set(index, prepElement(element));
    }

    /**
     * Adds a single tag. Supported element types:<br>
     * <u>NBTBase, CommonTag, byte, short, int, long, float, double, byte[],
     * int[], String</u>
     *
     * @param index to add at
     * @param element to add
     */
    public void addValue(int index, Object element) {
        getRawData().add(index, prepElement(element));
    }

    /**
     * Adds a single tag. Supported element types:<br>
     * <u>NBTBase, CommonTag, byte, short, int, long, float, double, byte[],
     * int[], String</u>
     *
     * @param element to add
     */
    public void addValue(Object element) {
        getRawData().add(prepElement(element));
    }

    @Override
    public CommonTag remove(int index) {
        return CommonTag.create(getRawData().remove(index));
    }

    @Override
    public CommonTag set(int index, CommonTag element) {
        final CommonTag prev = get(index);
        setValue(index, element);
        return prev;
    }

    @Override
    public void add(int index, CommonTag element) {
        addValue(index, element);
    }

    @Override
    public boolean add(CommonTag element) {
        addValue(element);
        return true;
    }

    @Override
    public CommonTag get(int index) {
        return CommonTag.create(NMSNBT.List.get.invoke(getRawHandle(), index));
    }

    /**
     * Gets all values contained in this CommonTagList and casts it to the type
     * specified<br>
     * Lists, Sets and arrays (also primitive) are supported for types
     *
     * @param type to cast to
     * @return all data contained
     */
    public <T> T getAllValues(Class<T> type) {
        T values = Conversion.convert(this, type, null);
        if (values == null) {
            throw new IllegalArgumentException("Unsupported type: " + type.getName());
        } else {
            return values;
        }
    }

    /**
     * Clears all values contained and sets the contents to the data specified.
     * Collections, arrays (also primitive) and maps are supported for data
     * types. Other data types are added as a single element, and may cause an
     * exception if not supported.<br><br>
     * <p/>
     * The individual elements can be collections or arrays as well, which
     * allows adding multiple arrays at once.
     *
     * @param values to set to
     */
    public <T> void setAllValues(T... values) {
        clear();
        addAllValues(values);
    }

    /**
     * Adds all the values to this list. Collections, arrays (also primitive)
     * and maps are supported for data types. Other data types are added as a
     * single element, and may cause an exception if not supported.<br><br>
     * <p/>
     * The individual elements can be collections or arrays as well, which
     * allows adding multiple arrays at once.
     *
     * @param values to set to
     */
    public <T> void addAllValues(T... values) {
        for (Object data : values) {
            if (data == null) {
                continue;
            }
            Class<?> dataType = data.getClass();
            if (data instanceof Collection) {
                for (Object o : (Collection<?>) data) {
                    addValue(o);
                }
            } else if (data instanceof Map) {
                for (Entry<Object, Object> entry : ((Map<Object, Object>) data).entrySet()) {
                    addValue(entry.getValue());
                }
            } else if (dataType.isArray()) {
                if (dataType.isPrimitive()) {
                    int len = Array.getLength(data);
                    for (int i = 0; i < len; i++) {
                        addValue(Array.get(data, i));
                    }
                } else {
                    for (Object elem : (Object[]) data) {
                        addValue(elem);
                    }
                }
            } else {
                addValue(data);
            }
        }
    }

    @Override
    public int indexOf(Object o) {
        return getRawData().indexOf(commonToNbt(o));
    }

    @Override
    public int lastIndexOf(Object o) {
        return getRawData().lastIndexOf(commonToNbt(o));
    }

    @Override
    public boolean contains(Object o) {
        for (Object elem : this) {
            if (elem != null && elem.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] values = new Object[size()];
        Iterator<CommonTag> iter = iterator();
        for (int i = 0; i < values.length; i++) {
            values[i] = iter.next();
        }
        return values;
    }

    @Override
    public <K> K[] toArray(K[] array) {
        if (this.size() > array.length) {
            array = (K[]) LogicUtil.createArray(array.getClass().getComponentType(), this.size());
        }
        Iterator<CommonTag> iter = this.iterator();
        for (int i = 0; iter.hasNext(); i++) {
            array[i] = (K) iter.next();
        }
        return array;
    }

    @Override
    public boolean remove(Object o) {
        return getRawData().remove(commonToNbt(o));
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        List<?> raw = getRawData();
        for (Object elem : c) {
            if (!raw.contains(commonToNbt(elem))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends CommonTag> c) {
        return getRawData().addAll((Collection<Object>) commonToNbt(c));
    }

    @Override
    public boolean addAll(int index, Collection<? extends CommonTag> c) {
        return getRawData().addAll(index, (Collection<Object>) commonToNbt(c));
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        List<Object> raw = getRawData();
        boolean changed = false;
        for (Object o : c) {
            changed |= raw.remove(commonToNbt(o));
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return CollectionBasics.retainAll(this, c);
    }

    @Override
    public Iterator<CommonTag> iterator() {
        return new ConvertingIterator<>(getRawData().iterator(), Conversion.toCommonTag);
    }

    @Override
    public ListIterator<CommonTag> listIterator() {
        return new ConvertingListIterator<>(getRawData().listIterator(), DuplexConversion.commonTag);
    }

    @Override
    public ListIterator<CommonTag> listIterator(int index) {
        return new ConvertingListIterator<>(getRawData().listIterator(index), DuplexConversion.commonTag);
    }

    @Override
    public List<CommonTag> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("No sublist can be made from tag data");
    }
    
    /**
     * Deserializes and reads a list tag from a stream. The input data should be uncompressed.
     * 
     * @param in Stream to read from
     * @return read list tag
     * @throws IOException
     */
    public static CommonTagList readFromStream(InputStream in) throws IOException {
    	CommonTag tag = CommonTag.readFromStream(in);
    	if (!(tag instanceof CommonTagList)) {
    		throw new IOException("Tag read is not a list!");
    	}
    	return (CommonTagList) tag;
    }

    /**
     * Creates a CommonTagList from the handle specified<br>
     * If the handle is null or not a list, null is returned
     *
     * @param handle to create a list wrapper class for
     * @return Wrapper class suitable for the given handle
     */
    public static CommonTagList create(Object handle) {
        return CommonUtil.tryCast(CommonTag.create(handle), CommonTagList.class);
    }
}
