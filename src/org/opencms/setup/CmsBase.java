/*
 * File   : $Source: /alkacon/cvs/opencms/src/org/opencms/setup/Attic/CmsBase.java,v $
 * Date   : $Date: 2004/02/11 16:12:05 $
 * Version: $Revision: 1.2 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (C) 2002 - 2004 Alkacon Software (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.opencms.setup;

import org.opencms.main.OpenCms;

import java.io.File;

/**
 * OpenCms Base class for static access to system wide properties
 * and helper functions, e.g. OpenCms logging oder OpenCms base path.<p>
 *
 * @author  Alexander Kandzior (a.kandzior@alkacon.com)
 * @version $Revision: 1.2 $ 
 */
public final class CmsBase extends Object {
    
    /**
     * Default constructor. Nobody is allowed to create an instance of this class!
     */
    private CmsBase() {
        super();
    }    

    /**
     * Get the OpenCms web-base path.<p> 
     * 
     * @return the current web base path
     */
    public static String getWebBasePath() {
        File basePath = new File(OpenCms.getSystemInfo().getBasePath());
        String webBasePath = basePath.getParent();
        if (!webBasePath.endsWith(File.separatorChar+"")) {
            webBasePath += File.separatorChar;
        }
        return webBasePath;
    }

    /** 
     * Get the OpenCms WebApplicationName.<p> 
     * 
     * @return the web application name
     */
    public static String getWebAppName() {
        File basePath = new File(OpenCms.getSystemInfo().getBasePath());
        String webAppName = basePath.getParentFile().getName();
        return webAppName;
    }

    /**
     * Get the absolute web path for a given path.<p>
     * 
     * @param s the path
     * @return the absolute path
     */
    public static String getAbsoluteWebPath(String s) {
        if (s == null) {
            return null;
        }

        File f = new File(s);
        if (! f.isAbsolute()) {
            if (OpenCms.getSystemInfo().getBasePath() == null) {
                return null;
            } else {
                return getWebBasePath() + s;
            }
        } else {
            return s;
        }
    }

    private static final char m_replaceSep = (File.separatorChar == '/')?'\\':'/';

    /**
     * Gets the absolute path for a given path.<p>
     * 
     * @param s the path
     * @return the absolute path
     */
    public static String getAbsolutePath(String s) {
        if (s == null) {
            return null;
        }         

        File f = new File(s);
        if (! f.isAbsolute()) {
            if (OpenCms.getSystemInfo().getBasePath() == null) {
                return null;
            } else {
                return (OpenCms.getSystemInfo().getBasePath() + s).replace(m_replaceSep, File.separatorChar);
            }
        } else {
            return s.replace(m_replaceSep, File.separatorChar);
        }
    }

    /**
     * Gets the path to the properties file.<p>
     * 
     * @param absolute flag to indicate if absolute path is wanted
     * @return the relative or absolute path to opencms.properties
     */
    public static String getPropertiesPath(boolean absolute) {
        String result = "config/opencms.properties".replace('/', File.separatorChar);
        if (absolute) {
            if (OpenCms.getSystemInfo().getBasePath() == null) {
                result = null;
            } else {
                result = OpenCms.getSystemInfo().getBasePath() + result;
            }
        }
        return result;
    }
}
