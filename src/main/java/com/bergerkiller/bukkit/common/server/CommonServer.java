package com.bergerkiller.bukkit.common.server;

import com.bergerkiller.bukkit.common.internal.CommonPlugin;
import java.io.File;
import java.util.Collection;

public interface CommonServer {

    /**
     * Tries to initialize the server
     *
     * @return True if initializing was successful, False if not
     */
    public boolean init();

    /**
     * Called after the {@link #init()} method successfully detected the server
     * and initialized the server. In here processing that depends on the
     * CommonServer instance being fully initialized can be continued.
     */
    public void postInit();

    /**
     * Prepares this server for enabling of BKCommonLIb
     *
     * @param plugin instance
     */
    public void enable(CommonPlugin plugin);

    /**
     * Prepares this server for disabling of BKCommonLib
     *
     * @param plugin instance
     */
    public void disable(CommonPlugin plugin);

    /**
     * Combines all server information available into a single detailed description.
     * This is the message displayed when the plugin is enabled.
     * 
     * @return server details String
     */
    public String getServerDetails();

    /**
     * Gets the versioning information of the server
     *
     * @return server versioning description
     */
    public String getServerVersion();

    /**
     * Gets the full name of the server
     *
     * @return server name
     */
    public String getServerName();

    /**
     * Gets a more detailed description of the server, excluding the server
     * version
     *
     * @return server description
     */
    public String getServerDescription();

    /**
     * Gets the real Class name for the given Path, allowing Class path
     * translations to occur
     *
     * @param path to the Class to fix
     * @return the real Class path
     */
    public String getClassName(String path);

    /**
     * Obtains the real method name for a given method, allowing name
     * translations to occur
     *
     * @param type of Class the method is in
     * @param methodName of the method to fix
     * @param params of the method
     * @return the (translated) method name
     */
    public String getMethodName(Class<?> type, String methodName, Class<?>... params);

    /**
     * Obtains the real field name for a given field, allowing name translations
     * to occur
     *
     * @param type of Class the field is in
     * @param fieldName of the field to fix
     * @return the (translated) field name
     */
    public String getFieldName(Class<?> type, String fieldName);

    /**
     * Checks whether BKCommonLib is compatible with this server
     *
     * @return True if compatible, False if not
     */
    public boolean isCompatible();

    /**
     * Gets the version of Minecraft the server supports
     *
     * @return Minecraft version
     */
    public String getMinecraftVersion();

    /**
     * Gets the major Minecraft version, removing any -pre postfixes from the version.
     * 
     * @return major minecraft version
     */
    public String getMinecraftVersionMajor();

    /**
     * Gets the pre-release version name from the Minecraft version. Is null if this
     * is not a pre-release version.
     * 
     * @return pre release minecraft version
     */
    public String getMinecraftVersionPre();

    /**
     * Checks if the Minecraft version matches a version condition
     * 
     * @param operand to evaluate with, for example ">=" and "!="
     * @param version the operand is applied to (right side)
     * @return True if the version matches, False if not
     */
    public boolean evaluateMCVersion(String operand, String version);

    /**
     * Gets the File Location where the regions of a world are contained
     *
     * @param worldName to get the regions folder for
     * @return Region folder
     */
    public File getWorldRegionFolder(String worldName);

    /**
     * Gets the File Location where a world is contained
     *
     * @return World folder
     */
    public File getWorldFolder(String worldName);

    /**
     * Gets a Collection of all worlds that can be loaded without creating it
     *
     * @return Loadable world names
     */
    public Collection<String> getLoadableWorlds();

    /**
     * Checks whether the World name specified contains a folder and can be
     * loaded
     *
     * @param worldName to check
     * @return True if the world can be loaded, False if not
     */
    public boolean isLoadableWorld(String worldName);
}
