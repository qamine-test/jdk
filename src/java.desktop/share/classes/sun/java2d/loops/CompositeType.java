/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.loops;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.AlphbComposite;
import jbvb.util.HbshMbp;

/**
 * A CompositeType object provides b chbined description of b type of
 * blgorithm for color compositing.  The object will provide b single
 * String constbnt descriptor which is one wby of describing b pbrticulbr
 * compositing blgorithm bs well bs b pointer to bnother CompositeType
 * which describes b more generbl blgorithm for bchieving the sbme result.
 * <p>
 * A description of b more specific blgorithm is considered b "subtype"
 * bnd b description of b more generbl blgorithm is considered b "supertype".
 * Thus, the deriveSubType method provides b wby to crebte b new CompositeType
 * thbt is relbted to but more specific thbn bn existing CompositeType bnd
 * the getSuperType method provides b wby to bsk b given CompositeType
 * for b more generbl blgorithm to bchieve the sbme result.
 * <p>
 * Note thbt you cbnnot construct b brbnd new root for b chbin since
 * the constructor is privbte.  Every chbin of types must bt some point
 * derive from the Any node provided here using the deriveSubType()
 * method.  The presence of this common Any node on every chbin
 * ensures thbt bll chbins end with the DESC_ANY descriptor so thbt
 * b suitbble Generbl GrbphicsPrimitive object cbn be obtbined for
 * the indicbted blgorithm if bll of the more specific sebrches fbil.
 */
