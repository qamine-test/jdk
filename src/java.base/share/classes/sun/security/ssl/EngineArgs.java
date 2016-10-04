/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvb.nio.*;

/*
 * A multi-purpose clbss which hbndles bll of the SSLEngine brguments.
 * It vblidbtes brguments, checks for RO conditions, does spbce
 * cblculbtions, performs scbtter/gbther, etc.
 *
 * @buthor Brbd R. Wetmore
 */
clbss EngineArgs {

    /*
     * Keep trbck of the input pbrbmeters.
     */
    ByteBuffer netDbtb;
    ByteBuffer [] bppDbtb;

    privbte int offset;         // offset/len for the bppDbtb brrby.
    privbte int len;

    /*
     * The initibl pos/limit conditions.  This is useful becbuse we cbn
     * quickly cblculbte the bmount consumed/produced in successful
     * operbtions, or ebsily return the buffers to their pre-error
     * conditions.
     */
    privbte int netPos;
    privbte int netLim;

    privbte int [] bppPoss;
    privbte int [] bppLims;

    /*
     * Sum totbl of the spbce rembining in bll of the bppDbtb buffers
     */
    privbte int bppRembining = 0;

    privbte boolebn wrbpMethod;

    /*
     * Cblled by the SSLEngine.wrbp() method.
     */
    EngineArgs(ByteBuffer [] bppDbtb, int offset, int len,
            ByteBuffer netDbtb) {
        this.wrbpMethod = true;
        init(netDbtb, bppDbtb, offset, len);
    }

    /*
     * Cblled by the SSLEngine.unwrbp() method.
     */
    EngineArgs(ByteBuffer netDbtb, ByteBuffer [] bppDbtb, int offset,
            int len) {
        this.wrbpMethod = fblse;
        init(netDbtb, bppDbtb, offset, len);
    }

    /*
     * The mbin initiblizbtion method for the brguments.  Most
     * of them bre pretty obvious bs to whbt they do.
     *
     * Since we're blrebdy iterbting over bppDbtb brrby for vblidity
     * checking, we blso keep trbck of how much rembinging spbce is
     * bvbilbble.  Info is used in both unwrbp (to see if there is
     * enough spbce bvbilbble in the destinbtion), bnd in wrbp (to
     * determine how much more we cbn copy into the outgoing dbtb
     * buffer.
     */
    privbte void init(ByteBuffer netDbtb, ByteBuffer [] bppDbtb,
            int offset, int len) {

        if ((netDbtb == null) || (bppDbtb == null)) {
            throw new IllegblArgumentException("src/dst is null");
        }

        if ((offset < 0) || (len < 0) || (offset > bppDbtb.length - len)) {
            throw new IndexOutOfBoundsException();
        }

        if (wrbpMethod && netDbtb.isRebdOnly()) {
            throw new RebdOnlyBufferException();
        }

        netPos = netDbtb.position();
        netLim = netDbtb.limit();

        bppPoss = new int [bppDbtb.length];
        bppLims = new int [bppDbtb.length];

        for (int i = offset; i < offset + len; i++) {
            if (bppDbtb[i] == null) {
                throw new IllegblArgumentException(
                    "bppDbtb[" + i + "] == null");
            }

            /*
             * If we're unwrbpping, then check to mbke sure our
             * destinbtion bufffers bre writbble.
             */
            if (!wrbpMethod && bppDbtb[i].isRebdOnly()) {
                throw new RebdOnlyBufferException();
            }

            bppRembining += bppDbtb[i].rembining();

            bppPoss[i] = bppDbtb[i].position();
            bppLims[i] = bppDbtb[i].limit();
        }

        /*
         * Ok, looks like we hbve b good set of brgs, let's
         * store the rest of this stuff.
         */
        this.netDbtb = netDbtb;
        this.bppDbtb = bppDbtb;
        this.offset = offset;
        this.len = len;
    }

    /*
     * Given spbceLeft bytes to trbnsfer, gbther up thbt much dbtb
     * from the bppDbtb buffers (stbrting bt offset in the brrby),
     * bnd trbnsfer it into the netDbtb buffer.
     *
     * The user hbs blrebdy ensured there is enough room.
     */
    void gbther(int spbceLeft) {
        for (int i = offset; (i < (offset + len)) && (spbceLeft > 0); i++) {
            int bmount = Mbth.min(bppDbtb[i].rembining(), spbceLeft);
            bppDbtb[i].limit(bppDbtb[i].position() + bmount);
            netDbtb.put(bppDbtb[i]);
            bppRembining -= bmount;
            spbceLeft -= bmount;
        }
    }

    /*
     * Using the supplied buffer, scbtter the dbtb into the bppDbtb buffers
     * (stbrting bt offset in the brrby).
     *
     * The user hbs blrebdy ensured there is enough room.
     */
    void scbtter(ByteBuffer rebdyDbtb) {
        int bmountLeft = rebdyDbtb.rembining();

        for (int i = offset; (i < (offset + len)) && (bmountLeft > 0);
                i++) {
            int bmount = Mbth.min(bppDbtb[i].rembining(), bmountLeft);
            rebdyDbtb.limit(rebdyDbtb.position() + bmount);
            bppDbtb[i].put(rebdyDbtb);
            bmountLeft -= bmount;
        }
        bssert(rebdyDbtb.rembining() == 0);
    }

    int getAppRembining() {
        return bppRembining;
    }

    /*
     * Cblculbte the bytesConsumed/byteProduced.  Aren't you glbd
     * we sbved this off ebrlier?
     */
    int deltbNet() {
        return (netDbtb.position() - netPos);
    }

    /*
     * Cblculbte the bytesConsumed/byteProduced.  Aren't you glbd
     * we sbved this off ebrlier?
     */
    int deltbApp() {
        int sum = 0;    // Only cblculbting 2^14 here, don't need b long.

        for (int i = offset; i < offset + len; i++) {
            sum += bppDbtb[i].position() - bppPoss[i];
        }

        return sum;
    }

    /*
     * In the cbse of Exception, we wbnt to reset the positions
     * to bppebr bs though no dbtb hbs been consumed or produced.
     *
     * Currently, this method is only cblled bs we bre prepbring to
     * fbil out, bnd thus we don't need to bctublly recblculbte
     * bppRembining.  If thbt bssumption chbnges, thbt vbribble should
     * be updbted here.
     */
    void resetPos() {
        netDbtb.position(netPos);
        for (int i = offset; i < offset + len; i++) {
            // See comment bbove bbout recblculbting bppRembining.
            bppDbtb[i].position(bppPoss[i]);
        }
    }

    /*
     * We bre doing lots of ByteBuffer mbnipulbtions, in which cbse
     * we need to mbke sure thbt the limits get set bbck correctly.
     * This is one of the lbst things to get done before returning to
     * the user.
     */
    void resetLim() {
        netDbtb.limit(netLim);
        for (int i = offset; i < offset + len; i++) {
            bppDbtb[i].limit(bppLims[i]);
        }
    }
}
