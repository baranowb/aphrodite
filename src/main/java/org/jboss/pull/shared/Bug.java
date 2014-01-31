/*
 * JBoss, Home of Professional Open Source.
 * Copyright (c) 2013, Red Hat, Inc., and individual contributors
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
package org.jboss.pull.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bug implements Serializable{

    // Bug Status
    public enum Status {
        NEW,
        ASSIGNED,
        POST,
        MODIFIED,
        ON_DEV,
        ON_QA,
        VERIFIED,
        RELEASE_PENDING,
        CLOSED
    }

    private static final long serialVersionUID = 6967220126171894474L;

    private Map<String, Object> bugMap;

    //includes attributes for Bug.get execution
    public static final Object[] include_fields = {"id", "assigned_to", "status", "flags", "blocks"};

    private int id;
    private String assignedTo;
    private Status status;
    private List<Flag> flags;
    private Set<Integer> blocks;

    public Bug(Map<String, Object> bugMap) {
        this.bugMap = bugMap;
        initBug();
    }

    private void initBug() {
        id = (Integer) bugMap.get("id");
        assignedTo = (String) bugMap.get("assigned_to");
        status = Status.valueOf((String)bugMap.get("status"));

        flags = new ArrayList<Flag>();
        Object[] flagObjs = (Object[]) bugMap.get("flags");
        for(Object obj : flagObjs){
            @SuppressWarnings("unchecked")
            Map<String, Object> flag = (Map<String, Object>)obj;
            String name = (String) flag.get("name");
            String setter = (String) flag.get("setter");
            String s = (String) flag.get("status");
            Flag.Status status;

            if (s.equals(" ")) {
                status = Flag.Status.UNSET;
            } else if (s.equals("?")) {
                status = Flag.Status.UNKNOWN;
            } else if (s.equals("+")) {
                status = Flag.Status.POSITIVE;
            } else if (s.equals("-")) {
                status = Flag.Status.NEGATIVE;
            } else {
                throw new IllegalStateException("Unknown flag state");
            }

            flags.add(new Flag(name, setter, status));
        }

        Object[] blockObjs = (Object[]) bugMap.get("blocks");
        blocks = new HashSet<Integer>(blockObjs.length);
        for (Object obj : blockObjs) {
            blocks.add((Integer) obj);
        }
    }

    public Map<String, Object> getBugMap() {
        return bugMap;
    }

    public void setBugMap(Map<String, Object> bugMap) {
        this.bugMap = bugMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Flag> getFlags() {
        return flags;
    }

    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }

    public Set<Integer> getBlocks() {
        return blocks;
    }

    public void setBlocks(Set<Integer> blocks) {
        this.blocks = blocks;
    }

}
