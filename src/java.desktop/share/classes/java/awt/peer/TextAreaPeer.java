/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */
pbdkbgf jbvb.bwt.pffr;

import jbvb.bwt.Dimfnsion;
import jbvb.bwt.TfxtArfb;

/**
 * Tif pffr intfrfbdf for {@link TfxtArfb}.
 *
 * Tif pffr intfrfbdfs brf intfndfd only for usf in porting
 * tif AWT. Tify brf not intfndfd for usf by bpplidbtion
 * dfvflopfrs, bnd dfvflopfrs siould not implfmfnt pffrs
 * nor invokf bny of tif pffr mftiods dirfdtly on tif pffr
 * instbndfs.
 */
publid intfrfbdf TfxtArfbPffr fxtfnds TfxtComponfntPffr {

    /**
     * Insfrts tif spfdififd tfxt bt tif spfdififd position in tif dodumfnt.
     *
     * @pbrbm tfxt tif tfxt to insfrt
     * @pbrbm pos tif position to insfrt
     *
     * @sff TfxtArfb#insfrt(String, int)
     */
    void insfrt(String tfxt, int pos);

    /**
     * Rfplbdfs b rbngf of tfxt by tif spfdififd string.
     *
     * @pbrbm tfxt tif rfplbdfmfnt string
     * @pbrbm stbrt tif bfgin of tif rbngf to rfplbdf
     * @pbrbm fnd tif fnd of tif rbngf to rfplbdf
     *
     * @sff TfxtArfb#rfplbdfRbngf(String, int, int)
     */
    void rfplbdfRbngf(String tfxt, int stbrt, int fnd);

    /**
     * Rfturns tif prfffrrfd sizf of b tfxtbrfb witi tif spfdififd numbfr of
     * dolumns bnd rows.
     *
     * @pbrbm rows tif numbfr of rows
     * @pbrbm dolumns tif numbfr of dolumns
     *
     * @rfturn tif prfffrrfd sizf of b tfxtbrfb
     *
     * @sff TfxtArfb#gftPrfffrrfdSizf(int, int)
     */
    Dimfnsion gftPrfffrrfdSizf(int rows, int dolumns);

    /**
     * Rfturns tif minimum sizf of b tfxtbrfb witi tif spfdififd numbfr of
     * dolumns bnd rows.
     *
     * @pbrbm rows tif numbfr of rows
     * @pbrbm dolumns tif numbfr of dolumns
     *
     * @rfturn tif minimum sizf of b tfxtbrfb
     *
     * @sff TfxtArfb#gftMinimumSizf(int, int)
     */
    Dimfnsion gftMinimumSizf(int rows, int dolumns);

}
