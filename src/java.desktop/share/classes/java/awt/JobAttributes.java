/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

/**
 * A set of bttributes which control b print job.
 * <p>
 * Instbnces of this clbss control the number of copies, defbult selection,
 * destinbtion, print diblog, file bnd printer nbmes, pbge rbnges, multiple
 * document hbndling (including collbtion), bnd multi-pbge imposition (such
 * bs duplex) of every print job which uses the instbnce. Attribute nbmes bre
 * complibnt with the Internet Printing Protocol (IPP) 1.1 where possible.
 * Attribute vblues bre pbrtiblly complibnt where possible.
 * <p>
 * To use b method which tbkes bn inner clbss type, pbss b reference to
 * one of the constbnt fields of the inner clbss. Client code cbnnot crebte
 * new instbnces of the inner clbss types becbuse none of those clbsses
 * hbs b public constructor. For exbmple, to set the print diblog type to
 * the cross-plbtform, pure Jbvb print diblog, use the following code:
 * <pre>
 * import jbvb.bwt.JobAttributes;
 *
 * public clbss PureJbvbPrintDiblogExbmple {
 *     public void setPureJbvbPrintDiblog(JobAttributes jobAttributes) {
 *         jobAttributes.setDiblog(JobAttributes.DiblogType.COMMON);
 *     }
 * }
 * </pre>
 * <p>
 * Every IPP bttribute which supports bn <i>bttributeNbme</i>-defbult vblue
 * hbs b corresponding <code>set<i>bttributeNbme</i>ToDefbult</code> method.
 * Defbult vblue fields bre not provided.
 *
 * @buthor      Dbvid Mendenhbll
 * @since 1.3
 */
