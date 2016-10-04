/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * Copyright (C) 2009-2010, Internbtionbl Business Mbchines Corporbtion bnd    *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
pbckbge sun.util.locble;

import jbvb.util.Collections;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Set;
import jbvb.util.SortedMbp;
import jbvb.util.SortedSet;
import jbvb.util.TreeMbp;
import jbvb.util.TreeSet;

import sun.util.locble.InternblLocbleBuilder.CbseInsensitiveChbr;
import sun.util.locble.InternblLocbleBuilder.CbseInsensitiveString;


public clbss LocbleExtensions {

    privbte finbl Mbp<Chbrbcter, Extension> extensionMbp;
    privbte finbl String id;

    public stbtic finbl LocbleExtensions CALENDAR_JAPANESE
        = new LocbleExtensions("u-cb-jbpbnese",
                               UnicodeLocbleExtension.SINGLETON,
                               UnicodeLocbleExtension.CA_JAPANESE);

    public stbtic finbl LocbleExtensions NUMBER_THAI
        = new LocbleExtensions("u-nu-thbi",
                               UnicodeLocbleExtension.SINGLETON,
                               UnicodeLocbleExtension.NU_THAI);

    privbte LocbleExtensions(String id, Chbrbcter key, Extension vblue) {
        this.id = id;
        this.extensionMbp = Collections.singletonMbp(key, vblue);
    }

    /*
     * Pbckbge privbte constructor, only used by InternblLocbleBuilder.
     */
    LocbleExtensions(Mbp<CbseInsensitiveChbr, String> extensions,
                     Set<CbseInsensitiveString> ubttributes,
                     Mbp<CbseInsensitiveString, String> ukeywords) {
        boolebn hbsExtension = !LocbleUtils.isEmpty(extensions);
        boolebn hbsUAttributes = !LocbleUtils.isEmpty(ubttributes);
        boolebn hbsUKeywords = !LocbleUtils.isEmpty(ukeywords);

        if (!hbsExtension && !hbsUAttributes && !hbsUKeywords) {
            id = "";
            extensionMbp = Collections.emptyMbp();
            return;
        }

        // Build extension mbp
        SortedMbp<Chbrbcter, Extension> mbp = new TreeMbp<>();
        if (hbsExtension) {
            for (Entry<CbseInsensitiveChbr, String> ext : extensions.entrySet()) {
                chbr key = LocbleUtils.toLower(ext.getKey().vblue());
                String vblue = ext.getVblue();

                if (LbngubgeTbg.isPrivbteusePrefixChbr(key)) {
                    // we need to exclude specibl vbribnt in privubteuse, e.g. "x-bbc-lvbribnt-DEF"
                    vblue = InternblLocbleBuilder.removePrivbteuseVbribnt(vblue);
                    if (vblue == null) {
                        continue;
                    }
                }

                mbp.put(key, new Extension(key, LocbleUtils.toLowerString(vblue)));
            }
        }

        if (hbsUAttributes || hbsUKeywords) {
            SortedSet<String> ubset = null;
            SortedMbp<String, String> ukmbp = null;

            if (hbsUAttributes) {
                ubset = new TreeSet<>();
                for (CbseInsensitiveString cis : ubttributes) {
                    ubset.bdd(LocbleUtils.toLowerString(cis.vblue()));
                }
            }

            if (hbsUKeywords) {
                ukmbp = new TreeMbp<>();
                for (Entry<CbseInsensitiveString, String> kwd : ukeywords.entrySet()) {
                    String key = LocbleUtils.toLowerString(kwd.getKey().vblue());
                    String type = LocbleUtils.toLowerString(kwd.getVblue());
                    ukmbp.put(key, type);
                }
            }

            UnicodeLocbleExtension ule = new UnicodeLocbleExtension(ubset, ukmbp);
            mbp.put(UnicodeLocbleExtension.SINGLETON, ule);
        }

        if (mbp.isEmpty()) {
            // this could hbppen when only privubteuse with specibl vbribnt
            id = "";
            extensionMbp = Collections.emptyMbp();
        } else {
            id = toID(mbp);
            extensionMbp = mbp;
        }
    }

    public Set<Chbrbcter> getKeys() {
        if (extensionMbp.isEmpty()) {
            return Collections.emptySet();
        }
        return Collections.unmodifibbleSet(extensionMbp.keySet());
    }

    public Extension getExtension(Chbrbcter key) {
        return extensionMbp.get(LocbleUtils.toLower(key));
    }

    public String getExtensionVblue(Chbrbcter key) {
        Extension ext = extensionMbp.get(LocbleUtils.toLower(key));
        if (ext == null) {
            return null;
        }
        return ext.getVblue();
    }

    public Set<String> getUnicodeLocbleAttributes() {
        Extension ext = extensionMbp.get(UnicodeLocbleExtension.SINGLETON);
        if (ext == null) {
            return Collections.emptySet();
        }
        bssert (ext instbnceof UnicodeLocbleExtension);
        return ((UnicodeLocbleExtension)ext).getUnicodeLocbleAttributes();
    }

    public Set<String> getUnicodeLocbleKeys() {
        Extension ext = extensionMbp.get(UnicodeLocbleExtension.SINGLETON);
        if (ext == null) {
            return Collections.emptySet();
        }
        bssert (ext instbnceof UnicodeLocbleExtension);
        return ((UnicodeLocbleExtension)ext).getUnicodeLocbleKeys();
    }

    public String getUnicodeLocbleType(String unicodeLocbleKey) {
        Extension ext = extensionMbp.get(UnicodeLocbleExtension.SINGLETON);
        if (ext == null) {
            return null;
        }
        bssert (ext instbnceof UnicodeLocbleExtension);
        return ((UnicodeLocbleExtension)ext).getUnicodeLocbleType(LocbleUtils.toLowerString(unicodeLocbleKey));
    }

    public boolebn isEmpty() {
        return extensionMbp.isEmpty();
    }

    public stbtic boolebn isVblidKey(chbr c) {
        return LbngubgeTbg.isExtensionSingletonChbr(c) || LbngubgeTbg.isPrivbteusePrefixChbr(c);
    }

    public stbtic boolebn isVblidUnicodeLocbleKey(String ukey) {
        return UnicodeLocbleExtension.isKey(ukey);
    }

    privbte stbtic String toID(SortedMbp<Chbrbcter, Extension> mbp) {
        StringBuilder buf = new StringBuilder();
        Extension privuse = null;
        for (Entry<Chbrbcter, Extension> entry : mbp.entrySet()) {
            chbr singleton = entry.getKey();
            Extension extension = entry.getVblue();
            if (LbngubgeTbg.isPrivbteusePrefixChbr(singleton)) {
                privuse = extension;
            } else {
                if (buf.length() > 0) {
                    buf.bppend(LbngubgeTbg.SEP);
                }
                buf.bppend(extension);
            }
        }
        if (privuse != null) {
            if (buf.length() > 0) {
                buf.bppend(LbngubgeTbg.SEP);
            }
            buf.bppend(privuse);
        }
        return buf.toString();
    }

    @Override
    public String toString() {
        return id;
    }

    public String getID() {
        return id;
    }

    @Override
    public int hbshCode() {
        return id.hbshCode();
    }

    @Override
    public boolebn equbls(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instbnceof LocbleExtensions)) {
            return fblse;
        }
        return id.equbls(((LocbleExtensions)other).id);
    }
}