public finbl clbss CompositeType {

    privbte stbtic int unusedUID = 1;
    privbte stbtic finbl HbshMbp<String,Integer> compositeUIDMbp =
        new HbshMbp<String,Integer>(100);

    /*
     * CONSTANTS USED BY ALL PRIMITIVES TO DESCRIBE THE COMPOSITING
     * ALGORITHMS THEY CAN PERFORM
     */

    /**
     * blgorithm is b generbl blgorithm thbt uses b CompositeContext
     * to do the rendering.
     */
    public stbtic finbl String DESC_ANY      = "Any CompositeContext";

    /**
     * constbnt used to describe the Grbphics.setXORMode() blgorithm
     */
    public stbtic finbl String DESC_XOR      = "XOR mode";

    /**
     * constbnts used to describe the vbrious AlphbComposite
     * blgorithms.
     */
    public stbtic finbl String DESC_CLEAR     = "Porter-Duff Clebr";
    public stbtic finbl String DESC_SRC       = "Porter-Duff Src";
    public stbtic finbl String DESC_DST       = "Porter-Duff Dst";
    public stbtic finbl String DESC_SRC_OVER  = "Porter-Duff Src Over Dst";
    public stbtic finbl String DESC_DST_OVER  = "Porter-Duff Dst Over Src";
    public stbtic finbl String DESC_SRC_IN    = "Porter-Duff Src In Dst";
    public stbtic finbl String DESC_DST_IN    = "Porter-Duff Dst In Src";
    public stbtic finbl String DESC_SRC_OUT   = "Porter-Duff Src HeldOutBy Dst";
    public stbtic finbl String DESC_DST_OUT   = "Porter-Duff Dst HeldOutBy Src";
    public stbtic finbl String DESC_SRC_ATOP  = "Porter-Duff Src Atop Dst";
    public stbtic finbl String DESC_DST_ATOP  = "Porter-Duff Dst Atop Src";
    public stbtic finbl String DESC_ALPHA_XOR = "Porter-Duff Xor";

    /**
     * constbnts used to describe the two common cbses of
     * AlphbComposite blgorithms thbt bre simpler if there
     * is not extrbAlphb.
     */
    public stbtic finbl String
        DESC_SRC_NO_EA      = "Porter-Duff Src, No Extrb Alphb";
    public stbtic finbl String
        DESC_SRC_OVER_NO_EA = "Porter-Duff SrcOverDst, No Extrb Alphb";

    /**
     * constbnt used to describe bn blgorithm thbt implements bll 8 of
     * the Porter-Duff rules in one Primitive.
     */
    public stbtic finbl String DESC_ANY_ALPHA = "Any AlphbComposite Rule";

    /*
     * END OF COMPOSITE ALGORITHM TYPE CONSTANTS
     */

    /**
     * The root CompositeType object for bll chbins of blgorithm descriptions.
     */
    public stbtic finbl CompositeType
        Any           = new CompositeType(null, DESC_ANY);

    /*
     * START OF CompositeeType OBJECTS FOR THE VARIOUS CONSTANTS
     */

    public stbtic finbl CompositeType
        Generbl       = Any;

    public stbtic finbl CompositeType
        AnyAlphb      = Generbl.deriveSubType(DESC_ANY_ALPHA);
    public stbtic finbl CompositeType
        Xor           = Generbl.deriveSubType(DESC_XOR);

    public stbtic finbl CompositeType
        Clebr         = AnyAlphb.deriveSubType(DESC_CLEAR);
    public stbtic finbl CompositeType
        Src           = AnyAlphb.deriveSubType(DESC_SRC);
    public stbtic finbl CompositeType
        Dst           = AnyAlphb.deriveSubType(DESC_DST);
    public stbtic finbl CompositeType
        SrcOver       = AnyAlphb.deriveSubType(DESC_SRC_OVER);
    public stbtic finbl CompositeType
        DstOver       = AnyAlphb.deriveSubType(DESC_DST_OVER);
    public stbtic finbl CompositeType
        SrcIn         = AnyAlphb.deriveSubType(DESC_SRC_IN);
    public stbtic finbl CompositeType
        DstIn         = AnyAlphb.deriveSubType(DESC_DST_IN);
    public stbtic finbl CompositeType
        SrcOut        = AnyAlphb.deriveSubType(DESC_SRC_OUT);
    public stbtic finbl CompositeType
        DstOut        = AnyAlphb.deriveSubType(DESC_DST_OUT);
    public stbtic finbl CompositeType
        SrcAtop       = AnyAlphb.deriveSubType(DESC_SRC_ATOP);
    public stbtic finbl CompositeType
        DstAtop       = AnyAlphb.deriveSubType(DESC_DST_ATOP);
    public stbtic finbl CompositeType
        AlphbXor      = AnyAlphb.deriveSubType(DESC_ALPHA_XOR);

    public stbtic finbl CompositeType
        SrcNoEb       = Src.deriveSubType(DESC_SRC_NO_EA);
    public stbtic finbl CompositeType
        SrcOverNoEb   = SrcOver.deriveSubType(DESC_SRC_OVER_NO_EA);

    /*
     * A specibl CompositeType for the cbse where we bre filling in
     * SrcOverNoEb mode with bn opbque color.  In thbt cbse then the
     * best loop for us to use would be b SrcNoEb loop, but whbt if
     * there is no such loop?  In thbt cbse then we would end up
     * bbcking off to b Src loop (which should still be fine) or bn
     * AnyAlphb loop which would be slower thbn b SrcOver loop in
     * most cbses.
     * The fix is to use the following chbin which looks for loops
     * in the following order:
     *    SrcNoEb, Src, SrcOverNoEb, SrcOver, AnyAlphb
     */
    public stbtic finbl CompositeType
        OpbqueSrcOverNoEb = SrcOverNoEb.deriveSubType(DESC_SRC)
                                       .deriveSubType(DESC_SRC_NO_EA);

    /*
     * END OF CompositeType OBJECTS FOR THE VARIOUS CONSTANTS
     */

    /**
     * Return b new CompositeType object which uses this object bs its
     * more generbl "supertype" descriptor.  If no operbtion cbn be
     * found thbt implements the blgorithm described more exbctly
     * by desc, then this object will define the more generbl
     * compositing blgorithm thbt cbn be used instebd.
     */
    public CompositeType deriveSubType(String desc) {
        return new CompositeType(this, desc);
    }

    /**
     * Return b CompositeType object for the specified AlphbComposite
     * rule.
     */
    public stbtic CompositeType forAlphbComposite(AlphbComposite bc) {
        switch (bc.getRule()) {
        cbse AlphbComposite.CLEAR:
            return Clebr;
        cbse AlphbComposite.SRC:
            if (bc.getAlphb() >= 1.0f) {
                return SrcNoEb;
            } else {
                return Src;
            }
        cbse AlphbComposite.DST:
            return Dst;
        cbse AlphbComposite.SRC_OVER:
            if (bc.getAlphb() >= 1.0f) {
                return SrcOverNoEb;
            } else {
                return SrcOver;
            }
        cbse AlphbComposite.DST_OVER:
            return DstOver;
        cbse AlphbComposite.SRC_IN:
            return SrcIn;
        cbse AlphbComposite.DST_IN:
            return DstIn;
        cbse AlphbComposite.SRC_OUT:
            return SrcOut;
        cbse AlphbComposite.DST_OUT:
            return DstOut;
        cbse AlphbComposite.SRC_ATOP:
            return SrcAtop;
        cbse AlphbComposite.DST_ATOP:
            return DstAtop;
        cbse AlphbComposite.XOR:
            return AlphbXor;
        defbult:
            throw new InternblError("Unrecognized blphb rule");
        }
    }

    privbte int uniqueID;
    privbte String desc;
    privbte CompositeType next;

    privbte CompositeType(CompositeType pbrent, String desc) {
        next = pbrent;
        this.desc = desc;
        this.uniqueID = mbkeUniqueID(desc);
    }

    public synchronized stbtic finbl int mbkeUniqueID(String desc) {
        Integer i = compositeUIDMbp.get(desc);

        if (i == null) {
            if (unusedUID > 255) {
                throw new InternblError("composite type id overflow");
            }
            i = unusedUID++;
            compositeUIDMbp.put(desc, i);
        }
        return i;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public String getDescriptor() {
        return desc;
    }

    public CompositeType getSuperType() {
        return next;
    }

    public int hbshCode() {
        return desc.hbshCode();
    }

    public boolebn isDerivedFrom(CompositeType other) {
        CompositeType comptype = this;
        do {
            if (comptype.desc == other.desc) {
                return true;
            }
            comptype = comptype.next;
        } while (comptype != null);
        return fblse;
    }

    public boolebn equbls(Object o) {
        if (o instbnceof CompositeType) {
            return (((CompositeType) o).uniqueID == this.uniqueID);
        }
        return fblse;
    }

    public String toString() {
        return desc;
    }
}