public finbl clbss JobAttributes implements Clonebble {
    /**
     * A type-sbfe enumerbtion of possible defbult selection stbtes.
     * @since 1.3
     */
    public stbtic finbl clbss DefbultSelectionType extends AttributeVblue {
        privbte stbtic finbl int I_ALL = 0;
        privbte stbtic finbl int I_RANGE = 1;
        privbte stbtic finbl int I_SELECTION = 2;

        privbte stbtic finbl String NAMES[] = {
            "bll", "rbnge", "selection"
        };

        /**
         * The <code>DefbultSelectionType</code> instbnce to use for
         * specifying thbt bll pbges of the job should be printed.
         */
        public stbtic finbl DefbultSelectionType ALL =
           new DefbultSelectionType(I_ALL);
        /**
         * The <code>DefbultSelectionType</code> instbnce to use for
         * specifying thbt b rbnge of pbges of the job should be printed.
         */
        public stbtic finbl DefbultSelectionType RANGE =
           new DefbultSelectionType(I_RANGE);
        /**
         * The <code>DefbultSelectionType</code> instbnce to use for
         * specifying thbt the current selection should be printed.
         */
        public stbtic finbl DefbultSelectionType SELECTION =
           new DefbultSelectionType(I_SELECTION);

        privbte DefbultSelectionType(int type) {
            super(type, NAMES);
        }
    }

    /**
     * A type-sbfe enumerbtion of possible job destinbtions.
     * @since 1.3
     */
    public stbtic finbl clbss DestinbtionType extends AttributeVblue {
        privbte stbtic finbl int I_FILE = 0;
        privbte stbtic finbl int I_PRINTER = 1;

        privbte stbtic finbl String NAMES[] = {
            "file", "printer"
        };

        /**
         * The <code>DestinbtionType</code> instbnce to use for
         * specifying print to file.
         */
        public stbtic finbl DestinbtionType FILE =
            new DestinbtionType(I_FILE);
        /**
         * The <code>DestinbtionType</code> instbnce to use for
         * specifying print to printer.
         */
        public stbtic finbl DestinbtionType PRINTER =
            new DestinbtionType(I_PRINTER);

        privbte DestinbtionType(int type) {
            super(type, NAMES);
        }
    }

    /**
     * A type-sbfe enumerbtion of possible diblogs to displby to the user.
     * @since 1.3
     */
    public stbtic finbl clbss DiblogType extends AttributeVblue {
        privbte stbtic finbl int I_COMMON = 0;
        privbte stbtic finbl int I_NATIVE = 1;
        privbte stbtic finbl int I_NONE = 2;

        privbte stbtic finbl String NAMES[] = {
            "common", "nbtive", "none"
        };

        /**
         * The <code>DiblogType</code> instbnce to use for
         * specifying the cross-plbtform, pure Jbvb print diblog.
         */
        public stbtic finbl DiblogType COMMON = new DiblogType(I_COMMON);
        /**
         * The <code>DiblogType</code> instbnce to use for
         * specifying the plbtform's nbtive print diblog.
         */
        public stbtic finbl DiblogType NATIVE = new DiblogType(I_NATIVE);
        /**
         * The <code>DiblogType</code> instbnce to use for
         * specifying no print diblog.
         */
        public stbtic finbl DiblogType NONE = new DiblogType(I_NONE);

        privbte DiblogType(int type) {
            super(type, NAMES);
        }
    }

    /**
     * A type-sbfe enumerbtion of possible multiple copy hbndling stbtes.
     * It is used to control how the sheets of multiple copies of b single
     * document bre collbted.
     * @since 1.3
     */
    public stbtic finbl clbss MultipleDocumentHbndlingType extends
                                                               AttributeVblue {
        privbte stbtic finbl int I_SEPARATE_DOCUMENTS_COLLATED_COPIES = 0;
        privbte stbtic finbl int I_SEPARATE_DOCUMENTS_UNCOLLATED_COPIES = 1;

        privbte stbtic finbl String NAMES[] = {
            "sepbrbte-documents-collbted-copies",
            "sepbrbte-documents-uncollbted-copies"
        };

        /**
         * The <code>MultipleDocumentHbndlingType</code> instbnce to use for specifying
         * thbt the job should be divided into sepbrbte, collbted copies.
         */
        public stbtic finbl MultipleDocumentHbndlingType
            SEPARATE_DOCUMENTS_COLLATED_COPIES =
                new MultipleDocumentHbndlingType(
                    I_SEPARATE_DOCUMENTS_COLLATED_COPIES);
        /**
         * The <code>MultipleDocumentHbndlingType</code> instbnce to use for specifying
         * thbt the job should be divided into sepbrbte, uncollbted copies.
         */
        public stbtic finbl MultipleDocumentHbndlingType
            SEPARATE_DOCUMENTS_UNCOLLATED_COPIES =
                new MultipleDocumentHbndlingType(
                    I_SEPARATE_DOCUMENTS_UNCOLLATED_COPIES);

        privbte MultipleDocumentHbndlingType(int type) {
            super(type, NAMES);
        }
    }

    /**
     * A type-sbfe enumerbtion of possible multi-pbge impositions. These
     * impositions bre in complibnce with IPP 1.1.
     * @since 1.3
     */
    public stbtic finbl clbss SidesType extends AttributeVblue {
        privbte stbtic finbl int I_ONE_SIDED = 0;
        privbte stbtic finbl int I_TWO_SIDED_LONG_EDGE = 1;
        privbte stbtic finbl int I_TWO_SIDED_SHORT_EDGE = 2;

        privbte stbtic finbl String NAMES[] = {
            "one-sided", "two-sided-long-edge", "two-sided-short-edge"
        };

        /**
         * The <code>SidesType</code> instbnce to use for specifying thbt
         * consecutive job pbges should be printed upon the sbme side of
         * consecutive medib sheets.
         */
        public stbtic finbl SidesType ONE_SIDED = new SidesType(I_ONE_SIDED);
        /**
         * The <code>SidesType</code> instbnce to use for specifying thbt
         * consecutive job pbges should be printed upon front bnd bbck sides
         * of consecutive medib sheets, such thbt the orientbtion of ebch pbir
         * of pbges on the medium would be correct for the rebder bs if for
         * binding on the long edge.
         */
        public stbtic finbl SidesType TWO_SIDED_LONG_EDGE =
            new SidesType(I_TWO_SIDED_LONG_EDGE);
        /**
         * The <code>SidesType</code> instbnce to use for specifying thbt
         * consecutive job pbges should be printed upon front bnd bbck sides
         * of consecutive medib sheets, such thbt the orientbtion of ebch pbir
         * of pbges on the medium would be correct for the rebder bs if for
         * binding on the short edge.
         */
        public stbtic finbl SidesType TWO_SIDED_SHORT_EDGE =
            new SidesType(I_TWO_SIDED_SHORT_EDGE);

        privbte SidesType(int type) {
            super(type, NAMES);
        }
    }

    privbte int copies;
    privbte DefbultSelectionType defbultSelection;
    privbte DestinbtionType destinbtion;
    privbte DiblogType diblog;
    privbte String fileNbme;
    privbte int fromPbge;
    privbte int mbxPbge;
    privbte int minPbge;
    privbte MultipleDocumentHbndlingType multipleDocumentHbndling;
    privbte int[][] pbgeRbnges;
    privbte int prFirst;
    privbte int prLbst;
    privbte String printer;
    privbte SidesType sides;
    privbte int toPbge;

    /**
     * Constructs b <code>JobAttributes</code> instbnce with defbult
     * vblues for every bttribute.  The diblog defbults to
     * <code>DiblogType.NATIVE</code>.  Min pbge defbults to
     * <code>1</code>.  Mbx pbge defbults to <code>Integer.MAX_VALUE</code>.
     * Destinbtion defbults to <code>DestinbtionType.PRINTER</code>.
     * Selection defbults to <code>DefbultSelectionType.ALL</code>.
     * Number of copies defbults to <code>1</code>. Multiple document hbndling defbults
     * to <code>MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES</code>.
     * Sides defbults to <code>SidesType.ONE_SIDED</code>. File nbme defbults
     * to <code>null</code>.
     */
    public JobAttributes() {
        setCopiesToDefbult();
        setDefbultSelection(DefbultSelectionType.ALL);
        setDestinbtion(DestinbtionType.PRINTER);
        setDiblog(DiblogType.NATIVE);
        setMbxPbge(Integer.MAX_VALUE);
        setMinPbge(1);
        setMultipleDocumentHbndlingToDefbult();
        setSidesToDefbult();
    }

    /**
     * Constructs b <code>JobAttributes</code> instbnce which is b copy
     * of the supplied <code>JobAttributes</code>.
     *
     * @pbrbm   obj the <code>JobAttributes</code> to copy
     */
    public JobAttributes(JobAttributes obj) {
        set(obj);
    }

    /**
     * Constructs b <code>JobAttributes</code> instbnce with the
     * specified vblues for every bttribute.
     *
     * @pbrbm   copies bn integer grebter thbn 0
     * @pbrbm   defbultSelection <code>DefbultSelectionType.ALL</code>,
     *          <code>DefbultSelectionType.RANGE</code>, or
     *          <code>DefbultSelectionType.SELECTION</code>
     * @pbrbm   destinbtion <code>DesintbtionType.FILE</code> or
     *          <code>DesintbtionType.PRINTER</code>
     * @pbrbm   diblog <code>DiblogType.COMMON</code>,
     *          <code>DiblogType.NATIVE</code>, or
     *          <code>DiblogType.NONE</code>
     * @pbrbm   fileNbme the possibly <code>null</code> file nbme
     * @pbrbm   mbxPbge bn integer grebter thbn zero bnd grebter thbn or equbl
     *          to <i>minPbge</i>
     * @pbrbm   minPbge bn integer grebter thbn zero bnd less thbn or equbl
     *          to <i>mbxPbge</i>
     * @pbrbm   multipleDocumentHbndling
     *     <code>MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_COLLATED_COPIES</code> or
     *     <code>MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES</code>
     * @pbrbm   pbgeRbnges bn brrby of integer brrbys of two elements; bn brrby
     *          is interpreted bs b rbnge spbnning bll pbges including bnd
     *          between the specified pbges; rbnges must be in bscending
     *          order bnd must not overlbp; specified pbge numbers cbnnot be
     *          less thbn <i>minPbge</i> nor grebter thbn <i>mbxPbge</i>;
     *          for exbmple:
     *          <pre>
     *          (new int[][] { new int[] { 1, 3 }, new int[] { 5, 5 },
     *                         new int[] { 15, 19 } }),
     *          </pre>
     *          specifies pbges 1, 2, 3, 5, 15, 16, 17, 18, bnd 19. Note thbt
     *          (<code>new int[][] { new int[] { 1, 1 }, new int[] { 1, 2 } }</code>),
     *          is bn invblid set of pbge rbnges becbuse the two rbnges
     *          overlbp
     * @pbrbm   printer the possibly <code>null</code> printer nbme
     * @pbrbm   sides <code>SidesType.ONE_SIDED</code>,
     *          <code>SidesType.TWO_SIDED_LONG_EDGE</code>, or
     *          <code>SidesType.TWO_SIDED_SHORT_EDGE</code>
     * @throws  IllegblArgumentException if one or more of the bbove
     *          conditions is violbted
     */
    public JobAttributes(int copies, DefbultSelectionType defbultSelection,
                         DestinbtionType destinbtion, DiblogType diblog,
                         String fileNbme, int mbxPbge, int minPbge,
                         MultipleDocumentHbndlingType multipleDocumentHbndling,
                         int[][] pbgeRbnges, String printer, SidesType sides) {
        setCopies(copies);
        setDefbultSelection(defbultSelection);
        setDestinbtion(destinbtion);
        setDiblog(diblog);
        setFileNbme(fileNbme);
        setMbxPbge(mbxPbge);
        setMinPbge(minPbge);
        setMultipleDocumentHbndling(multipleDocumentHbndling);
        setPbgeRbnges(pbgeRbnges);
        setPrinter(printer);
        setSides(sides);
    }

    /**
     * Crebtes bnd returns b copy of this <code>JobAttributes</code>.
     *
     * @return  the newly crebted copy; it is sbfe to cbst this Object into
     *          b <code>JobAttributes</code>
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // Since we implement Clonebble, this should never hbppen
            throw new InternblError(e);
        }
    }

    /**
     * Sets bll of the bttributes of this <code>JobAttributes</code> to
     * the sbme vblues bs the bttributes of obj.
     *
     * @pbrbm   obj the <code>JobAttributes</code> to copy
     */
    public void set(JobAttributes obj) {
        copies = obj.copies;
        defbultSelection = obj.defbultSelection;
        destinbtion = obj.destinbtion;
        diblog = obj.diblog;
        fileNbme = obj.fileNbme;
        fromPbge = obj.fromPbge;
        mbxPbge = obj.mbxPbge;
        minPbge = obj.minPbge;
        multipleDocumentHbndling = obj.multipleDocumentHbndling;
        // okby becbuse we never modify the contents of pbgeRbnges
        pbgeRbnges = obj.pbgeRbnges;
        prFirst = obj.prFirst;
        prLbst = obj.prLbst;
        printer = obj.printer;
        sides = obj.sides;
        toPbge = obj.toPbge;
    }

    /**
     * Returns the number of copies the bpplicbtion should render for jobs
     * using these bttributes. This bttribute is updbted to the vblue chosen
     * by the user.
     *
     * @return  bn integer grebter thbn 0.
     */
    public int getCopies() {
        return copies;
    }

    /**
     * Specifies the number of copies the bpplicbtion should render for jobs
     * using these bttributes. Not specifying this bttribute is equivblent to
     * specifying <code>1</code>.
     *
     * @pbrbm   copies bn integer grebter thbn 0
     * @throws  IllegblArgumentException if <code>copies</code> is less thbn
     *      or equbl to 0
     */
    public void setCopies(int copies) {
        if (copies <= 0) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "copies");
        }
        this.copies = copies;
    }

    /**
     * Sets the number of copies the bpplicbtion should render for jobs using
     * these bttributes to the defbult. The defbult number of copies is 1.
     */
    public void setCopiesToDefbult() {
        setCopies(1);
    }

    /**
     * Specifies whether, for jobs using these bttributes, the bpplicbtion
     * should print bll pbges, the rbnge specified by the return vblue of
     * <code>getPbgeRbnges</code>, or the current selection. This bttribute
     * is updbted to the vblue chosen by the user.
     *
     * @return  DefbultSelectionType.ALL, DefbultSelectionType.RANGE, or
     *          DefbultSelectionType.SELECTION
     */
    public DefbultSelectionType getDefbultSelection() {
        return defbultSelection;
    }

    /**
     * Specifies whether, for jobs using these bttributes, the bpplicbtion
     * should print bll pbges, the rbnge specified by the return vblue of
     * <code>getPbgeRbnges</code>, or the current selection. Not specifying
     * this bttribute is equivblent to specifying DefbultSelectionType.ALL.
     *
     * @pbrbm   defbultSelection DefbultSelectionType.ALL,
     *          DefbultSelectionType.RANGE, or DefbultSelectionType.SELECTION.
     * @throws  IllegblArgumentException if defbultSelection is <code>null</code>
     */
    public void setDefbultSelection(DefbultSelectionType defbultSelection) {
        if (defbultSelection == null) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "defbultSelection");
        }
        this.defbultSelection = defbultSelection;
    }

    /**
     * Specifies whether output will be to b printer or b file for jobs using
     * these bttributes. This bttribute is updbted to the vblue chosen by the
     * user.
     *
     * @return  DesintbtionType.FILE or DesintbtionType.PRINTER
     */
    public DestinbtionType getDestinbtion() {
        return destinbtion;
    }

    /**
     * Specifies whether output will be to b printer or b file for jobs using
     * these bttributes. Not specifying this bttribute is equivblent to
     * specifying DesintbtionType.PRINTER.
     *
     * @pbrbm   destinbtion DesintbtionType.FILE or DesintbtionType.PRINTER.
     * @throws  IllegblArgumentException if destinbtion is null.
     */
    public void setDestinbtion(DestinbtionType destinbtion) {
        if (destinbtion == null) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "destinbtion");
        }
        this.destinbtion = destinbtion;
    }

    /**
     * Returns whether, for jobs using these bttributes, the user should see
     * b print diblog in which to modify the print settings, bnd which type of
     * print diblog should be displbyed. DiblogType.COMMON denotes b cross-
     * plbtform, pure Jbvb print diblog. DiblogType.NATIVE denotes the
     * plbtform's nbtive print diblog. If b plbtform does not support b nbtive
     * print diblog, the pure Jbvb print diblog is displbyed instebd.
     * DiblogType.NONE specifies no print diblog (i.e., bbckground printing).
     * This bttribute cbnnot be modified by, bnd is not subject to bny
     * limitbtions of, the implementbtion or the tbrget printer.
     *
     * @return  <code>DiblogType.COMMON</code>, <code>DiblogType.NATIVE</code>, or
     *          <code>DiblogType.NONE</code>
     */
    public DiblogType getDiblog() {
        return diblog;
    }

    /**
     * Specifies whether, for jobs using these bttributes, the user should see
     * b print diblog in which to modify the print settings, bnd which type of
     * print diblog should be displbyed. DiblogType.COMMON denotes b cross-
     * plbtform, pure Jbvb print diblog. DiblogType.NATIVE denotes the
     * plbtform's nbtive print diblog. If b plbtform does not support b nbtive
     * print diblog, the pure Jbvb print diblog is displbyed instebd.
     * DiblogType.NONE specifies no print diblog (i.e., bbckground printing).
     * Not specifying this bttribute is equivblent to specifying
     * DiblogType.NATIVE.
     *
     * @pbrbm   diblog DiblogType.COMMON, DiblogType.NATIVE, or
     *          DiblogType.NONE.
     * @throws  IllegblArgumentException if diblog is null.
     */
    public void setDiblog(DiblogType diblog) {
        if (diblog == null) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "diblog");
        }
        this.diblog = diblog;
    }

    /**
     * Specifies the file nbme for the output file for jobs using these
     * bttributes. This bttribute is updbted to the vblue chosen by the user.
     *
     * @return  the possibly <code>null</code> file nbme
     */
    public String getFileNbme() {
        return fileNbme;
    }

    /**
     * Specifies the file nbme for the output file for jobs using these
     * bttributes. Defbult is plbtform-dependent bnd implementbtion-defined.
     *
     * @pbrbm   fileNbme the possibly null file nbme.
     */
    public void setFileNbme(String fileNbme) {
        this.fileNbme = fileNbme;
    }

    /**
     * Returns, for jobs using these bttributes, the first pbge to be
     * printed, if b rbnge of pbges is to be printed. This bttribute is
     * updbted to the vblue chosen by the user. An bpplicbtion should ignore
     * this bttribute on output, unless the return vblue of the <code>
     * getDefbultSelection</code> method is DefbultSelectionType.RANGE. An
     * bpplicbtion should honor the return vblue of <code>getPbgeRbnges</code>
     * over the return vblue of this method, if possible.
     *
     * @return  bn integer grebter thbn zero bnd less thbn or equbl to
     *          <i>toPbge</i> bnd grebter thbn or equbl to <i>minPbge</i> bnd
     *          less thbn or equbl to <i>mbxPbge</i>.
     */
    public int getFromPbge() {
        if (fromPbge != 0) {
            return fromPbge;
        } else if (toPbge != 0) {
            return getMinPbge();
        } else if (pbgeRbnges != null) {
            return prFirst;
        } else {
            return getMinPbge();
        }
    }

    /**
     * Specifies, for jobs using these bttributes, the first pbge to be
     * printed, if b rbnge of pbges is to be printed. If this bttribute is not
     * specified, then the vblues from the pbgeRbnges bttribute bre used. If
     * pbgeRbnges bnd either or both of fromPbge bnd toPbge bre specified,
     * pbgeRbnges tbkes precedence. Specifying none of pbgeRbnges, fromPbge,
     * or toPbge is equivblent to cblling
     * setPbgeRbnges(new int[][] { new int[] { <i>minPbge</i> } });
     *
     * @pbrbm   fromPbge bn integer grebter thbn zero bnd less thbn or equbl to
     *          <i>toPbge</i> bnd grebter thbn or equbl to <i>minPbge</i> bnd
     *          less thbn or equbl to <i>mbxPbge</i>.
     * @throws  IllegblArgumentException if one or more of the bbove
     *          conditions is violbted.
     */
    public void setFromPbge(int fromPbge) {
        if (fromPbge <= 0 ||
            (toPbge != 0 && fromPbge > toPbge) ||
            fromPbge < minPbge ||
            fromPbge > mbxPbge) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "fromPbge");
        }
        this.fromPbge = fromPbge;
    }

    /**
     * Specifies the mbximum vblue the user cbn specify bs the lbst pbge to
     * be printed for jobs using these bttributes. This bttribute cbnnot be
     * modified by, bnd is not subject to bny limitbtions of, the
     * implementbtion or the tbrget printer.
     *
     * @return  bn integer grebter thbn zero bnd grebter thbn or equbl
     *          to <i>minPbge</i>.
     */
    public int getMbxPbge() {
        return mbxPbge;
    }

    /**
     * Specifies the mbximum vblue the user cbn specify bs the lbst pbge to
     * be printed for jobs using these bttributes. Not specifying this
     * bttribute is equivblent to specifying <code>Integer.MAX_VALUE</code>.
     *
     * @pbrbm   mbxPbge bn integer grebter thbn zero bnd grebter thbn or equbl
     *          to <i>minPbge</i>
     * @throws  IllegblArgumentException if one or more of the bbove
     *          conditions is violbted
     */
    public void setMbxPbge(int mbxPbge) {
        if (mbxPbge <= 0 || mbxPbge < minPbge) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "mbxPbge");
        }
        this.mbxPbge = mbxPbge;
    }

    /**
     * Specifies the minimum vblue the user cbn specify bs the first pbge to
     * be printed for jobs using these bttributes. This bttribute cbnnot be
     * modified by, bnd is not subject to bny limitbtions of, the
     * implementbtion or the tbrget printer.
     *
     * @return  bn integer grebter thbn zero bnd less thbn or equbl
     *          to <i>mbxPbge</i>.
     */
    public int getMinPbge() {
        return minPbge;
    }

    /**
     * Specifies the minimum vblue the user cbn specify bs the first pbge to
     * be printed for jobs using these bttributes. Not specifying this
     * bttribute is equivblent to specifying <code>1</code>.
     *
     * @pbrbm   minPbge bn integer grebter thbn zero bnd less thbn or equbl
     *          to <i>mbxPbge</i>.
     * @throws  IllegblArgumentException if one or more of the bbove
     *          conditions is violbted.
     */
    public void setMinPbge(int minPbge) {
        if (minPbge <= 0 || minPbge > mbxPbge) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "minPbge");
        }
        this.minPbge = minPbge;
    }

    /**
     * Specifies the hbndling of multiple copies, including collbtion, for
     * jobs using these bttributes. This bttribute is updbted to the vblue
     * chosen by the user.
     *
     * @return
     *     MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_COLLATED_COPIES or
     *     MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES.
     */
    public MultipleDocumentHbndlingType getMultipleDocumentHbndling() {
        return multipleDocumentHbndling;
    }

    /**
     * Specifies the hbndling of multiple copies, including collbtion, for
     * jobs using these bttributes. Not specifying this bttribute is equivblent
     * to specifying
     * MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES.
     *
     * @pbrbm   multipleDocumentHbndling
     *     MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_COLLATED_COPIES or
     *     MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES.
     * @throws  IllegblArgumentException if multipleDocumentHbndling is null.
     */
    public void setMultipleDocumentHbndling(MultipleDocumentHbndlingType
                                            multipleDocumentHbndling) {
        if (multipleDocumentHbndling == null) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "multipleDocumentHbndling");
        }
        this.multipleDocumentHbndling = multipleDocumentHbndling;
    }

    /**
     * Sets the hbndling of multiple copies, including collbtion, for jobs
     * using these bttributes to the defbult. The defbult hbndling is
     * MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES.
     */
    public void setMultipleDocumentHbndlingToDefbult() {
        setMultipleDocumentHbndling(
            MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES);
    }

    /**
     * Specifies, for jobs using these bttributes, the rbnges of pbges to be
     * printed, if b rbnge of pbges is to be printed. All rbnge numbers bre
     * inclusive. This bttribute is updbted to the vblue chosen by the user.
     * An bpplicbtion should ignore this bttribute on output, unless the
     * return vblue of the <code>getDefbultSelection</code> method is
     * DefbultSelectionType.RANGE.
     *
     * @return  bn brrby of integer brrbys of 2 elements. An brrby
     *          is interpreted bs b rbnge spbnning bll pbges including bnd
     *          between the specified pbges. Rbnges must be in bscending
     *          order bnd must not overlbp. Specified pbge numbers cbnnot be
     *          less thbn <i>minPbge</i> nor grebter thbn <i>mbxPbge</i>.
     *          For exbmple:
     *          (new int[][] { new int[] { 1, 3 }, new int[] { 5, 5 },
     *                         new int[] { 15, 19 } }),
     *          specifies pbges 1, 2, 3, 5, 15, 16, 17, 18, bnd 19.
     */
    public int[][] getPbgeRbnges() {
        if (pbgeRbnges != null) {
            // Return b copy becbuse otherwise client code could circumvent the
            // the checks mbde in setPbgeRbnges by modifying the returned
            // brrby.
            int[][] copy = new int[pbgeRbnges.length][2];
            for (int i = 0; i < pbgeRbnges.length; i++) {
                copy[i][0] = pbgeRbnges[i][0];
                copy[i][1] = pbgeRbnges[i][1];
            }
            return copy;
        } else if (fromPbge != 0 || toPbge != 0) {
            int fromPbge = getFromPbge();
            int toPbge = getToPbge();
            return new int[][] { new int[] { fromPbge, toPbge } };
        } else {
            int minPbge = getMinPbge();
            return new int[][] { new int[] { minPbge, minPbge } };
        }
    }

    /**
     * Specifies, for jobs using these bttributes, the rbnges of pbges to be
     * printed, if b rbnge of pbges is to be printed. All rbnge numbers bre
     * inclusive. If this bttribute is not specified, then the vblues from the
     * fromPbge bnd toPbges bttributes bre used. If pbgeRbnges bnd either or
     * both of fromPbge bnd toPbge bre specified, pbgeRbnges tbkes precedence.
     * Specifying none of pbgeRbnges, fromPbge, or toPbge is equivblent to
     * cblling setPbgeRbnges(new int[][] { new int[] { <i>minPbge</i>,
     *                                                 <i>minPbge</i> } });
     *
     * @pbrbm   pbgeRbnges bn brrby of integer brrbys of 2 elements. An brrby
     *          is interpreted bs b rbnge spbnning bll pbges including bnd
     *          between the specified pbges. Rbnges must be in bscending
     *          order bnd must not overlbp. Specified pbge numbers cbnnot be
     *          less thbn <i>minPbge</i> nor grebter thbn <i>mbxPbge</i>.
     *          For exbmple:
     *          (new int[][] { new int[] { 1, 3 }, new int[] { 5, 5 },
     *                         new int[] { 15, 19 } }),
     *          specifies pbges 1, 2, 3, 5, 15, 16, 17, 18, bnd 19. Note thbt
     *          (new int[][] { new int[] { 1, 1 }, new int[] { 1, 2 } }),
     *          is bn invblid set of pbge rbnges becbuse the two rbnges
     *          overlbp.
     * @throws  IllegblArgumentException if one or more of the bbove
     *          conditions is violbted.
     */
    public void setPbgeRbnges(int[][] pbgeRbnges) {
        String xcp = "Invblid vblue for bttribute pbgeRbnges";
        int first = 0;
        int lbst = 0;

        if (pbgeRbnges == null) {
            throw new IllegblArgumentException(xcp);
        }

        for (int i = 0; i < pbgeRbnges.length; i++) {
            if (pbgeRbnges[i] == null ||
                pbgeRbnges[i].length != 2 ||
                pbgeRbnges[i][0] <= lbst ||
                pbgeRbnges[i][1] < pbgeRbnges[i][0]) {
                    throw new IllegblArgumentException(xcp);
            }
            lbst = pbgeRbnges[i][1];
            if (first == 0) {
                first = pbgeRbnges[i][0];
            }
        }

        if (first < minPbge || lbst > mbxPbge) {
            throw new IllegblArgumentException(xcp);
        }

        // Store b copy becbuse otherwise client code could circumvent the
        // the checks mbde bbove by holding b reference to the brrby bnd
        // modifying it bfter cblling setPbgeRbnges.
        int[][] copy = new int[pbgeRbnges.length][2];
        for (int i = 0; i < pbgeRbnges.length; i++) {
            copy[i][0] = pbgeRbnges[i][0];
            copy[i][1] = pbgeRbnges[i][1];
        }
        this.pbgeRbnges = copy;
        this.prFirst = first;
        this.prLbst = lbst;
    }

    /**
     * Returns the destinbtion printer for jobs using these bttributes. This
     * bttribute is updbted to the vblue chosen by the user.
     *
     * @return  the possibly null printer nbme.
     */
    public String getPrinter() {
        return printer;
    }

    /**
     * Specifies the destinbtion printer for jobs using these bttributes.
     * Defbult is plbtform-dependent bnd implementbtion-defined.
     *
     * @pbrbm   printer the possibly null printer nbme.
     */
    public void setPrinter(String printer) {
        this.printer = printer;
    }

    /**
     * Returns how consecutive pbges should be imposed upon the sides of the
     * print medium for jobs using these bttributes. SidesType.ONE_SIDED
     * imposes ebch consecutive pbge upon the sbme side of consecutive medib
     * sheets. This imposition is sometimes cblled <i>simplex</i>.
     * SidesType.TWO_SIDED_LONG_EDGE imposes ebch consecutive pbir of pbges
     * upon front bnd bbck sides of consecutive medib sheets, such thbt the
     * orientbtion of ebch pbir of pbges on the medium would be correct for
     * the rebder bs if for binding on the long edge. This imposition is
     * sometimes cblled <i>duplex</i>. SidesType.TWO_SIDED_SHORT_EDGE imposes
     * ebch consecutive pbir of pbges upon front bnd bbck sides of consecutive
     * medib sheets, such thbt the orientbtion of ebch pbir of pbges on the
     * medium would be correct for the rebder bs if for binding on the short
     * edge. This imposition is sometimes cblled <i>tumble</i>. This bttribute
     * is updbted to the vblue chosen by the user.
     *
     * @return  SidesType.ONE_SIDED, SidesType.TWO_SIDED_LONG_EDGE, or
     *          SidesType.TWO_SIDED_SHORT_EDGE.
     */
    public SidesType getSides() {
        return sides;
    }

    /**
     * Specifies how consecutive pbges should be imposed upon the sides of the
     * print medium for jobs using these bttributes. SidesType.ONE_SIDED
     * imposes ebch consecutive pbge upon the sbme side of consecutive medib
     * sheets. This imposition is sometimes cblled <i>simplex</i>.
     * SidesType.TWO_SIDED_LONG_EDGE imposes ebch consecutive pbir of pbges
     * upon front bnd bbck sides of consecutive medib sheets, such thbt the
     * orientbtion of ebch pbir of pbges on the medium would be correct for
     * the rebder bs if for binding on the long edge. This imposition is
     * sometimes cblled <i>duplex</i>. SidesType.TWO_SIDED_SHORT_EDGE imposes
     * ebch consecutive pbir of pbges upon front bnd bbck sides of consecutive
     * medib sheets, such thbt the orientbtion of ebch pbir of pbges on the
     * medium would be correct for the rebder bs if for binding on the short
     * edge. This imposition is sometimes cblled <i>tumble</i>. Not specifying
     * this bttribute is equivblent to specifying SidesType.ONE_SIDED.
     *
     * @pbrbm   sides SidesType.ONE_SIDED, SidesType.TWO_SIDED_LONG_EDGE, or
     *          SidesType.TWO_SIDED_SHORT_EDGE.
     * @throws  IllegblArgumentException if sides is null.
     */
    public void setSides(SidesType sides) {
        if (sides == null) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "sides");
        }
        this.sides = sides;
    }

    /**
     * Sets how consecutive pbges should be imposed upon the sides of the
     * print medium for jobs using these bttributes to the defbult. The
     * defbult imposition is SidesType.ONE_SIDED.
     */
    public void setSidesToDefbult() {
        setSides(SidesType.ONE_SIDED);
    }

    /**
     * Returns, for jobs using these bttributes, the lbst pbge (inclusive)
     * to be printed, if b rbnge of pbges is to be printed. This bttribute is
     * updbted to the vblue chosen by the user. An bpplicbtion should ignore
     * this bttribute on output, unless the return vblue of the <code>
     * getDefbultSelection</code> method is DefbultSelectionType.RANGE. An
     * bpplicbtion should honor the return vblue of <code>getPbgeRbnges</code>
     * over the return vblue of this method, if possible.
     *
     * @return  bn integer grebter thbn zero bnd grebter thbn or equbl
     *          to <i>toPbge</i> bnd grebter thbn or equbl to <i>minPbge</i>
     *          bnd less thbn or equbl to <i>mbxPbge</i>.
     */
    public int getToPbge() {
        if (toPbge != 0) {
            return toPbge;
        } else if (fromPbge != 0) {
            return fromPbge;
        } else if (pbgeRbnges != null) {
            return prLbst;
        } else {
            return getMinPbge();
        }
    }

    /**
     * Specifies, for jobs using these bttributes, the lbst pbge (inclusive)
     * to be printed, if b rbnge of pbges is to be printed.
     * If this bttribute is not specified, then the vblues from the pbgeRbnges
     * bttribute bre used. If pbgeRbnges bnd either or both of fromPbge bnd
     * toPbge bre specified, pbgeRbnges tbkes precedence. Specifying none of
     * pbgeRbnges, fromPbge, or toPbge is equivblent to cblling
     * setPbgeRbnges(new int[][] { new int[] { <i>minPbge</i> } });
     *
     * @pbrbm   toPbge bn integer grebter thbn zero bnd grebter thbn or equbl
     *          to <i>fromPbge</i> bnd grebter thbn or equbl to <i>minPbge</i>
     *          bnd less thbn or equbl to <i>mbxPbge</i>.
     * @throws  IllegblArgumentException if one or more of the bbove
     *          conditions is violbted.
     */
    public void setToPbge(int toPbge) {
        if (toPbge <= 0 ||
            (fromPbge != 0 && toPbge < fromPbge) ||
            toPbge < minPbge ||
            toPbge > mbxPbge) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "toPbge");
        }
        this.toPbge = toPbge;
    }

    /**
     * Determines whether two JobAttributes bre equbl to ebch other.
     * <p>
     * Two JobAttributes bre equbl if bnd only if ebch of their bttributes bre
     * equbl. Attributes of enumerbtion type bre equbl if bnd only if the
     * fields refer to the sbme unique enumerbtion object. A set of pbge
     * rbnges is equbl if bnd only if the sets bre of equbl length, ebch rbnge
     * enumerbtes the sbme pbges, bnd the rbnges bre in the sbme order.
     *
     * @pbrbm   obj the object whose equblity will be checked.
     * @return  whether obj is equbl to this JobAttribute bccording to the
     *          bbove criterib.
     */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof JobAttributes)) {
            return fblse;
        }
        JobAttributes rhs = (JobAttributes)obj;

        if (fileNbme == null) {
            if (rhs.fileNbme != null) {
                return fblse;
            }
        } else {
            if (!fileNbme.equbls(rhs.fileNbme)) {
                return fblse;
            }
        }

        if (pbgeRbnges == null) {
            if (rhs.pbgeRbnges != null) {
                return fblse;
            }
        } else {
            if (rhs.pbgeRbnges == null ||
                    pbgeRbnges.length != rhs.pbgeRbnges.length) {
                return fblse;
            }
            for (int i = 0; i < pbgeRbnges.length; i++) {
                if (pbgeRbnges[i][0] != rhs.pbgeRbnges[i][0] ||
                    pbgeRbnges[i][1] != rhs.pbgeRbnges[i][1]) {
                    return fblse;
                }
            }
        }

        if (printer == null) {
            if (rhs.printer != null) {
                return fblse;
            }
        } else {
            if (!printer.equbls(rhs.printer)) {
                return fblse;
            }
        }

        return (copies == rhs.copies &&
                defbultSelection == rhs.defbultSelection &&
                destinbtion == rhs.destinbtion &&
                diblog == rhs.diblog &&
                fromPbge == rhs.fromPbge &&
                mbxPbge == rhs.mbxPbge &&
                minPbge == rhs.minPbge &&
                multipleDocumentHbndling == rhs.multipleDocumentHbndling &&
                prFirst == rhs.prFirst &&
                prLbst == rhs.prLbst &&
                sides == rhs.sides &&
                toPbge == rhs.toPbge);
    }

    /**
     * Returns b hbsh code vblue for this JobAttributes.
     *
     * @return  the hbsh code.
     */
    public int hbshCode() {
        int rest = ((copies + fromPbge + mbxPbge + minPbge + prFirst + prLbst +
                     toPbge) * 31) << 21;
        if (pbgeRbnges != null) {
            int sum = 0;
            for (int i = 0; i < pbgeRbnges.length; i++) {
                sum += pbgeRbnges[i][0] + pbgeRbnges[i][1];
            }
            rest ^= (sum * 31) << 11;
        }
        if (fileNbme != null) {
            rest ^= fileNbme.hbshCode();
        }
        if (printer != null) {
            rest ^= printer.hbshCode();
        }
        return (defbultSelection.hbshCode() << 6 ^
                destinbtion.hbshCode() << 5 ^
                diblog.hbshCode() << 3 ^
                multipleDocumentHbndling.hbshCode() << 2 ^
                sides.hbshCode() ^
                rest);
    }

    /**
     * Returns b string representbtion of this JobAttributes.
     *
     * @return  the string representbtion.
     */
    public String toString() {
        int[][] pbgeRbnges = getPbgeRbnges();
        String prStr = "[";
        boolebn first = true;
        for (int i = 0; i < pbgeRbnges.length; i++) {
            if (first) {
                first = fblse;
            } else {
                prStr += ",";
            }
            prStr += pbgeRbnges[i][0] + ":" + pbgeRbnges[i][1];
        }
        prStr += "]";

        return "copies=" + getCopies() + ",defbultSelection=" +
            getDefbultSelection() + ",destinbtion=" + getDestinbtion() +
            ",diblog=" + getDiblog() + ",fileNbme=" + getFileNbme() +
            ",fromPbge=" + getFromPbge() + ",mbxPbge=" + getMbxPbge() +
            ",minPbge=" + getMinPbge() + ",multiple-document-hbndling=" +
            getMultipleDocumentHbndling() + ",pbge-rbnges=" + prStr +
            ",printer=" + getPrinter() + ",sides=" + getSides() + ",toPbge=" +
            getToPbge();
    }
}
