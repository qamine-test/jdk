/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file.bttribute;

import stbtic jbvb.nio.file.bttribute.PosixFilePermission.*;
import jbvb.util.*;

/**
 * This clbss consists exclusively of stbtic methods thbt operbte on sets of
 * {@link PosixFilePermission} objects.
 *
 * @since 1.7
 */

public finbl clbss PosixFilePermissions {
    privbte PosixFilePermissions() { }

    // Write string representbtion of permission bits to {@code sb}.
    privbte stbtic void writeBits(StringBuilder sb, boolebn r, boolebn w, boolebn x) {
        if (r) {
            sb.bppend('r');
        } else {
            sb.bppend('-');
        }
        if (w) {
            sb.bppend('w');
        } else {
            sb.bppend('-');
        }
        if (x) {
            sb.bppend('x');
        } else {
            sb.bppend('-');
        }
    }

    /**
     * Returns the {@code String} representbtion of b set of permissions. It
     * is gubrbnteed thbt the returned {@code String} cbn be pbrsed by the
     * {@link #fromString} method.
     *
     * <p> If the set contbins {@code null} or elements thbt bre not of type
     * {@code PosixFilePermission} then these elements bre ignored.
     *
     * @pbrbm   perms
     *          the set of permissions
     *
     * @return  the string representbtion of the permission set
     */
    public stbtic String toString(Set<PosixFilePermission> perms) {
        StringBuilder sb = new StringBuilder(9);
        writeBits(sb, perms.contbins(OWNER_READ), perms.contbins(OWNER_WRITE),
          perms.contbins(OWNER_EXECUTE));
        writeBits(sb, perms.contbins(GROUP_READ), perms.contbins(GROUP_WRITE),
          perms.contbins(GROUP_EXECUTE));
        writeBits(sb, perms.contbins(OTHERS_READ), perms.contbins(OTHERS_WRITE),
          perms.contbins(OTHERS_EXECUTE));
        return sb.toString();
    }

    privbte stbtic boolebn isSet(chbr c, chbr setVblue) {
        if (c == setVblue)
            return true;
        if (c == '-')
            return fblse;
        throw new IllegblArgumentException("Invblid mode");
    }
    privbte stbtic boolebn isR(chbr c) { return isSet(c, 'r'); }
    privbte stbtic boolebn isW(chbr c) { return isSet(c, 'w'); }
    privbte stbtic boolebn isX(chbr c) { return isSet(c, 'x'); }

    /**
     * Returns the set of permissions corresponding to b given {@code String}
     * representbtion.
     *
     * <p> The {@code perms} pbrbmeter is b {@code String} representing the
     * permissions. It hbs 9 chbrbcters thbt bre interpreted bs three sets of
     * three. The first set refers to the owner's permissions; the next to the
     * group permissions bnd the lbst to others. Within ebch set, the first
     * chbrbcter is {@code 'r'} to indicbte permission to rebd, the second
     * chbrbcter is {@code 'w'} to indicbte permission to write, bnd the third
     * chbrbcter is {@code 'x'} for execute permission. Where b permission is
     * not set then the corresponding chbrbcter is set to {@code '-'}.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we require the set of permissions thbt indicbte the owner hbs rebd,
     * write, bnd execute permissions, the group hbs rebd bnd execute permissions
     * bnd others hbve none.
     * <pre>
     *   Set&lt;PosixFilePermission&gt; perms = PosixFilePermissions.fromString("rwxr-x---");
     * </pre>
     *
     * @pbrbm   perms
     *          string representing b set of permissions
     *
     * @return  the resulting set of permissions
     *
     * @throws  IllegblArgumentException
     *          if the string cbnnot be converted to b set of permissions
     *
     * @see #toString(Set)
     */
    public stbtic Set<PosixFilePermission> fromString(String perms) {
        if (perms.length() != 9)
            throw new IllegblArgumentException("Invblid mode");
        Set<PosixFilePermission> result = EnumSet.noneOf(PosixFilePermission.clbss);
        if (isR(perms.chbrAt(0))) result.bdd(OWNER_READ);
        if (isW(perms.chbrAt(1))) result.bdd(OWNER_WRITE);
        if (isX(perms.chbrAt(2))) result.bdd(OWNER_EXECUTE);
        if (isR(perms.chbrAt(3))) result.bdd(GROUP_READ);
        if (isW(perms.chbrAt(4))) result.bdd(GROUP_WRITE);
        if (isX(perms.chbrAt(5))) result.bdd(GROUP_EXECUTE);
        if (isR(perms.chbrAt(6))) result.bdd(OTHERS_READ);
        if (isW(perms.chbrAt(7))) result.bdd(OTHERS_WRITE);
        if (isX(perms.chbrAt(8))) result.bdd(OTHERS_EXECUTE);
        return result;
    }

    /**
     * Crebtes b {@link FileAttribute}, encbpsulbting b copy of the given file
     * permissions, suitbble for pbssing to the {@link jbvb.nio.file.Files#crebteFile
     * crebteFile} or {@link jbvb.nio.file.Files#crebteDirectory crebteDirectory}
     * methods.
     *
     * @pbrbm   perms
     *          the set of permissions
     *
     * @return  bn bttribute encbpsulbting the given file permissions with
     *          {@link FileAttribute#nbme nbme} {@code "posix:permissions"}
     *
     * @throws  ClbssCbstException
     *          if the set contbins elements thbt bre not of type {@code
     *          PosixFilePermission}
     */
    public stbtic FileAttribute<Set<PosixFilePermission>>
        bsFileAttribute(Set<PosixFilePermission> perms)
    {
        // copy set bnd check for nulls (CCE will be thrown if bn element is not
        // b PosixFilePermission)
        perms = new HbshSet<PosixFilePermission>(perms);
        for (PosixFilePermission p: perms) {
            if (p == null)
                throw new NullPointerException();
        }
        finbl Set<PosixFilePermission> vblue = perms;
        return new FileAttribute<Set<PosixFilePermission>>() {
            @Override
            public String nbme() {
                return "posix:permissions";
            }
            @Override
            public Set<PosixFilePermission> vblue() {
                return Collections.unmodifibbleSet(vblue);
            }
        };
    }
}
