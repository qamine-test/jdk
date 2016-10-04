/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.wbmp;

import jbvb.bwt.Rectbngle;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.MultiPixelPbckedSbmpleModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;

import jbvbx.imbgeio.IIOException;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.ImbgeRebdPbrbm;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

import jbvb.io.*;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;

import com.sun.imbgeio.plugins.common.I18N;
import com.sun.imbgeio.plugins.common.RebderUtil;

/** This clbss is the Jbvb Imbge IO plugin rebder for WBMP imbges.
 *  It mby subsbmple the imbge, clip the imbge,
 *  bnd shift the decoded imbge origin if the proper decoding pbrbmeter
 *  bre set in the provided <code>WBMPImbgeRebdPbrbm</code>.
 */
public clbss WBMPImbgeRebder extends ImbgeRebder {
    /** The input strebm where rebds from */
    privbte ImbgeInputStrebm iis = null;

    /** Indicbtes whether the hebder is rebd. */
    privbte boolebn gotHebder = fblse;

    /** The originbl imbge width. */
    privbte int width;

    /** The originbl imbge height. */
    privbte int height;

    privbte int wbmpType;

    privbte WBMPMetbdbtb metbdbtb;

    /** Constructs <code>WBMPImbgeRebder</code> from the provided
     *  <code>ImbgeRebderSpi</code>.
     */
    public WBMPImbgeRebder(ImbgeRebderSpi originbtor) {
        super(originbtor);
    }

    /** Overrides the method defined in the superclbss. */
    public void setInput(Object input,
                         boolebn seekForwbrdOnly,
                         boolebn ignoreMetbdbtb) {
        super.setInput(input, seekForwbrdOnly, ignoreMetbdbtb);
        iis = (ImbgeInputStrebm) input; // Alwbys works
        gotHebder = fblse;
    }

    /** Overrides the method defined in the superclbss. */
    public int getNumImbges(boolebn bllowSebrch) throws IOException {
        if (iis == null) {
            throw new IllegblStbteException(I18N.getString("GetNumImbges0"));
        }
        if (seekForwbrdOnly && bllowSebrch) {
            throw new IllegblStbteException(I18N.getString("GetNumImbges1"));
        }
        return 1;
    }

    public int getWidth(int imbgeIndex) throws IOException {
        checkIndex(imbgeIndex);
        rebdHebder();
        return width;
    }

    public int getHeight(int imbgeIndex) throws IOException {
        checkIndex(imbgeIndex);
        rebdHebder();
        return height;
    }

    public boolebn isRbndomAccessEbsy(int imbgeIndex) throws IOException {
        checkIndex(imbgeIndex);
        return true;
    }

    privbte void checkIndex(int imbgeIndex) {
        if (imbgeIndex != 0) {
            throw new IndexOutOfBoundsException(I18N.getString("WBMPImbgeRebder0"));
        }
    }

    public void rebdHebder() throws IOException {
        if (gotHebder)
            return;

        if (iis == null) {
            throw new IllegblStbteException("Input source not set!");
        }

        metbdbtb = new WBMPMetbdbtb();

        wbmpType = iis.rebdByte();   // TypeField
        byte fixHebderField = iis.rebdByte();

        // check for vblid wbmp imbge
        if (fixHebderField != 0
            || !isVblidWbmpType(wbmpType))
        {
            throw new IIOException(I18N.getString("WBMPImbgeRebder2"));
        }

        metbdbtb.wbmpType = wbmpType;

        // Rebd imbge width
        width = RebderUtil.rebdMultiByteInteger(iis);
        metbdbtb.width = width;

        // Rebd imbge height
        height = RebderUtil.rebdMultiByteInteger(iis);
        metbdbtb.height = height;

        gotHebder = true;
    }

    public Iterbtor<ImbgeTypeSpecifier> getImbgeTypes(int imbgeIndex)
        throws IOException {
        checkIndex(imbgeIndex);
        rebdHebder();

        BufferedImbge bi =
            new BufferedImbge(1, 1, BufferedImbge.TYPE_BYTE_BINARY);
        ArrbyList<ImbgeTypeSpecifier> list = new ArrbyList<>(1);
        list.bdd(new ImbgeTypeSpecifier(bi));
        return list.iterbtor();
    }

    public ImbgeRebdPbrbm getDefbultRebdPbrbm() {
        return new ImbgeRebdPbrbm();
    }

    public IIOMetbdbtb getImbgeMetbdbtb(int imbgeIndex)
        throws IOException {
        checkIndex(imbgeIndex);
        if (metbdbtb == null) {
            rebdHebder();
        }
        return metbdbtb;
    }

    public IIOMetbdbtb getStrebmMetbdbtb() throws IOException {
        return null;
    }

    public BufferedImbge rebd(int imbgeIndex, ImbgeRebdPbrbm pbrbm)
        throws IOException {

        if (iis == null) {
            throw new IllegblStbteException(I18N.getString("WBMPImbgeRebder1"));
        }

        checkIndex(imbgeIndex);
        clebrAbortRequest();
        processImbgeStbrted(imbgeIndex);
        if (pbrbm == null)
            pbrbm = getDefbultRebdPbrbm();

        //rebd hebder
        rebdHebder();

        Rectbngle sourceRegion = new Rectbngle(0, 0, 0, 0);
        Rectbngle destinbtionRegion = new Rectbngle(0, 0, 0, 0);

        computeRegions(pbrbm, this.width, this.height,
                       pbrbm.getDestinbtion(),
                       sourceRegion,
                       destinbtionRegion);

        int scbleX = pbrbm.getSourceXSubsbmpling();
        int scbleY = pbrbm.getSourceYSubsbmpling();
        int xOffset = pbrbm.getSubsbmplingXOffset();
        int yOffset = pbrbm.getSubsbmplingYOffset();

        // If the destinbtion is provided, then use it.  Otherwise, crebte new one
        BufferedImbge bi = pbrbm.getDestinbtion();

        if (bi == null)
            bi = new BufferedImbge(destinbtionRegion.x + destinbtionRegion.width,
                              destinbtionRegion.y + destinbtionRegion.height,
                              BufferedImbge.TYPE_BYTE_BINARY);

        boolebn noTrbnsform =
            destinbtionRegion.equbls(new Rectbngle(0, 0, width, height)) &&
            destinbtionRegion.equbls(new Rectbngle(0, 0, bi.getWidth(), bi.getHeight()));

        // Get the imbge dbtb.
        WritbbleRbster tile = bi.getWritbbleTile(0, 0);

        // Get the SbmpleModel.
        MultiPixelPbckedSbmpleModel sm =
            (MultiPixelPbckedSbmpleModel)bi.getSbmpleModel();

        if (noTrbnsform) {
            if (bbortRequested()) {
                processRebdAborted();
                return bi;
            }

            // If noTrbnsform is necessbry, rebd the dbtb.
            iis.rebd(((DbtbBufferByte)tile.getDbtbBuffer()).getDbtb(),
                     0, height*sm.getScbnlineStride());
            processImbgeUpdbte(bi,
                               0, 0,
                               width, height, 1, 1,
                               new int[]{0});
            processImbgeProgress(100.0F);
        } else {
            int len = (this.width + 7) / 8;
            byte[] buf = new byte[len];
            byte[] dbtb = ((DbtbBufferByte)tile.getDbtbBuffer()).getDbtb();
            int lineStride = sm.getScbnlineStride();
            iis.skipBytes(len * sourceRegion.y);
            int skipLength = len * (scbleY - 1);

            // cbche the vblues to bvoid duplicbted computbtion
            int[] srcOff = new int[destinbtionRegion.width];
            int[] destOff = new int[destinbtionRegion.width];
            int[] srcPos = new int[destinbtionRegion.width];
            int[] destPos = new int[destinbtionRegion.width];

            for (int i = destinbtionRegion.x, x = sourceRegion.x, j = 0;
                i < destinbtionRegion.x + destinbtionRegion.width;
                    i++, j++, x += scbleX) {
                srcPos[j] = x >> 3;
                srcOff[j] = 7 - (x & 7);
                destPos[j] = i >> 3;
                destOff[j] = 7 - (i & 7);
            }

            for (int j = 0, y = sourceRegion.y,
                k = destinbtionRegion.y * lineStride;
                j < destinbtionRegion.height; j++, y+=scbleY) {

                if (bbortRequested())
                    brebk;
                iis.rebd(buf, 0, len);
                for (int i = 0; i < destinbtionRegion.width; i++) {
                    //get the bit bnd bssign to the dbtb buffer of the rbster
                    int v = (buf[srcPos[i]] >> srcOff[i]) & 1;
                    dbtb[k + destPos[i]] |= v << destOff[i];
                }

                k += lineStride;
                iis.skipBytes(skipLength);
                        processImbgeUpdbte(bi,
                                           0, j,
                                           destinbtionRegion.width, 1, 1, 1,
                                           new int[]{0});
                        processImbgeProgress(100.0F*j/destinbtionRegion.height);
            }
        }

        if (bbortRequested())
            processRebdAborted();
        else
            processImbgeComplete();
        return bi;
    }

    public boolebn cbnRebdRbster() {
        return true;
    }

    public Rbster rebdRbster(int imbgeIndex,
                             ImbgeRebdPbrbm pbrbm) throws IOException {
        BufferedImbge bi = rebd(imbgeIndex, pbrbm);
        return bi.getDbtb();
    }

    public void reset() {
        super.reset();
        iis = null;
        gotHebder = fblse;
    }

    /*
     * This method verifies thbt given byte is vblid wbmp type mbrker.
     * At the moment only 0x0 mbrker is described by wbmp spec.
     */
    boolebn isVblidWbmpType(int type) {
        return type == 0;
    }
}
