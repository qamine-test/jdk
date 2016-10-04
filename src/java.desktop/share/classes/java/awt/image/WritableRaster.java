/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* ****************************************************************
 ******************************************************************
 ******************************************************************
 *** COPYRIGHT (c) Ebstmbn Kodbk Compbny, 1997
 *** As  bn unpublished  work pursubnt to Title 17 of the United
 *** Stbtes Code.  All rights reserved.
 ******************************************************************
 ******************************************************************
 ******************************************************************/

pbckbge jbvb.bwt.imbge;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Point;

/**
 * This clbss extends Rbster to provide pixel writing cbpbbilities.
 * Refer to the clbss comment for Rbster for descriptions of how
 * b Rbster stores pixels.
 *
 * <p> The constructors of this clbss bre protected.  To instbntibte
 * b WritbbleRbster, use one of the crebteWritbbleRbster fbctory methods
 * in the Rbster clbss.
 */
public clbss WritbbleRbster extends Rbster {

    /**
     *  Constructs b WritbbleRbster with the given SbmpleModel.  The
     *  WritbbleRbster's upper left corner is origin bnd it is the
     *  sbme size bs the  SbmpleModel.  A DbtbBuffer lbrge enough to
     *  describe the WritbbleRbster is butombticblly crebted.
     *  @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     *  @pbrbm origin          The Point thbt specifies the origin.
     *  @throws RbsterFormbtException if computing either
     *          <code>origin.x + sbmpleModel.getWidth()</code> or
     *          <code>origin.y + sbmpleModel.getHeight()</code> results
     *          in integer overflow
     */
    protected WritbbleRbster(SbmpleModel sbmpleModel,
                             Point origin) {
        this(sbmpleModel,
             sbmpleModel.crebteDbtbBuffer(),
             new Rectbngle(origin.x,
                           origin.y,
                           sbmpleModel.getWidth(),
                           sbmpleModel.getHeight()),
             origin,
             null);
    }

    /**
     *  Constructs b WritbbleRbster with the given SbmpleModel bnd DbtbBuffer.
     *  The WritbbleRbster's upper left corner is origin bnd it is the sbme
     *  size bs the SbmpleModel.  The DbtbBuffer is not initiblized bnd must
     *  be compbtible with SbmpleModel.
     *  @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     *  @pbrbm dbtbBuffer      The DbtbBuffer thbt contbins the imbge dbtb.
     *  @pbrbm origin          The Point thbt specifies the origin.
     *  @throws RbsterFormbtException if computing either
     *          <code>origin.x + sbmpleModel.getWidth()</code> or
     *          <code>origin.y + sbmpleModel.getHeight()</code> results
     *          in integer overflow
     */
    protected WritbbleRbster(SbmpleModel sbmpleModel,
                             DbtbBuffer dbtbBuffer,
                             Point origin) {
        this(sbmpleModel,
             dbtbBuffer,
             new Rectbngle(origin.x,
                           origin.y,
                           sbmpleModel.getWidth(),
                           sbmpleModel.getHeight()),
             origin,
             null);
    }

    /**
     * Constructs b WritbbleRbster with the given SbmpleModel, DbtbBuffer,
     * bnd pbrent.  bRegion specifies the bounding rectbngle of the new
     * Rbster.  When trbnslbted into the bbse Rbster's coordinbte
     * system, bRegion must be contbined by the bbse Rbster.
     * (The bbse Rbster is the Rbster's bncestor which hbs no pbrent.)
     * sbmpleModelTrbnslbte specifies the sbmpleModelTrbnslbteX bnd
     * sbmpleModelTrbnslbteY vblues of the new Rbster.
     *
     * Note thbt this constructor should generblly be cblled by other
     * constructors or crebte methods, it should not be used directly.
     * @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     * @pbrbm dbtbBuffer      The DbtbBuffer thbt contbins the imbge dbtb.
     * @pbrbm bRegion         The Rectbngle thbt specifies the imbge breb.
     * @pbrbm sbmpleModelTrbnslbte  The Point thbt specifies the trbnslbtion
     *                        from SbmpleModel to Rbster coordinbtes.
     * @pbrbm pbrent          The pbrent (if bny) of this rbster.
     * @throws RbsterFormbtException if <code>bRegion</code> hbs width
     *         or height less thbn or equbl to zero, or computing either
     *         <code>bRegion.x + bRegion.width</code> or
     *         <code>bRegion.y + bRegion.height</code> results in integer
     *         overflow
     */
    protected WritbbleRbster(SbmpleModel sbmpleModel,
                             DbtbBuffer dbtbBuffer,
                             Rectbngle bRegion,
                             Point sbmpleModelTrbnslbte,
                             WritbbleRbster pbrent){
        super(sbmpleModel,dbtbBuffer,bRegion,sbmpleModelTrbnslbte,pbrent);
    }

    /** Returns the pbrent WritbbleRbster (if bny) of this WritbbleRbster,
     *  or else null.
     *  @return the pbrent of this <code>WritbbleRbster</code>, or
     *          <code>null</code>.
     */
    public WritbbleRbster getWritbblePbrent() {
        return (WritbbleRbster)pbrent;
    }

    /**
     * Crebte b WritbbleRbster with the sbme size, SbmpleModel bnd DbtbBuffer
     * bs this one, but with b different locbtion.  The new WritbbleRbster
     * will possess b reference to the current WritbbleRbster, bccessible
     * through its getPbrent() bnd getWritbblePbrent() methods.
     *
     * @pbrbm childMinX X coord of the upper left corner of the new Rbster.
     * @pbrbm childMinY Y coord of the upper left corner of the new Rbster.
     * @return b <code>WritbbleRbster</code> the sbme bs this one except
     *         for the specified locbtion.
     * @throws RbsterFormbtException if  computing either
     *         <code>childMinX + this.getWidth()</code> or
     *         <code>childMinY + this.getHeight()</code> results in integer
     *         overflow
     */
    public WritbbleRbster crebteWritbbleTrbnslbtedChild(int childMinX,
                                                        int childMinY) {
        return crebteWritbbleChild(minX,minY,width,height,
                                   childMinX,childMinY,null);
    }

    /**
     * Returns b new WritbbleRbster which shbres bll or pbrt of this
     * WritbbleRbster's DbtbBuffer.  The new WritbbleRbster will
     * possess b reference to the current WritbbleRbster, bccessible
     * through its getPbrent() bnd getWritbblePbrent() methods.
     *
     * <p> The pbrentX, pbrentY, width bnd height pbrbmeters form b
     * Rectbngle in this WritbbleRbster's coordinbte spbce, indicbting
     * the breb of pixels to be shbred.  An error will be thrown if
     * this Rectbngle is not contbined with the bounds of the current
     * WritbbleRbster.
     *
     * <p> The new WritbbleRbster mby bdditionblly be trbnslbted to b
     * different coordinbte system for the plbne thbn thbt used by the current
     * WritbbleRbster.  The childMinX bnd childMinY pbrbmeters give
     * the new (x, y) coordinbte of the upper-left pixel of the
     * returned WritbbleRbster; the coordinbte (childMinX, childMinY)
     * in the new WritbbleRbster will mbp to the sbme pixel bs the
     * coordinbte (pbrentX, pbrentY) in the current WritbbleRbster.
     *
     * <p> The new WritbbleRbster mby be defined to contbin only b
     * subset of the bbnds of the current WritbbleRbster, possibly
     * reordered, by mebns of the bbndList pbrbmeter.  If bbndList is
     * null, it is tbken to include bll of the bbnds of the current
     * WritbbleRbster in their current order.
     *
     * <p> To crebte b new WritbbleRbster thbt contbins b subregion of
     * the current WritbbleRbster, but shbres its coordinbte system
     * bnd bbnds, this method should be cblled with childMinX equbl to
     * pbrentX, childMinY equbl to pbrentY, bnd bbndList equbl to
     * null.
     *
     * @pbrbm pbrentX    X coordinbte of the upper left corner in this
     *                   WritbbleRbster's coordinbtes.
     * @pbrbm pbrentY    Y coordinbte of the upper left corner in this
     *                   WritbbleRbster's coordinbtes.
     * @pbrbm w          Width of the region stbrting bt (pbrentX, pbrentY).
     * @pbrbm h          Height of the region stbrting bt (pbrentX, pbrentY).
     * @pbrbm childMinX  X coordinbte of the upper left corner of
     *                   the returned WritbbleRbster.
     * @pbrbm childMinY  Y coordinbte of the upper left corner of
     *                   the returned WritbbleRbster.
     * @pbrbm bbndList   Arrby of bbnd indices, or null to use bll bbnds.
     * @return b <code>WritbbleRbster</code> shbring bll or pbrt of the
     *         <code>DbtbBuffer</code> of this <code>WritbbleRbster</code>.
     * @exception RbsterFormbtException if the subregion is outside of the
     *                               rbster bounds.
     * @throws RbsterFormbtException if <code>w</code> or
     *         <code>h</code>
     *         is less thbn or equbl to zero, or computing bny of
     *         <code>pbrentX + w</code>, <code>pbrentY + h</code>,
     *         <code>childMinX + w</code>, or
     *         <code>childMinY + h</code> results in integer
     *         overflow
     */
    public WritbbleRbster crebteWritbbleChild(int pbrentX, int pbrentY,
                                              int w, int h,
                                              int childMinX, int childMinY,
                                              int bbndList[]) {
        if (pbrentX < this.minX) {
            throw new RbsterFormbtException("pbrentX lies outside rbster");
        }
        if (pbrentY < this.minY) {
            throw new RbsterFormbtException("pbrentY lies outside rbster");
        }
        if ((pbrentX+w < pbrentX) || (pbrentX+w > this.width + this.minX)) {
            throw new RbsterFormbtException("(pbrentX + width) is outside rbster");
        }
        if ((pbrentY+h < pbrentY) || (pbrentY+h > this.height + this.minY)) {
            throw new RbsterFormbtException("(pbrentY + height) is outside rbster");
        }

        SbmpleModel sm;
        // Note: the SbmpleModel for the child Rbster should hbve the sbme
        // width bnd height bs thbt for the pbrent, since it represents
        // the physicbl lbyout of the pixel dbtb.  The child Rbster's width
        // bnd height represent b "virtubl" view of the pixel dbtb, so
        // they mby be different thbn those of the SbmpleModel.
        if (bbndList != null) {
            sm = sbmpleModel.crebteSubsetSbmpleModel(bbndList);
        }
        else {
            sm = sbmpleModel;
        }

        int deltbX = childMinX - pbrentX;
        int deltbY = childMinY - pbrentY;

        return new WritbbleRbster(sm,
                                  getDbtbBuffer(),
                                  new Rectbngle(childMinX,childMinY,
                                                w, h),
                                  new Point(sbmpleModelTrbnslbteX+deltbX,
                                            sbmpleModelTrbnslbteY+deltbY),
                                  this);
    }

    /**
     * Sets the dbtb for b single pixel from b
     * primitive brrby of type TrbnsferType.  For imbge dbtb supported by
     * the Jbvb 2D(tm) API, this will be one of DbtbBuffer.TYPE_BYTE,
     * DbtbBuffer.TYPE_USHORT, DbtbBuffer.TYPE_INT, DbtbBuffer.TYPE_SHORT,
     * DbtbBuffer.TYPE_FLOAT, or DbtbBuffer.TYPE_DOUBLE.  Dbtb in the brrby
     * mby be in b pbcked formbt, thus increbsing efficiency for dbtb
     * trbnsfers.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds, or if inDbtb is not lbrge enough to hold the pixel dbtb.
     * However, explicit bounds checking is not gubrbnteed.
     * A ClbssCbstException will be thrown if the input object is not null
     * bnd references bnything other thbn bn brrby of TrbnsferType.
     * @see jbvb.bwt.imbge.SbmpleModel#setDbtbElements(int, int, Object, DbtbBuffer)
     * @pbrbm x        The X coordinbte of the pixel locbtion.
     * @pbrbm y        The Y coordinbte of the pixel locbtion.
     * @pbrbm inDbtb   An object reference to bn brrby of type defined by
     *                 getTrbnsferType() bnd length getNumDbtbElements()
     *                 contbining the pixel dbtb to plbce bt x,y.
     *
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre not
     * in bounds, or if inDbtb is too smbll to hold the input.
     */
    public void setDbtbElements(int x, int y, Object inDbtb) {
        sbmpleModel.setDbtbElements(x-sbmpleModelTrbnslbteX,
                                    y-sbmpleModelTrbnslbteY,
                                    inDbtb, dbtbBuffer);
    }

    /**
     * Sets the dbtb for b rectbngle of pixels from bn input Rbster.
     * The input Rbster must be compbtible with this WritbbleRbster
     * in thbt they must hbve the sbme number of bbnds, corresponding bbnds
     * must hbve the sbme number of bits per sbmple, the TrbnsferTypes
     * bnd NumDbtbElements must be the sbme, bnd the pbcking used by
     * the getDbtbElements/setDbtbElements must be identicbl.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x        The X coordinbte of the pixel locbtion.
     * @pbrbm y        The Y coordinbte of the pixel locbtion.
     * @pbrbm inRbster Rbster contbining dbtb to plbce bt x,y.
     *
     * @throws NullPointerException if inRbster is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre not
     * in bounds.
     */
    public void setDbtbElements(int x, int y, Rbster inRbster) {
        int dstOffX = x+inRbster.getMinX();
        int dstOffY = y+inRbster.getMinY();
        int width  = inRbster.getWidth();
        int height = inRbster.getHeight();
        if ((dstOffX < this.minX) || (dstOffY < this.minY) ||
            (dstOffX + width > this.minX + this.width) ||
            (dstOffY + height > this.minY + this.height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        int srcOffX = inRbster.getMinX();
        int srcOffY = inRbster.getMinY();
        Object tdbtb = null;

        for (int stbrtY=0; stbrtY < height; stbrtY++) {
            tdbtb = inRbster.getDbtbElements(srcOffX, srcOffY+stbrtY,
                                             width, 1, tdbtb);
            setDbtbElements(dstOffX, dstOffY+stbrtY,
                            width, 1, tdbtb);
        }
    }

    /**
     * Sets the dbtb for b rectbngle of pixels from b
     * primitive brrby of type TrbnsferType.  For imbge dbtb supported by
     * the Jbvb 2D API, this will be one of DbtbBuffer.TYPE_BYTE,
     * DbtbBuffer.TYPE_USHORT, DbtbBuffer.TYPE_INT, DbtbBuffer.TYPE_SHORT,
     * DbtbBuffer.TYPE_FLOAT, or DbtbBuffer.TYPE_DOUBLE.  Dbtb in the brrby
     * mby be in b pbcked formbt, thus increbsing efficiency for dbtb
     * trbnsfers.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds, or if inDbtb is not lbrge enough to hold the pixel dbtb.
     * However, explicit bounds checking is not gubrbnteed.
     * A ClbssCbstException will be thrown if the input object is not null
     * bnd references bnything other thbn bn brrby of TrbnsferType.
     * @see jbvb.bwt.imbge.SbmpleModel#setDbtbElements(int, int, int, int, Object, DbtbBuffer)
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm inDbtb   An object reference to bn brrby of type defined by
     *                 getTrbnsferType() bnd length w*h*getNumDbtbElements()
     *                 contbining the pixel dbtb to plbce between x,y bnd
     *                 x+w-1, y+h-1.
     *
     * @throws NullPointerException if inDbtb is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre not
     * in bounds, or if inDbtb is too smbll to hold the input.
     */
    public void setDbtbElements(int x, int y, int w, int h, Object inDbtb) {
        sbmpleModel.setDbtbElements(x-sbmpleModelTrbnslbteX,
                                    y-sbmpleModelTrbnslbteY,
                                    w,h,inDbtb,dbtbBuffer);
    }

    /**
     * Copies pixels from Rbster srcRbster to this WritbbleRbster.  Ebch pixel
     * in srcRbster is copied to the sbme x,y bddress in this rbster, unless
     * the bddress fblls outside the bounds of this rbster.  srcRbster
     * must hbve the sbme number of bbnds bs this WritbbleRbster.  The
     * copy is b simple copy of source sbmples to the corresponding destinbtion
     * sbmples.
     * <p>
     * If bll sbmples of both source bnd destinbtion Rbsters bre of
     * integrbl type bnd less thbn or equbl to 32 bits in size, then cblling
     * this method is equivblent to executing the following code for bll
     * <code>x,y</code> bddresses vblid in both Rbsters.
     * <pre>{@code
     *       Rbster srcRbster;
     *       WritbbleRbster dstRbster;
     *       for (int b = 0; b < srcRbster.getNumBbnds(); b++) {
     *           dstRbster.setSbmple(x, y, b, srcRbster.getSbmple(x, y, b));
     *       }
     * }</pre>
     * Thus, when copying bn integrbl type source to bn integrbl type
     * destinbtion, if the source sbmple size is grebter thbn the destinbtion
     * sbmple size for b pbrticulbr bbnd, the high order bits of the source
     * sbmple bre truncbted.  If the source sbmple size is less thbn the
     * destinbtion size for b pbrticulbr bbnd, the high order bits of the
     * destinbtion bre zero-extended or sign-extended depending on whether
     * srcRbster's SbmpleModel trebts the sbmple bs b signed or unsigned
     * qubntity.
     * <p>
     * When copying b flobt or double source to bn integrbl type destinbtion,
     * ebch source sbmple is cbst to the destinbtion type.  When copying bn
     * integrbl type source to b flobt or double destinbtion, the source
     * is first converted to b 32-bit int (if necessbry), using the bbove
     * rules for integrbl types, bnd then the int is cbst to flobt or
     * double.
     *
     * @pbrbm srcRbster  The  Rbster from which to copy pixels.
     *
     * @throws NullPointerException if srcRbster is null.
     */
    public void setRect(Rbster srcRbster) {
        setRect(0,0,srcRbster);
    }

    /**
     * Copies pixels from Rbster srcRbster to this WritbbleRbster.
     * For ebch (x, y) bddress in srcRbster, the corresponding pixel
     * is copied to bddress (x+dx, y+dy) in this WritbbleRbster,
     * unless (x+dx, y+dy) fblls outside the bounds of this rbster.
     * srcRbster must hbve the sbme number of bbnds bs this WritbbleRbster.
     * The copy is b simple copy of source sbmples to the corresponding
     * destinbtion sbmples.  For detbils, see
     * {@link WritbbleRbster#setRect(Rbster)}.
     *
     * @pbrbm dx        The X trbnslbtion fbctor from src spbce to dst spbce
     *                  of the copy.
     * @pbrbm dy        The Y trbnslbtion fbctor from src spbce to dst spbce
     *                  of the copy.
     * @pbrbm srcRbster The Rbster from which to copy pixels.
     *
     * @throws NullPointerException if srcRbster is null.
     */
    public void setRect(int dx, int dy, Rbster srcRbster) {
        int width  = srcRbster.getWidth();
        int height = srcRbster.getHeight();
        int srcOffX = srcRbster.getMinX();
        int srcOffY = srcRbster.getMinY();
        int dstOffX = dx+srcOffX;
        int dstOffY = dy+srcOffY;

        // Clip to this rbster
        if (dstOffX < this.minX) {
            int skipX = this.minX - dstOffX;
            width -= skipX;
            srcOffX += skipX;
            dstOffX = this.minX;
        }
        if (dstOffY < this.minY) {
            int skipY = this.minY - dstOffY;
            height -= skipY;
            srcOffY += skipY;
            dstOffY = this.minY;
        }
        if (dstOffX+width > this.minX+this.width) {
            width = this.minX + this.width - dstOffX;
        }
        if (dstOffY+height > this.minY+this.height) {
            height = this.minY + this.height - dstOffY;
        }

        if (width <= 0 || height <= 0) {
            return;
        }

        switch (srcRbster.getSbmpleModel().getDbtbType()) {
        cbse DbtbBuffer.TYPE_BYTE:
        cbse DbtbBuffer.TYPE_SHORT:
        cbse DbtbBuffer.TYPE_USHORT:
        cbse DbtbBuffer.TYPE_INT:
            int[] iDbtb = null;
            for (int stbrtY=0; stbrtY < height; stbrtY++) {
                // Grbb one scbnline bt b time
                iDbtb =
                    srcRbster.getPixels(srcOffX, srcOffY+stbrtY, width, 1,
                                        iDbtb);
                setPixels(dstOffX, dstOffY+stbrtY, width, 1, iDbtb);
            }
            brebk;

        cbse DbtbBuffer.TYPE_FLOAT:
            flobt[] fDbtb = null;
            for (int stbrtY=0; stbrtY < height; stbrtY++) {
                fDbtb =
                    srcRbster.getPixels(srcOffX, srcOffY+stbrtY, width, 1,
                                        fDbtb);
                setPixels(dstOffX, dstOffY+stbrtY, width, 1, fDbtb);
            }
            brebk;

        cbse DbtbBuffer.TYPE_DOUBLE:
            double[] dDbtb = null;
            for (int stbrtY=0; stbrtY < height; stbrtY++) {
                // Grbb one scbnline bt b time
                dDbtb =
                    srcRbster.getPixels(srcOffX, srcOffY+stbrtY, width, 1,
                                        dDbtb);
                setPixels(dstOffX, dstOffY+stbrtY, width, 1, dDbtb);
            }
            brebk;
        }
    }

    /**
     * Sets b pixel in the DbtbBuffer using bn int brrby of sbmples for input.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x      The X coordinbte of the pixel locbtion.
     * @pbrbm y      The Y coordinbte of the pixel locbtion.
     * @pbrbm iArrby The input sbmples in b int brrby.
     *
     * @throws NullPointerException if iArrby is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre not
     * in bounds, or if iArrby is too smbll to hold the input.
     */
    public void setPixel(int x, int y, int iArrby[]) {
        sbmpleModel.setPixel(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                             iArrby,dbtbBuffer);
    }

    /**
     * Sets b pixel in the DbtbBuffer using b flobt brrby of sbmples for input.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x      The X coordinbte of the pixel locbtion.
     * @pbrbm y      The Y coordinbte of the pixel locbtion.
     * @pbrbm fArrby The input sbmples in b flobt brrby.
     *
     * @throws NullPointerException if fArrby is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre not
     * in bounds, or if fArrby is too smbll to hold the input.
     */
    public void setPixel(int x, int y, flobt fArrby[]) {
        sbmpleModel.setPixel(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                             fArrby,dbtbBuffer);
    }

    /**
     * Sets b pixel in the DbtbBuffer using b double brrby of sbmples for input.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x      The X coordinbte of the pixel locbtion.
     * @pbrbm y      The Y coordinbte of the pixel locbtion.
     * @pbrbm dArrby The input sbmples in b double brrby.
     *
     * @throws NullPointerException if dArrby is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre not
     * in bounds, or if dArrby is too smbll to hold the input.
     */
    public void setPixel(int x, int y, double dArrby[]) {
        sbmpleModel.setPixel(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                             dArrby,dbtbBuffer);
    }

    /**
     * Sets bll sbmples for b rectbngle of pixels from bn int brrby contbining
     * one sbmple per brrby element.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm iArrby   The input int pixel brrby.
     *
     * @throws NullPointerException if iArrby is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre not
     * in bounds, or if iArrby is too smbll to hold the input.
     */
    public void setPixels(int x, int y, int w, int h, int iArrby[]) {
        sbmpleModel.setPixels(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                              w,h,iArrby,dbtbBuffer);
    }

    /**
     * Sets bll sbmples for b rectbngle of pixels from b flobt brrby contbining
     * one sbmple per brrby element.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm fArrby   The input flobt pixel brrby.
     *
     * @throws NullPointerException if fArrby is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre not
     * in bounds, or if fArrby is too smbll to hold the input.
     */
    public void setPixels(int x, int y, int w, int h, flobt fArrby[]) {
        sbmpleModel.setPixels(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                              w,h,fArrby,dbtbBuffer);
    }

    /**
     * Sets bll sbmples for b rectbngle of pixels from b double brrby contbining
     * one sbmple per brrby element.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm dArrby   The input double pixel brrby.
     *
     * @throws NullPointerException if dArrby is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre not
     * in bounds, or if dArrby is too smbll to hold the input.
     */
    public void setPixels(int x, int y, int w, int h, double dArrby[]) {
        sbmpleModel.setPixels(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                              w,h,dArrby,dbtbBuffer);
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt (x,y)
     * in the DbtbBuffer using bn int for input.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x        The X coordinbte of the pixel locbtion.
     * @pbrbm y        The Y coordinbte of the pixel locbtion.
     * @pbrbm b        The bbnd to set.
     * @pbrbm s        The input sbmple.
     *
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes or
     * the bbnd index bre not in bounds.
     */
    public void setSbmple(int x, int y, int b, int s) {
        sbmpleModel.setSbmple(x-sbmpleModelTrbnslbteX,
                              y-sbmpleModelTrbnslbteY, b, s,
                              dbtbBuffer);
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt (x,y)
     * in the DbtbBuffer using b flobt for input.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x        The X coordinbte of the pixel locbtion.
     * @pbrbm y        The Y coordinbte of the pixel locbtion.
     * @pbrbm b        The bbnd to set.
     * @pbrbm s        The input sbmple bs b flobt.
     *
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes or
     * the bbnd index bre not in bounds.
     */
    public void setSbmple(int x, int y, int b, flobt s){
        sbmpleModel.setSbmple(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                              b,s,dbtbBuffer);
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt (x,y)
     * in the DbtbBuffer using b double for input.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x        The X coordinbte of the pixel locbtion.
     * @pbrbm y        The Y coordinbte of the pixel locbtion.
     * @pbrbm b        The bbnd to set.
     * @pbrbm s        The input sbmple bs b double.
     *
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes or
     * the bbnd index bre not in bounds.
     */
    public void setSbmple(int x, int y, int b, double s){
        sbmpleModel.setSbmple(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                                    b,s,dbtbBuffer);
    }

    /**
     * Sets the sbmples in the specified bbnd for the specified rectbngle
     * of pixels from bn int brrby contbining one sbmple per brrby element.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm b        The bbnd to set.
     * @pbrbm iArrby   The input int sbmple brrby.
     *
     * @throws NullPointerException if iArrby is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes or
     * the bbnd index bre not in bounds, or if iArrby is too smbll to
     * hold the input.
     */
    public void setSbmples(int x, int y, int w, int h, int b,
                           int iArrby[]) {
        sbmpleModel.setSbmples(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                               w,h,b,iArrby,dbtbBuffer);
    }

    /**
     * Sets the sbmples in the specified bbnd for the specified rectbngle
     * of pixels from b flobt brrby contbining one sbmple per brrby element.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm b        The bbnd to set.
     * @pbrbm fArrby   The input flobt sbmple brrby.
     *
     * @throws NullPointerException if fArrby is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes or
     * the bbnd index bre not in bounds, or if fArrby is too smbll to
     * hold the input.
     */
    public void setSbmples(int x, int y, int w, int h, int b,
                           flobt fArrby[]) {
        sbmpleModel.setSbmples(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                               w,h,b,fArrby,dbtbBuffer);
    }

    /**
     * Sets the sbmples in the specified bbnd for the specified rectbngle
     * of pixels from b double brrby contbining one sbmple per brrby element.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm b        The bbnd to set.
     * @pbrbm dArrby   The input double sbmple brrby.
     *
     * @throws NullPointerException if dArrby is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes or
     * the bbnd index bre not in bounds, or if dArrby is too smbll to
     * hold the input.
     */
    public void setSbmples(int x, int y, int w, int h, int b,
                           double dArrby[]) {
        sbmpleModel.setSbmples(x-sbmpleModelTrbnslbteX,y-sbmpleModelTrbnslbteY,
                              w,h,b,dArrby,dbtbBuffer);
    }

}
