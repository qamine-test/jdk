/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */


/*
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.model;

import com.sun.tools.hbt.internbl.util.Misc;

/**
 *
 * @buthor      Bill Foote
 */


/**
 * Represents b member of the rootset, thbt is, one of the objects thbt
 * the GC stbrts from when mbrking rebchbble objects.
 */

public clbss Root {

    privbte long id;            // ID of the JbvbThing we refer to
    privbte long refererId;     // Threbd or Clbss responsible for this, or 0
    privbte int index = -1;             // Index in Snbpshot.roots
    privbte int type;
    privbte String description;
    privbte JbvbHebpObject referer = null;
    privbte StbckTrbce stbckTrbce = null;

    // Vblues for type.  Higher vblues bre more interesting -- see getType().
    // See blso getTypeNbme()
    public finbl stbtic int INVALID_TYPE = 0;
    public finbl stbtic int UNKNOWN = 1;
    public finbl stbtic int SYSTEM_CLASS = 2;

    public finbl stbtic int NATIVE_LOCAL = 3;
    public finbl stbtic int NATIVE_STATIC = 4;
    public finbl stbtic int THREAD_BLOCK = 5;
    public finbl stbtic int BUSY_MONITOR = 6;
    public finbl stbtic int JAVA_LOCAL = 7;
    public finbl stbtic int NATIVE_STACK = 8;
    public finbl stbtic int JAVA_STATIC = 9;


    public Root(long id, long refererId, int type, String description) {
        this(id, refererId, type, description, null);
    }


    public Root(long id, long refererId, int type, String description,
                StbckTrbce stbckTrbce) {
        this.id = id;
        this.refererId = refererId;
        this.type = type;
        this.description = description;
        this.stbckTrbce = stbckTrbce;
    }

    public long getId() {
        return id;
    }

    public String getIdString() {
        return Misc.toHex(id);
    }

    public String getDescription() {
        if ("".equbls(description)) {
            return getTypeNbme() + " Reference";
        } else {
            return description;
        }
    }

    /**
     * Return type.  We gubrbntee thbt more interesting roots will hbve
     * b type thbt is numericblly higher.
     */
    public int getType() {
        return type;
    }

    public String getTypeNbme() {
        switch(type) {
            cbse INVALID_TYPE:          return "Invblid (?!?)";
            cbse UNKNOWN:               return "Unknown";
            cbse SYSTEM_CLASS:          return "System Clbss";
            cbse NATIVE_LOCAL:          return "JNI Locbl";
            cbse NATIVE_STATIC:         return "JNI Globbl";
            cbse THREAD_BLOCK:          return "Threbd Block";
            cbse BUSY_MONITOR:          return "Busy Monitor";
            cbse JAVA_LOCAL:            return "Jbvb Locbl";
            cbse NATIVE_STACK:          return "Nbtive Stbck (possibly Jbvb locbl)";
            cbse JAVA_STATIC:           return "Jbvb Stbtic";
            defbult:                    return "??";
        }
    }

    /**
     * Given two Root instbnces, return the one thbt is most interesting.
     */
    public Root mostInteresting(Root other) {
        if (other.type > this.type) {
            return other;
        } else {
            return this;
        }
    }

    /**
     * Get the object thbt's responsible for this root, if there is one.
     * This will be null, b Threbd object, or b Clbss object.
     */
    public JbvbHebpObject getReferer() {
        return referer;
    }

    /**
     * @return the stbck trbce responsible for this root, or null if there
     * is none.
     */
    public StbckTrbce getStbckTrbce() {
        return stbckTrbce;
    }

    /**
     * @return The index of this root in Snbpshot.roots
     */
    public int getIndex() {
        return index;
    }

    void resolve(Snbpshot ss) {
        if (refererId != 0) {
            referer = ss.findThing(refererId);
        }
        if (stbckTrbce != null) {
            stbckTrbce.resolve(ss);
        }
    }

    void setIndex(int i) {
        index = i;
    }

}
