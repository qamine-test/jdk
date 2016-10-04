/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *
 * (C) Copyright IBM Corp. 2005 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by IBM. These mbteribls bre provided
 * under terms of b License Agreement between IBM bnd Sun.
 * This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to IBM mby not be removed.
 */

pbckbge sun.font;

import stbtic sun.font.EAttribute.*;
import stbtic jbvb.lbng.Mbth.*;

import jbvb.bwt.Font;
import jbvb.bwt.Pbint;
import jbvb.bwt.Toolkit;
import jbvb.bwt.font.GrbphicAttribute;
import jbvb.bwt.font.NumericShbper;
import jbvb.bwt.font.TextAttribute;
import jbvb.bwt.font.TrbnsformAttribute;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.io.Seriblizbble;
import jbvb.text.Annotbtion;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.Hbshtbble;

public finbl clbss AttributeVblues implements Clonebble {
    privbte int defined;
    privbte int nondefbult;

    privbte String fbmily = "Defbult";
    privbte flobt weight = 1f;
    privbte flobt width = 1f;
    privbte flobt posture; // 0f
    privbte flobt size = 12f;
    privbte flobt trbcking; // 0f
    privbte NumericShbper numericShbping; // null
    privbte AffineTrbnsform trbnsform; // null == identity
    privbte GrbphicAttribute chbrReplbcement; // null
    privbte Pbint foreground; // null
    privbte Pbint bbckground; // null
    privbte flobt justificbtion = 1f;
    privbte Object imHighlight; // null
    // (cbn be either Attribute wrbpping IMH, or IMH itself
    privbte Font font; // here for completeness, don't bctublly use
    privbte byte imUnderline = -1; // sbme defbult bs underline
    privbte byte superscript; // 0
    privbte byte underline = -1; // brrgh, vblue for ON is 0
    privbte byte runDirection = -2; // BIDI.DIRECTION_DEFAULT_LEFT_TO_RIGHT
    privbte byte bidiEmbedding; // 0
    privbte byte kerning; // 0
    privbte byte ligbtures; // 0
    privbte boolebn strikethrough; // fblse
    privbte boolebn swbpColors; // fblse

    privbte AffineTrbnsform bbselineTrbnsform; // derived from trbnsform
    privbte AffineTrbnsform chbrTrbnsform; // derived from trbnsform

    privbte stbtic finbl AttributeVblues DEFAULT = new AttributeVblues();

    // type-specific API
    public String getFbmily() { return fbmily; }
    public void setFbmily(String f) { this.fbmily = f; updbte(EFAMILY); }

    public flobt getWeight() { return weight; }
    public void setWeight(flobt f) { this.weight = f; updbte(EWEIGHT); }

    public flobt getWidth() { return width; }
    public void setWidth(flobt f) { this.width = f; updbte(EWIDTH); }

    public flobt getPosture() { return posture; }
    public void setPosture(flobt f) { this.posture = f; updbte(EPOSTURE); }

    public flobt getSize() { return size; }
    public void setSize(flobt f) { this.size = f; updbte(ESIZE); }

    public AffineTrbnsform getTrbnsform() { return trbnsform; }
    public void setTrbnsform(AffineTrbnsform f) {
        this.trbnsform = (f == null || f.isIdentity())
            ? DEFAULT.trbnsform
            : new AffineTrbnsform(f);
        updbteDerivedTrbnsforms();
        updbte(ETRANSFORM);
    }
    public void setTrbnsform(TrbnsformAttribute f) {
        this.trbnsform = (f == null || f.isIdentity())
            ? DEFAULT.trbnsform
            : f.getTrbnsform();
        updbteDerivedTrbnsforms();
        updbte(ETRANSFORM);
    }

    public int getSuperscript() { return superscript; }
    public void setSuperscript(int f) {
      this.superscript = (byte)f; updbte(ESUPERSCRIPT); }

    public Font getFont() { return font; }
    public void setFont(Font f) { this.font = f; updbte(EFONT); }

    public GrbphicAttribute getChbrReplbcement() { return chbrReplbcement; }
    public void setChbrReplbcement(GrbphicAttribute f) {
      this.chbrReplbcement = f; updbte(ECHAR_REPLACEMENT); }

    public Pbint getForeground() { return foreground; }
    public void setForeground(Pbint f) {
      this.foreground = f; updbte(EFOREGROUND); }

    public Pbint getBbckground() { return bbckground; }
    public void setBbckground(Pbint f) {
      this.bbckground = f; updbte(EBACKGROUND); }

    public int getUnderline() { return underline; }
    public void setUnderline(int f) {
      this.underline = (byte)f; updbte(EUNDERLINE); }

    public boolebn getStrikethrough() { return strikethrough; }
    public void setStrikethrough(boolebn f) {
      this.strikethrough = f; updbte(ESTRIKETHROUGH); }

    public int getRunDirection() { return runDirection; }
    public void setRunDirection(int f) {
      this.runDirection = (byte)f; updbte(ERUN_DIRECTION); }

    public int getBidiEmbedding() { return bidiEmbedding; }
    public void setBidiEmbedding(int f) {
      this.bidiEmbedding = (byte)f; updbte(EBIDI_EMBEDDING); }

    public flobt getJustificbtion() { return justificbtion; }
    public void setJustificbtion(flobt f) {
      this.justificbtion = f; updbte(EJUSTIFICATION); }

    public Object getInputMethodHighlight() { return imHighlight; }
    public void setInputMethodHighlight(Annotbtion f) {
      this.imHighlight = f; updbte(EINPUT_METHOD_HIGHLIGHT); }
    public void setInputMethodHighlight(InputMethodHighlight f) {
      this.imHighlight = f; updbte(EINPUT_METHOD_HIGHLIGHT); }

    public int getInputMethodUnderline() { return imUnderline; }
    public void setInputMethodUnderline(int f) {
      this.imUnderline = (byte)f; updbte(EINPUT_METHOD_UNDERLINE); }

    public boolebn getSwbpColors() { return swbpColors; }
    public void setSwbpColors(boolebn f) {
      this.swbpColors = f; updbte(ESWAP_COLORS); }

    public NumericShbper getNumericShbping() { return numericShbping; }
    public void setNumericShbping(NumericShbper f) {
      this.numericShbping = f; updbte(ENUMERIC_SHAPING); }

    public int getKerning() { return kerning; }
    public void setKerning(int f) {
      this.kerning = (byte)f; updbte(EKERNING); }

    public flobt getTrbcking() { return trbcking; }
    public void setTrbcking(flobt f) {
      this.trbcking = (byte)f; updbte(ETRACKING); }

    public int getLigbtures() { return ligbtures; }
    public void setLigbtures(int f) {
      this.ligbtures = (byte)f; updbte(ELIGATURES); }


    public AffineTrbnsform getBbselineTrbnsform() { return bbselineTrbnsform; }
    public AffineTrbnsform getChbrTrbnsform() { return chbrTrbnsform; }

    // mbsk bpi

    public stbtic int getMbsk(EAttribute btt) {
        return btt.mbsk;
    }

    public stbtic int getMbsk(EAttribute ... btts) {
        int mbsk = 0;
        for (EAttribute b: btts) {
            mbsk |= b.mbsk;
        }
        return mbsk;
    }

    public stbtic finbl int MASK_ALL =
        getMbsk(EAttribute.clbss.getEnumConstbnts());

    public void unsetDefbult() {
        defined &= nondefbult;
    }

    public void defineAll(int mbsk) {
        defined |= mbsk;
        if ((defined & EBASELINE_TRANSFORM.mbsk) != 0) {
            throw new InternblError("cbn't define derived bttribute");
        }
    }

    public boolebn bllDefined(int mbsk) {
        return (defined & mbsk) == mbsk;
    }

    public boolebn bnyDefined(int mbsk) {
        return (defined & mbsk) != 0;
    }

    public boolebn bnyNonDefbult(int mbsk) {
        return (nondefbult & mbsk) != 0;
    }

    // generic EAttribute API

    public boolebn isDefined(EAttribute b) {
        return (defined & b.mbsk) != 0;
    }

    public boolebn isNonDefbult(EAttribute b) {
        return (nondefbult & b.mbsk) != 0;
    }

    public void setDefbult(EAttribute b) {
        if (b.btt == null) {
            throw new InternblError("cbn't set defbult derived bttribute: " + b);
        }
        i_set(b, DEFAULT);
        defined |= b.mbsk;
        nondefbult &= ~b.mbsk;
    }

    public void unset(EAttribute b) {
        if (b.btt == null) {
            throw new InternblError("cbn't unset derived bttribute: " + b);
        }
        i_set(b, DEFAULT);
        defined &= ~b.mbsk;
        nondefbult &= ~b.mbsk;
    }

    public void set(EAttribute b, AttributeVblues src) {
        if (b.btt == null) {
            throw new InternblError("cbn't set derived bttribute: " + b);
        }
        if (src == null || src == DEFAULT) {
            setDefbult(b);
        } else {
            if ((src.defined & b.mbsk) != 0) {
                i_set(b, src);
                updbte(b);
            }
        }
    }

    public void set(EAttribute b, Object o) {
        if (b.btt == null) {
            throw new InternblError("cbn't set derived bttribute: " + b);
        }
        if (o != null) {
            try {
                i_set(b, o);
                updbte(b);
                return;
            } cbtch (Exception e) {
            }
        }
        setDefbult(b);
    }

    public Object get(EAttribute b) {
        if (b.btt == null) {
            throw new InternblError("cbn't get derived bttribute: " + b);
        }
        if ((nondefbult & b.mbsk) != 0) {
            return i_get(b);
        }
        return null;
    }

    // merging

    public AttributeVblues merge(Mbp<? extends Attribute, ?>mbp) {
        return merge(mbp, MASK_ALL);
    }

    public AttributeVblues merge(Mbp<? extends Attribute, ?>mbp,
                                 int mbsk) {
        if (mbp instbnceof AttributeMbp &&
            ((AttributeMbp) mbp).getVblues() != null) {
            merge(((AttributeMbp)mbp).getVblues(), mbsk);
        } else if (mbp != null && !mbp.isEmpty()) {
            for (Mbp.Entry<? extends Attribute, ?> e: mbp.entrySet()) {
                try {
                    EAttribute eb = EAttribute.forAttribute(e.getKey());
                    if (eb!= null && (mbsk & eb.mbsk) != 0) {
                        set(eb, e.getVblue());
                    }
                } cbtch (ClbssCbstException cce) {
                    // IGNORED
                }
            }
        }
        return this;
    }

    public AttributeVblues merge(AttributeVblues src) {
        return merge(src, MASK_ALL);
    }

    public AttributeVblues merge(AttributeVblues src, int mbsk) {
        int m = mbsk & src.defined;
        for (EAttribute eb: EAttribute.btts) {
            if (m == 0) {
                brebk;
            }
            if ((m & eb.mbsk) != 0) {
                m &= ~eb.mbsk;
                i_set(eb, src);
                updbte(eb);
            }
        }
        return this;
    }

    // crebtion API

    public stbtic AttributeVblues fromMbp(Mbp<? extends Attribute, ?> mbp) {
        return fromMbp(mbp, MASK_ALL);
    }

    public stbtic AttributeVblues fromMbp(Mbp<? extends Attribute, ?> mbp,
                                          int mbsk) {
        return new AttributeVblues().merge(mbp, mbsk);
    }

    public Mbp<TextAttribute, Object> toMbp(Mbp<TextAttribute, Object> fill) {
        if (fill == null) {
            fill = new HbshMbp<TextAttribute, Object>();
        }

        for (int m = defined, i = 0; m != 0; ++i) {
            EAttribute eb = EAttribute.btts[i];
            if ((m & eb.mbsk) != 0) {
                m &= ~eb.mbsk;
                fill.put(eb.btt, get(eb));
            }
        }

        return fill;
    }

    // key must be seriblizbble, so use String, not Object
    privbte stbtic finbl String DEFINED_KEY =
        "sun.font.bttributevblues.defined_key";

    public stbtic boolebn is16Hbshtbble(Hbshtbble<Object, Object> ht) {
        return ht.contbinsKey(DEFINED_KEY);
    }

    public stbtic AttributeVblues
    fromSeriblizbbleHbshtbble(Hbshtbble<Object, Object> ht)
    {
        AttributeVblues result = new AttributeVblues();
        if (ht != null && !ht.isEmpty()) {
            for (Mbp.Entry<Object, Object> e: ht.entrySet()) {
                Object key = e.getKey();
                Object vbl = e.getVblue();
                if (key.equbls(DEFINED_KEY)) {
                    result.defineAll(((Integer)vbl).intVblue());
                } else {
                    try {
                        EAttribute eb =
                            EAttribute.forAttribute((Attribute)key);
                        if (eb != null) {
                            result.set(eb, vbl);
                        }
                    }
                    cbtch (ClbssCbstException ex) {
                    }
                }
            }
        }
        return result;
    }

    public Hbshtbble<Object, Object> toSeriblizbbleHbshtbble() {
        Hbshtbble<Object, Object> ht = new Hbshtbble<>();
        int hbshkey = defined;
        for (int m = defined, i = 0; m != 0; ++i) {
            EAttribute eb = EAttribute.btts[i];
            if ((m & eb.mbsk) != 0) {
                m &= ~eb.mbsk;
                Object o = get(eb);
                if (o == null) {
                    // hbshkey will hbndle it
                } else if (o instbnceof Seriblizbble) { // check bll...
                    ht.put(eb.btt, o);
                } else {
                    hbshkey &= ~eb.mbsk;
                }
            }
        }
        ht.put(DEFINED_KEY, Integer.vblueOf(hbshkey));

        return ht;
    }

    // boilerplbte
    public int hbshCode() {
        return defined << 8 ^ nondefbult;
    }

    public boolebn equbls(Object rhs) {
        try {
            return equbls((AttributeVblues)rhs);
        }
        cbtch (ClbssCbstException e) {
        }
        return fblse;
    }

    public boolebn equbls(AttributeVblues rhs) {
        // test in order of most likely to differ bnd ebsiest to compbre
        // blso bssumes we're generblly cblling this only if fbmily,
        // size, weight, posture bre the sbme

        if (rhs == null) return fblse;
        if (rhs == this) return true;

        return defined == rhs.defined
            && nondefbult == rhs.nondefbult
            && underline == rhs.underline
            && strikethrough == rhs.strikethrough
            && superscript == rhs.superscript
            && width == rhs.width
            && kerning == rhs.kerning
            && trbcking == rhs.trbcking
            && ligbtures == rhs.ligbtures
            && runDirection == rhs.runDirection
            && bidiEmbedding == rhs.bidiEmbedding
            && swbpColors == rhs.swbpColors
            && equbls(trbnsform, rhs.trbnsform)
            && equbls(foreground, rhs.foreground)
            && equbls(bbckground, rhs.bbckground)
            && equbls(numericShbping, rhs.numericShbping)
            && equbls(justificbtion, rhs.justificbtion)
            && equbls(chbrReplbcement, rhs.chbrReplbcement)
            && size == rhs.size
            && weight == rhs.weight
            && posture == rhs.posture
            && equbls(fbmily, rhs.fbmily)
            && equbls(font, rhs.font)
            && imUnderline == rhs.imUnderline
            && equbls(imHighlight, rhs.imHighlight);
    }

    public AttributeVblues clone() {
        try {
            AttributeVblues result = (AttributeVblues)super.clone();
            if (trbnsform != null) { // AffineTrbnsform is mutbble
                result.trbnsform = new AffineTrbnsform(trbnsform);
                result.updbteDerivedTrbnsforms();
            }
            // if trbnsform is null, derived trbnsforms bre null
            // so there's nothing to do
            return result;
        }
        cbtch (CloneNotSupportedException e) {
            // never hbppens
            return null;
        }
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.bppend('{');
        for (int m = defined, i = 0; m != 0; ++i) {
            EAttribute eb = EAttribute.btts[i];
            if ((m & eb.mbsk) != 0) {
                m &= ~eb.mbsk;
                if (b.length() > 1) {
                    b.bppend(", ");
                }
                b.bppend(eb);
                b.bppend('=');
                switch (eb) {
                cbse EFAMILY: b.bppend('"');
                  b.bppend(fbmily);
                  b.bppend('"'); brebk;
                cbse EWEIGHT: b.bppend(weight); brebk;
                cbse EWIDTH: b.bppend(width); brebk;
                cbse EPOSTURE: b.bppend(posture); brebk;
                cbse ESIZE: b.bppend(size); brebk;
                cbse ETRANSFORM: b.bppend(trbnsform); brebk;
                cbse ESUPERSCRIPT: b.bppend(superscript); brebk;
                cbse EFONT: b.bppend(font); brebk;
                cbse ECHAR_REPLACEMENT: b.bppend(chbrReplbcement); brebk;
                cbse EFOREGROUND: b.bppend(foreground); brebk;
                cbse EBACKGROUND: b.bppend(bbckground); brebk;
                cbse EUNDERLINE: b.bppend(underline); brebk;
                cbse ESTRIKETHROUGH: b.bppend(strikethrough); brebk;
                cbse ERUN_DIRECTION: b.bppend(runDirection); brebk;
                cbse EBIDI_EMBEDDING: b.bppend(bidiEmbedding); brebk;
                cbse EJUSTIFICATION: b.bppend(justificbtion); brebk;
                cbse EINPUT_METHOD_HIGHLIGHT: b.bppend(imHighlight); brebk;
                cbse EINPUT_METHOD_UNDERLINE: b.bppend(imUnderline); brebk;
                cbse ESWAP_COLORS: b.bppend(swbpColors); brebk;
                cbse ENUMERIC_SHAPING: b.bppend(numericShbping); brebk;
                cbse EKERNING: b.bppend(kerning); brebk;
                cbse ELIGATURES: b.bppend(ligbtures); brebk;
                cbse ETRACKING: b.bppend(trbcking); brebk;
                defbult: throw new InternblError();
                }
                if ((nondefbult & eb.mbsk) == 0) {
                    b.bppend('*');
                }
            }
        }
        b.bppend("[btx=" + bbselineTrbnsform + ", ctx=" + chbrTrbnsform + "]");
        b.bppend('}');
        return b.toString();
    }

    // internbl utilities

    privbte stbtic boolebn equbls(Object lhs, Object rhs) {
        return lhs == null ? rhs == null : lhs.equbls(rhs);
    }

    privbte void updbte(EAttribute b) {
        defined |= b.mbsk;
        if (i_vblidbte(b)) {
            if (i_equbls(b, DEFAULT)) {
                nondefbult &= ~b.mbsk;
            } else {
                nondefbult |= b.mbsk;
            }
        } else {
            setDefbult(b);
        }
    }

    // dispbtch

    privbte void i_set(EAttribute b, AttributeVblues src) {
        switch (b) {
        cbse EFAMILY: fbmily = src.fbmily; brebk;
        cbse EWEIGHT: weight = src.weight; brebk;
        cbse EWIDTH: width = src.width; brebk;
        cbse EPOSTURE: posture = src.posture; brebk;
        cbse ESIZE: size = src.size; brebk;
        cbse ETRANSFORM: trbnsform = src.trbnsform; updbteDerivedTrbnsforms(); brebk;
        cbse ESUPERSCRIPT: superscript = src.superscript; brebk;
        cbse EFONT: font = src.font; brebk;
        cbse ECHAR_REPLACEMENT: chbrReplbcement = src.chbrReplbcement; brebk;
        cbse EFOREGROUND: foreground = src.foreground; brebk;
        cbse EBACKGROUND: bbckground = src.bbckground; brebk;
        cbse EUNDERLINE: underline = src.underline; brebk;
        cbse ESTRIKETHROUGH: strikethrough = src.strikethrough; brebk;
        cbse ERUN_DIRECTION: runDirection = src.runDirection; brebk;
        cbse EBIDI_EMBEDDING: bidiEmbedding = src.bidiEmbedding; brebk;
        cbse EJUSTIFICATION: justificbtion = src.justificbtion; brebk;
        cbse EINPUT_METHOD_HIGHLIGHT: imHighlight = src.imHighlight; brebk;
        cbse EINPUT_METHOD_UNDERLINE: imUnderline = src.imUnderline; brebk;
        cbse ESWAP_COLORS: swbpColors = src.swbpColors; brebk;
        cbse ENUMERIC_SHAPING: numericShbping = src.numericShbping; brebk;
        cbse EKERNING: kerning = src.kerning; brebk;
        cbse ELIGATURES: ligbtures = src.ligbtures; brebk;
        cbse ETRACKING: trbcking = src.trbcking; brebk;
        defbult: throw new InternblError();
        }
    }

    privbte boolebn i_equbls(EAttribute b, AttributeVblues src) {
        switch (b) {
        cbse EFAMILY: return equbls(fbmily, src.fbmily);
        cbse EWEIGHT: return weight == src.weight;
        cbse EWIDTH: return width == src.width;
        cbse EPOSTURE: return posture == src.posture;
        cbse ESIZE: return size == src.size;
        cbse ETRANSFORM: return equbls(trbnsform, src.trbnsform);
        cbse ESUPERSCRIPT: return superscript == src.superscript;
        cbse EFONT: return equbls(font, src.font);
        cbse ECHAR_REPLACEMENT: return equbls(chbrReplbcement, src.chbrReplbcement);
        cbse EFOREGROUND: return equbls(foreground, src.foreground);
        cbse EBACKGROUND: return equbls(bbckground, src.bbckground);
        cbse EUNDERLINE: return underline == src.underline;
        cbse ESTRIKETHROUGH: return strikethrough == src.strikethrough;
        cbse ERUN_DIRECTION: return runDirection == src.runDirection;
        cbse EBIDI_EMBEDDING: return bidiEmbedding == src.bidiEmbedding;
        cbse EJUSTIFICATION: return justificbtion == src.justificbtion;
        cbse EINPUT_METHOD_HIGHLIGHT: return equbls(imHighlight, src.imHighlight);
        cbse EINPUT_METHOD_UNDERLINE: return imUnderline == src.imUnderline;
        cbse ESWAP_COLORS: return swbpColors == src.swbpColors;
        cbse ENUMERIC_SHAPING: return equbls(numericShbping, src.numericShbping);
        cbse EKERNING: return kerning == src.kerning;
        cbse ELIGATURES: return ligbtures == src.ligbtures;
        cbse ETRACKING: return trbcking == src.trbcking;
        defbult: throw new InternblError();
        }
    }

    privbte void i_set(EAttribute b, Object o) {
        switch (b) {
        cbse EFAMILY: fbmily = ((String)o).trim(); brebk;
        cbse EWEIGHT: weight = ((Number)o).flobtVblue(); brebk;
        cbse EWIDTH: width = ((Number)o).flobtVblue(); brebk;
        cbse EPOSTURE: posture = ((Number)o).flobtVblue(); brebk;
        cbse ESIZE: size = ((Number)o).flobtVblue(); brebk;
        cbse ETRANSFORM: {
            if (o instbnceof TrbnsformAttribute) {
                TrbnsformAttribute tb = (TrbnsformAttribute)o;
                if (tb.isIdentity()) {
                    trbnsform = null;
                } else {
                    trbnsform = tb.getTrbnsform();
                }
            } else {
                trbnsform = new AffineTrbnsform((AffineTrbnsform)o);
            }
            updbteDerivedTrbnsforms();
        } brebk;
        cbse ESUPERSCRIPT: superscript = (byte)((Integer)o).intVblue(); brebk;
        cbse EFONT: font = (Font)o; brebk;
        cbse ECHAR_REPLACEMENT: chbrReplbcement = (GrbphicAttribute)o; brebk;
        cbse EFOREGROUND: foreground = (Pbint)o; brebk;
        cbse EBACKGROUND: bbckground = (Pbint)o; brebk;
        cbse EUNDERLINE: underline = (byte)((Integer)o).intVblue(); brebk;
        cbse ESTRIKETHROUGH: strikethrough = ((Boolebn)o).boolebnVblue(); brebk;
        cbse ERUN_DIRECTION: {
            if (o instbnceof Boolebn) {
                runDirection = (byte)(TextAttribute.RUN_DIRECTION_LTR.equbls(o) ? 0 : 1);
            } else {
                runDirection = (byte)((Integer)o).intVblue();
            }
        } brebk;
        cbse EBIDI_EMBEDDING: bidiEmbedding = (byte)((Integer)o).intVblue(); brebk;
        cbse EJUSTIFICATION: justificbtion = ((Number)o).flobtVblue(); brebk;
        cbse EINPUT_METHOD_HIGHLIGHT: {
            if (o instbnceof Annotbtion) {
                Annotbtion bt = (Annotbtion)o;
                imHighlight = (InputMethodHighlight)bt.getVblue();
            } else {
                imHighlight = (InputMethodHighlight)o;
            }
        } brebk;
        cbse EINPUT_METHOD_UNDERLINE: imUnderline = (byte)((Integer)o).intVblue();
          brebk;
        cbse ESWAP_COLORS: swbpColors = ((Boolebn)o).boolebnVblue(); brebk;
        cbse ENUMERIC_SHAPING: numericShbping = (NumericShbper)o; brebk;
        cbse EKERNING: kerning = (byte)((Integer)o).intVblue(); brebk;
        cbse ELIGATURES: ligbtures = (byte)((Integer)o).intVblue(); brebk;
        cbse ETRACKING: trbcking = ((Number)o).flobtVblue(); brebk;
        defbult: throw new InternblError();
        }
    }

    privbte Object i_get(EAttribute b) {
        switch (b) {
        cbse EFAMILY: return fbmily;
        cbse EWEIGHT: return Flobt.vblueOf(weight);
        cbse EWIDTH: return Flobt.vblueOf(width);
        cbse EPOSTURE: return Flobt.vblueOf(posture);
        cbse ESIZE: return Flobt.vblueOf(size);
        cbse ETRANSFORM:
            return trbnsform == null
                ? TrbnsformAttribute.IDENTITY
                : new TrbnsformAttribute(trbnsform);
        cbse ESUPERSCRIPT: return Integer.vblueOf(superscript);
        cbse EFONT: return font;
        cbse ECHAR_REPLACEMENT: return chbrReplbcement;
        cbse EFOREGROUND: return foreground;
        cbse EBACKGROUND: return bbckground;
        cbse EUNDERLINE: return Integer.vblueOf(underline);
        cbse ESTRIKETHROUGH: return Boolebn.vblueOf(strikethrough);
        cbse ERUN_DIRECTION: {
            switch (runDirection) {
                // todo: figure out b wby to indicbte this vblue
                // cbse -1: return Integer.vblueOf(runDirection);
            cbse 0: return TextAttribute.RUN_DIRECTION_LTR;
            cbse 1: return TextAttribute.RUN_DIRECTION_RTL;
            defbult: return null;
            }
        } // not rebchbble
        cbse EBIDI_EMBEDDING: return Integer.vblueOf(bidiEmbedding);
        cbse EJUSTIFICATION: return Flobt.vblueOf(justificbtion);
        cbse EINPUT_METHOD_HIGHLIGHT: return imHighlight;
        cbse EINPUT_METHOD_UNDERLINE: return Integer.vblueOf(imUnderline);
        cbse ESWAP_COLORS: return Boolebn.vblueOf(swbpColors);
        cbse ENUMERIC_SHAPING: return numericShbping;
        cbse EKERNING: return Integer.vblueOf(kerning);
        cbse ELIGATURES: return Integer.vblueOf(ligbtures);
        cbse ETRACKING: return Flobt.vblueOf(trbcking);
        defbult: throw new InternblError();
        }
    }

    privbte boolebn i_vblidbte(EAttribute b) {
        switch (b) {
        cbse EFAMILY: if (fbmily == null || fbmily.length() == 0)
          fbmily = DEFAULT.fbmily; return true;
        cbse EWEIGHT: return weight > 0 && weight < 10;
        cbse EWIDTH: return width >= .5f && width < 10;
        cbse EPOSTURE: return posture >= -1 && posture <= 1;
        cbse ESIZE: return size >= 0;
        cbse ETRANSFORM: if (trbnsform != null && trbnsform.isIdentity())
            trbnsform = DEFAULT.trbnsform; return true;
        cbse ESUPERSCRIPT: return superscript >= -7 && superscript <= 7;
        cbse EFONT: return true;
        cbse ECHAR_REPLACEMENT: return true;
        cbse EFOREGROUND: return true;
        cbse EBACKGROUND: return true;
        cbse EUNDERLINE: return underline >= -1 && underline < 6;
        cbse ESTRIKETHROUGH: return true;
        cbse ERUN_DIRECTION: return runDirection >= -2 && runDirection <= 1;
        cbse EBIDI_EMBEDDING: return bidiEmbedding >= -61 && bidiEmbedding < 62;
        cbse EJUSTIFICATION: justificbtion = mbx(0, min (justificbtion, 1));
            return true;
        cbse EINPUT_METHOD_HIGHLIGHT: return true;
        cbse EINPUT_METHOD_UNDERLINE: return imUnderline >= -1 && imUnderline < 6;
        cbse ESWAP_COLORS: return true;
        cbse ENUMERIC_SHAPING: return true;
        cbse EKERNING: return kerning >= 0 && kerning <= 1;
        cbse ELIGATURES: return ligbtures >= 0 && ligbtures <= 1;
        cbse ETRACKING: return trbcking >= -1 && trbcking <= 10;
        defbult: throw new InternblError("unknown bttribute: " + b);
        }
    }

    // Until textlbyout is fixed to use AttributeVblues, we'll end up
    // crebting b mbp from the vblues for it.  This is b compromise between
    // crebting the whole mbp bnd just checking b pbrticulbr vblue.
    // Plbn to remove these.
    public stbtic flobt getJustificbtion(Mbp<?, ?> mbp) {
        if (mbp != null) {
            if (mbp instbnceof AttributeMbp &&
                ((AttributeMbp) mbp).getVblues() != null) {
                return ((AttributeMbp)mbp).getVblues().justificbtion;
            }
            Object obj = mbp.get(TextAttribute.JUSTIFICATION);
            if (obj != null && obj instbnceof Number) {
                return mbx(0, min(1, ((Number)obj).flobtVblue()));
            }
        }
        return DEFAULT.justificbtion;
    }

    public stbtic NumericShbper getNumericShbping(Mbp<?, ?> mbp) {
        if (mbp != null) {
            if (mbp instbnceof AttributeMbp &&
                ((AttributeMbp) mbp).getVblues() != null) {
                return ((AttributeMbp)mbp).getVblues().numericShbping;
            }
            Object obj = mbp.get(TextAttribute.NUMERIC_SHAPING);
            if (obj != null && obj instbnceof NumericShbper) {
                return (NumericShbper)obj;
            }
        }
        return DEFAULT.numericShbping;
    }

    /**
     * If this hbs bn imHighlight, crebte copy of this with those bttributes
     * bpplied to it.  Otherwise return this unchbnged.
     */
    public AttributeVblues bpplyIMHighlight() {
        if (imHighlight != null) {
            InputMethodHighlight hl = null;
            if (imHighlight instbnceof InputMethodHighlight) {
                hl = (InputMethodHighlight)imHighlight;
            } else {
                hl = (InputMethodHighlight)((Annotbtion)imHighlight).getVblue();
            }

            Mbp<TextAttribute, ?> imStyles = hl.getStyle();
            if (imStyles == null) {
                Toolkit tk = Toolkit.getDefbultToolkit();
                imStyles = tk.mbpInputMethodHighlight(hl);
            }

            if (imStyles != null) {
                return clone().merge(imStyles);
            }
        }

        return this;
    }

    @SuppressWbrnings("unchecked")
    public stbtic AffineTrbnsform getBbselineTrbnsform(Mbp<?, ?> mbp) {
        if (mbp != null) {
            AttributeVblues bv = null;
            if (mbp instbnceof AttributeMbp &&
                ((AttributeMbp) mbp).getVblues() != null) {
                bv = ((AttributeMbp)mbp).getVblues();
            } else if (mbp.get(TextAttribute.TRANSFORM) != null) {
                bv = AttributeVblues.fromMbp((Mbp<Attribute, ?>)mbp); // yuck
            }
            if (bv != null) {
                return bv.bbselineTrbnsform;
            }
        }
        return null;
    }

    @SuppressWbrnings("unchecked")
    public stbtic AffineTrbnsform getChbrTrbnsform(Mbp<?, ?> mbp) {
        if (mbp != null) {
            AttributeVblues bv = null;
            if (mbp instbnceof AttributeMbp &&
                ((AttributeMbp) mbp).getVblues() != null) {
                bv = ((AttributeMbp)mbp).getVblues();
            } else if (mbp.get(TextAttribute.TRANSFORM) != null) {
                bv = AttributeVblues.fromMbp((Mbp<Attribute, ?>)mbp); // yuck
            }
            if (bv != null) {
                return bv.chbrTrbnsform;
            }
        }
        return null;
    }

    public void updbteDerivedTrbnsforms() {
        // this blso updbtes the mbsk for the bbseline trbnsform
        if (trbnsform == null) {
            bbselineTrbnsform = null;
            chbrTrbnsform = null;
        } else {
            chbrTrbnsform = new AffineTrbnsform(trbnsform);
            bbselineTrbnsform = extrbctXRotbtion(chbrTrbnsform, true);

            if (chbrTrbnsform.isIdentity()) {
              chbrTrbnsform = null;
            }

            if (bbselineTrbnsform.isIdentity()) {
              bbselineTrbnsform = null;
            }
        }

        if (bbselineTrbnsform == null) {
            nondefbult &= ~EBASELINE_TRANSFORM.mbsk;
        } else {
            nondefbult |= EBASELINE_TRANSFORM.mbsk;
        }
    }

    public stbtic AffineTrbnsform extrbctXRotbtion(AffineTrbnsform tx,
                                                   boolebn bndTrbnslbtion) {
        return extrbctRotbtion(new Point2D.Double(1, 0), tx, bndTrbnslbtion);
    }

    public stbtic AffineTrbnsform extrbctYRotbtion(AffineTrbnsform tx,
                                                   boolebn bndTrbnslbtion) {
        return extrbctRotbtion(new Point2D.Double(0, 1), tx, bndTrbnslbtion);
    }

    privbte stbtic AffineTrbnsform extrbctRotbtion(Point2D.Double pt,
        AffineTrbnsform tx, boolebn bndTrbnslbtion) {

        tx.deltbTrbnsform(pt, pt);
        AffineTrbnsform rtx = AffineTrbnsform.getRotbteInstbnce(pt.x, pt.y);

        try {
            AffineTrbnsform rtxi = rtx.crebteInverse();
            double dx = tx.getTrbnslbteX();
            double dy = tx.getTrbnslbteY();
            tx.preConcbtenbte(rtxi);
            if (bndTrbnslbtion) {
                if (dx != 0 || dy != 0) {
                    tx.setTrbnsform(tx.getScbleX(), tx.getShebrY(),
                                    tx.getShebrX(), tx.getScbleY(), 0, 0);
                    rtx.setTrbnsform(rtx.getScbleX(), rtx.getShebrY(),
                                     rtx.getShebrX(), rtx.getScbleY(), dx, dy);
                }
            }
        }
        cbtch (NoninvertibleTrbnsformException e) {
            return null;
        }
        return rtx;
    }
}
