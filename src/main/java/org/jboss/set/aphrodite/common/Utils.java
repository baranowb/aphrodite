/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2015, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.set.aphrodite.common;

import org.apache.commons.logging.Log;

/**
 * @author Ryan Emerson
 */
public class Utils {

    public static void logWarnMessage(Log log, String message) {
        if (log.isWarnEnabled())
            log.warn(message);
    }

    public static void logException(Log log, Exception e) {
        logException(log, null, e);
    }

    public static void logException(Log log, String message, Exception e) {
        if (log.isErrorEnabled()) {
            if (message == null)
                log.error(e);
            else
                log.error(message, e);
        }
    }

    public static Exception logExceptionAndGet(Log log, Exception e) {
        logException(log, null, e);
        return e;
    }

    public static Exception logExceptionAndGet(Log log, String message, Exception e) {
        logException(log, message, e);
        return e;
    }
}