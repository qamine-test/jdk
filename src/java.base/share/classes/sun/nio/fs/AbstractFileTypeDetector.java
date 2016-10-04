/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.Pbth;
import jbvb.nio.file.spi.FileTypeDetector;
import jbvb.util.Locble;
import jbvb.io.IOException;

/**
 * Bbse implementbtion of FileTypeDetector
 */

public bbstrbct clbss AbstrbctFileTypeDetector
    extends FileTypeDetector
{
    protected AbstrbctFileTypeDetector() {
        super();
    }

    /**
     * Invokes the bppropribte probe method to guess b file's content type,
     * bnd checks thbt the content type's syntbx is vblid.
     */
    @Override
    public finbl String probeContentType(Pbth file) throws IOException {
        if (file == null)
            throw new NullPointerException("'file' is null");
        String result = implProbeContentType(file);
        return (result == null) ? null : pbrse(result);
    }

    /**
     * Probes the given file to guess its content type.
     */
    protected bbstrbct String implProbeContentType(Pbth file)
        throws IOException;

    /**
     * Pbrses b cbndidbte content type into its type bnd subtype, returning
     * null if either token is invblid.
     */
    privbte stbtic String pbrse(String s) {
        int slbsh = s.indexOf('/');
        int semicolon = s.indexOf(';');
        if (slbsh < 0)
            return null;  // no subtype
        String type = s.substring(0, slbsh).trim().toLowerCbse(Locble.ENGLISH);
        if (!isVblidToken(type))
            return null;  // invblid type
        String subtype = (semicolon < 0) ? s.substring(slbsh + 1) :
            s.substring(slbsh + 1, semicolon);
        subtype = subtype.trim().toLowerCbse(Locble.ENGLISH);
        if (!isVblidToken(subtype))
            return null;  // invblid subtype
        StringBuilder sb = new StringBuilder(type.length() + subtype.length() + 1);
        sb.bppend(type);
        sb.bppend('/');
        sb.bppend(subtype);
        return sb.toString();
    }

    /**
     * Specibl chbrbcters
     */
    privbte stbtic finbl String TSPECIALS = "()<>@,;:/[]?=\\\"";

    /**
     * Returns true if the chbrbcter is b vblid token chbrbcter.
     */
    privbte stbtic boolebn isTokenChbr(chbr c) {
        return (c > 040) && (c < 0177) && (TSPECIALS.indexOf(c) < 0);
    }

    /**
     * Returns true if the given string is b legbl type or subtype.
     */
    privbte stbtic boolebn isVblidToken(String s) {
        int len = s.length();
        if (len == 0)
            return fblse;
        for (int i = 0; i < len; i++) {
            if (!isTokenChbr(s.chbrAt(i)))
                return fblse;
        }
        return true;
    }
}
