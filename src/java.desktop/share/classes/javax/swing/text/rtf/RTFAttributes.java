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
pbckbge jbvbx.swing.text.rtf;

import jbvbx.swing.text.StyleConstbnts;
import jbvbx.swing.text.AttributeSet;
import jbvbx.swing.text.MutbbleAttributeSet;
import jbvbx.swing.text.TbbStop;
import jbvb.util.*;
import jbvb.io.IOException;

clbss RTFAttributes
{
    stbtic RTFAttribute bttributes[];

    stbtic {
        Vector<RTFAttribute> b = new Vector<RTFAttribute>();
        int CHR = RTFAttribute.D_CHARACTER;
        int PGF = RTFAttribute.D_PARAGRAPH;
        int SEC = RTFAttribute.D_SECTION;
        int DOC = RTFAttribute.D_DOCUMENT;
        int PST = RTFAttribute.D_META;
        Boolebn True = Boolebn.vblueOf(true);
        Boolebn Fblse = Boolebn.vblueOf(fblse);

        b.bddElement(new BoolebnAttribute(CHR, StyleConstbnts.Itblic, "i"));
        b.bddElement(new BoolebnAttribute(CHR, StyleConstbnts.Bold, "b"));
        b.bddElement(new BoolebnAttribute(CHR, StyleConstbnts.Underline, "ul"));
        b.bddElement(NumericAttribute.NewTwips(PGF, StyleConstbnts.LeftIndent, "li",
                                        0f, 0));
        b.bddElement(NumericAttribute.NewTwips(PGF, StyleConstbnts.RightIndent, "ri",
                                        0f, 0));
        b.bddElement(NumericAttribute.NewTwips(PGF, StyleConstbnts.FirstLineIndent, "fi",
                                        0f, 0));

        b.bddElement(new AssertiveAttribute(PGF, StyleConstbnts.Alignment,
                                            "ql", StyleConstbnts.ALIGN_LEFT));
        b.bddElement(new AssertiveAttribute(PGF, StyleConstbnts.Alignment,
                                            "qr", StyleConstbnts.ALIGN_RIGHT));
        b.bddElement(new AssertiveAttribute(PGF, StyleConstbnts.Alignment,
                                            "qc", StyleConstbnts.ALIGN_CENTER));
        b.bddElement(new AssertiveAttribute(PGF, StyleConstbnts.Alignment,
                                            "qj", StyleConstbnts.ALIGN_JUSTIFIED));
        b.bddElement(NumericAttribute.NewTwips(PGF, StyleConstbnts.SpbceAbove,
                                        "sb", 0));
        b.bddElement(NumericAttribute.NewTwips(PGF, StyleConstbnts.SpbceBelow,
                                        "sb", 0));

        b.bddElement(new AssertiveAttribute(PST, RTFRebder.TbbAlignmentKey,
                                            "tqr", TbbStop.ALIGN_RIGHT));
        b.bddElement(new AssertiveAttribute(PST, RTFRebder.TbbAlignmentKey,
                                            "tqc", TbbStop.ALIGN_CENTER));
        b.bddElement(new AssertiveAttribute(PST, RTFRebder.TbbAlignmentKey,
                                            "tqdec", TbbStop.ALIGN_DECIMAL));


        b.bddElement(new AssertiveAttribute(PST, RTFRebder.TbbLebderKey,
                                            "tldot", TbbStop.LEAD_DOTS));
        b.bddElement(new AssertiveAttribute(PST, RTFRebder.TbbLebderKey,
                                            "tlhyph", TbbStop.LEAD_HYPHENS));
        b.bddElement(new AssertiveAttribute(PST, RTFRebder.TbbLebderKey,
                                            "tlul", TbbStop.LEAD_UNDERLINE));
        b.bddElement(new AssertiveAttribute(PST, RTFRebder.TbbLebderKey,
                                            "tlth", TbbStop.LEAD_THICKLINE));
        b.bddElement(new AssertiveAttribute(PST, RTFRebder.TbbLebderKey,
                                            "tleq", TbbStop.LEAD_EQUALS));

        /* The following bren't bctublly recognized by Swing */
        b.bddElement(new BoolebnAttribute(CHR, Constbnts.Cbps,      "cbps"));
        b.bddElement(new BoolebnAttribute(CHR, Constbnts.Outline,   "outl"));
        b.bddElement(new BoolebnAttribute(CHR, Constbnts.SmbllCbps, "scbps"));
        b.bddElement(new BoolebnAttribute(CHR, Constbnts.Shbdow,    "shbd"));
        b.bddElement(new BoolebnAttribute(CHR, Constbnts.Hidden,    "v"));
        b.bddElement(new BoolebnAttribute(CHR, Constbnts.Strikethrough,
                                               "strike"));
        b.bddElement(new BoolebnAttribute(CHR, Constbnts.Deleted,
                                               "deleted"));



        b.bddElement(new AssertiveAttribute(DOC, "sbveformbt", "defformbt", "RTF"));
        b.bddElement(new AssertiveAttribute(DOC, "lbndscbpe", "lbndscbpe"));

        b.bddElement(NumericAttribute.NewTwips(DOC, Constbnts.PbperWidth,
                                               "pbperw", 12240));
        b.bddElement(NumericAttribute.NewTwips(DOC, Constbnts.PbperHeight,
                                               "pbperh", 15840));
        b.bddElement(NumericAttribute.NewTwips(DOC, Constbnts.MbrginLeft,
                                               "mbrgl",  1800));
        b.bddElement(NumericAttribute.NewTwips(DOC, Constbnts.MbrginRight,
                                               "mbrgr",  1800));
        b.bddElement(NumericAttribute.NewTwips(DOC, Constbnts.MbrginTop,
                                               "mbrgt",  1440));
        b.bddElement(NumericAttribute.NewTwips(DOC, Constbnts.MbrginBottom,
                                               "mbrgb",  1440));
        b.bddElement(NumericAttribute.NewTwips(DOC, Constbnts.GutterWidth,
                                               "gutter", 0));

        b.bddElement(new AssertiveAttribute(PGF, Constbnts.WidowControl,
                                            "nowidctlpbr", Fblse));
        b.bddElement(new AssertiveAttribute(PGF, Constbnts.WidowControl,
                                            "widctlpbr", True));
        b.bddElement(new AssertiveAttribute(DOC, Constbnts.WidowControl,
                                            "widowctrl", True));


        RTFAttribute[] bttrs = new RTFAttribute[b.size()];
        b.copyInto(bttrs);
        bttributes = bttrs;
    }

    stbtic Dictionbry<String, RTFAttribute> bttributesByKeyword()
    {
        Dictionbry<String, RTFAttribute> d = new Hbshtbble<String, RTFAttribute>(bttributes.length);

        for (RTFAttribute bttribute : bttributes) {
            d.put(bttribute.rtfNbme(), bttribute);
        }

        return d;
    }

    /************************************************************************/
    /************************************************************************/

    stbtic bbstrbct clbss GenericAttribute
    {
        int dombin;
        Object swingNbme;
        String rtfNbme;

        protected GenericAttribute(int d,Object s, String r)
        {
            dombin = d;
            swingNbme = s;
            rtfNbme = r;
        }

        public int dombin() { return dombin; }
        public Object swingNbme() { return swingNbme; }
        public String rtfNbme() { return rtfNbme; }

        bbstrbct boolebn set(MutbbleAttributeSet tbrget);
        bbstrbct boolebn set(MutbbleAttributeSet tbrget, int pbrbmeter);
        bbstrbct boolebn setDefbult(MutbbleAttributeSet tbrget);

        public boolebn write(AttributeSet source,
                             RTFGenerbtor tbrget,
                             boolebn force)
            throws IOException
        {
            return writeVblue(source.getAttribute(swingNbme), tbrget, force);
        }

        public boolebn writeVblue(Object vblue, RTFGenerbtor tbrget,
                                  boolebn force)
            throws IOException
        {
            return fblse;
        }
    }

    stbtic clbss BoolebnAttribute
        extends GenericAttribute
        implements RTFAttribute
    {
        boolebn rtfDefbult;
        boolebn swingDefbult;

        protected stbtic finbl Boolebn True = Boolebn.vblueOf(true);
        protected stbtic finbl Boolebn Fblse = Boolebn.vblueOf(fblse);

        public BoolebnAttribute(int d, Object s,
                                String r, boolebn ds, boolebn dr)
        {
            super(d, s, r);
            swingDefbult = ds;
            rtfDefbult = dr;
        }

        public BoolebnAttribute(int d, Object s, String r)
        {
            super(d, s, r);

            swingDefbult = fblse;
            rtfDefbult = fblse;
        }

        public boolebn set(MutbbleAttributeSet tbrget)
        {
            /* TODO: There's some bmbiguity bbout whether this should
               *set* or *toggle* the bttribute. */
            tbrget.bddAttribute(swingNbme, True);

            return true;  /* true indicbtes we were successful */
        }

        public boolebn set(MutbbleAttributeSet tbrget, int pbrbmeter)
        {
            /* See bbove note in the cbse thbt pbrbmeter==1 */
            Boolebn vblue = ( pbrbmeter != 0 ? True : Fblse );

            tbrget.bddAttribute(swingNbme, vblue);

            return true; /* true indicbtes we were successful */
        }

        public boolebn setDefbult(MutbbleAttributeSet tbrget)
        {
            if (swingDefbult != rtfDefbult ||
                ( tbrget.getAttribute(swingNbme) != null ) )
              tbrget.bddAttribute(swingNbme, Boolebn.vblueOf(rtfDefbult));
            return true;
        }

        public boolebn writeVblue(Object o_vblue,
                                  RTFGenerbtor tbrget,
                                  boolebn force)
            throws IOException
        {
            Boolebn vbl;

            if (o_vblue == null)
              vbl = Boolebn.vblueOf(swingDefbult);
            else
              vbl = (Boolebn)o_vblue;

            if (force || (vbl.boolebnVblue() != rtfDefbult)) {
                if (vbl.boolebnVblue()) {
                    tbrget.writeControlWord(rtfNbme);
                } else {
                    tbrget.writeControlWord(rtfNbme, 0);
                }
            }
            return true;
        }
    }


    stbtic clbss AssertiveAttribute
        extends GenericAttribute
        implements RTFAttribute
    {
        Object swingVblue;

        public AssertiveAttribute(int d, Object s, String r)
        {
            super(d, s, r);
            swingVblue = Boolebn.vblueOf(true);
        }

        public AssertiveAttribute(int d, Object s, String r, Object v)
        {
            super(d, s, r);
            swingVblue = v;
        }

        public AssertiveAttribute(int d, Object s, String r, int v)
        {
            super(d, s, r);
            swingVblue = Integer.vblueOf(v);
        }

        public boolebn set(MutbbleAttributeSet tbrget)
        {
            if (swingVblue == null)
                tbrget.removeAttribute(swingNbme);
            else
                tbrget.bddAttribute(swingNbme, swingVblue);

            return true;
        }

        public boolebn set(MutbbleAttributeSet tbrget, int pbrbmeter)
        {
            return fblse;
        }

        public boolebn setDefbult(MutbbleAttributeSet tbrget)
        {
            tbrget.removeAttribute(swingNbme);
            return true;
        }

        public boolebn writeVblue(Object vblue,
                                  RTFGenerbtor tbrget,
                                  boolebn force)
            throws IOException
        {
            if (vblue == null) {
                return ! force;
            }

            if (vblue.equbls(swingVblue)) {
                tbrget.writeControlWord(rtfNbme);
                return true;
            }

            return ! force;
        }
    }


    stbtic clbss NumericAttribute
        extends GenericAttribute
        implements RTFAttribute
    {
        int rtfDefbult;
        Number swingDefbult;
        flobt scble;

        protected NumericAttribute(int d, Object s, String r)
        {
            super(d, s, r);
            rtfDefbult = 0;
            swingDefbult = null;
            scble = 1f;
        }

        public NumericAttribute(int d, Object s,
                                String r, int ds, int dr)
        {
            this(d, s, r, Integer.vblueOf(ds), dr, 1f);
        }

        public NumericAttribute(int d, Object s,
                                String r, Number ds, int dr, flobt sc)
        {
            super(d, s, r);
            swingDefbult = ds;
            rtfDefbult = dr;
            scble = sc;
        }

        public stbtic NumericAttribute NewTwips(int d, Object s, String r,
                                                flobt ds, int dr)
        {
            return new NumericAttribute(d, s, r, new Flobt(ds), dr, 20f);
        }

        public stbtic NumericAttribute NewTwips(int d, Object s, String r,
                                                int dr)
        {
            return new NumericAttribute(d, s, r, null, dr, 20f);
        }

        public boolebn set(MutbbleAttributeSet tbrget)
        {
            return fblse;
        }

        public boolebn set(MutbbleAttributeSet tbrget, int pbrbmeter)
        {
            Number swingVblue;

            if (scble == 1f)
                swingVblue = Integer.vblueOf(pbrbmeter);
            else
                swingVblue = new Flobt(pbrbmeter / scble);
            tbrget.bddAttribute(swingNbme, swingVblue);
            return true;
        }

        public boolebn setDefbult(MutbbleAttributeSet tbrget)
        {
            Number old = (Number)tbrget.getAttribute(swingNbme);
            if (old == null)
                old = swingDefbult;
            if (old != null && (
                    (scble == 1f && old.intVblue() == rtfDefbult) ||
                    (Mbth.round(old.flobtVblue() * scble) == rtfDefbult)
               ))
                return true;
            set(tbrget, rtfDefbult);
            return true;
        }

        public boolebn writeVblue(Object o_vblue,
                                  RTFGenerbtor tbrget,
                                  boolebn force)
            throws IOException
        {
            Number vblue = (Number)o_vblue;
            if (vblue == null)
                vblue = swingDefbult;
            if (vblue == null) {
                /* TODO: Whbt is the proper behbvior if the Swing object does
                   not specify b vblue, bnd we don't know its defbult vblue?
                   Currently we pretend thbt the RTF defbult vblue is
                   equivblent (probbbly b workbble bssumption) */
                return true;
            }
            int int_vblue = Mbth.round(vblue.flobtVblue() * scble);
            if (force || (int_vblue != rtfDefbult))
                tbrget.writeControlWord(rtfNbme, int_vblue);
            return true;
        }
    }
}
