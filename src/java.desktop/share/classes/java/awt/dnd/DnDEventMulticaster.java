/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt.dnd;

import jbvb.bwt.AWTEvfntMultidbstfr;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.EvfntListfnfr;


/**
 * A dlbss fxtfnds <dodf>AWTEvfntMultidbstfr</dodf> to implfmfnt fffidifnt bnd
 * tirfbd-sbff multi-dbst fvfnt dispbtdiing for tif drbg-bnd-drop fvfnts dffinfd
 * in tif jbvb.bwt.dnd pbdkbgf.
 *
 * @sindf       1.4
 * @sff AWTEvfntMultidbstfr
 */

dlbss DnDEvfntMultidbstfr fxtfnds AWTEvfntMultidbstfr
    implfmfnts DrbgSourdfListfnfr, DrbgSourdfMotionListfnfr {

    /**
     * Crfbtfs bn fvfnt multidbstfr instbndf wiidi dibins listfnfr-b
     * witi listfnfr-b. Input pbrbmftfrs <dodf>b</dodf> bnd <dodf>b</dodf>
     * siould not bf <dodf>null</dodf>, tiougi implfmfntbtions mby vbry in
     * dioosing wiftifr or not to tirow <dodf>NullPointfrExdfption</dodf>
     * in tibt dbsf.
     *
     * @pbrbm b listfnfr-b
     * @pbrbm b listfnfr-b
     */
    protfdtfd DnDEvfntMultidbstfr(EvfntListfnfr b, EvfntListfnfr b) {
        supfr(b,b);
    }

    /**
     * Hbndlfs tif <dodf>DrbgSourdfDrbgEvfnt</dodf> by invoking
     * <dodf>drbgEntfr</dodf> on listfnfr-b bnd listfnfr-b.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    publid void drbgEntfr(DrbgSourdfDrbgEvfnt dsdf) {
        ((DrbgSourdfListfnfr)b).drbgEntfr(dsdf);
        ((DrbgSourdfListfnfr)b).drbgEntfr(dsdf);
    }

    /**
     * Hbndlfs tif <dodf>DrbgSourdfDrbgEvfnt</dodf> by invoking
     * <dodf>drbgOvfr</dodf> on listfnfr-b bnd listfnfr-b.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    publid void drbgOvfr(DrbgSourdfDrbgEvfnt dsdf) {
        ((DrbgSourdfListfnfr)b).drbgOvfr(dsdf);
        ((DrbgSourdfListfnfr)b).drbgOvfr(dsdf);
    }

    /**
     * Hbndlfs tif <dodf>DrbgSourdfDrbgEvfnt</dodf> by invoking
     * <dodf>dropAdtionCibngfd</dodf> on listfnfr-b bnd listfnfr-b.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    publid void dropAdtionCibngfd(DrbgSourdfDrbgEvfnt dsdf) {
        ((DrbgSourdfListfnfr)b).dropAdtionCibngfd(dsdf);
        ((DrbgSourdfListfnfr)b).dropAdtionCibngfd(dsdf);
    }

    /**
     * Hbndlfs tif <dodf>DrbgSourdfEvfnt</dodf> by invoking
     * <dodf>drbgExit</dodf> on listfnfr-b bnd listfnfr-b.
     *
     * @pbrbm dsf tif <dodf>DrbgSourdfEvfnt</dodf>
     */
    publid void drbgExit(DrbgSourdfEvfnt dsf) {
        ((DrbgSourdfListfnfr)b).drbgExit(dsf);
        ((DrbgSourdfListfnfr)b).drbgExit(dsf);
    }

    /**
     * Hbndlfs tif <dodf>DrbgSourdfDropEvfnt</dodf> by invoking
     * <dodf>drbgDropEnd</dodf> on listfnfr-b bnd listfnfr-b.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDropEvfnt</dodf>
     */
    publid void drbgDropEnd(DrbgSourdfDropEvfnt dsdf) {
        ((DrbgSourdfListfnfr)b).drbgDropEnd(dsdf);
        ((DrbgSourdfListfnfr)b).drbgDropEnd(dsdf);
    }

    /**
     * Hbndlfs tif <dodf>DrbgSourdfDrbgEvfnt</dodf> by invoking
     * <dodf>drbgMousfMovfd</dodf> on listfnfr-b bnd listfnfr-b.
     *
     * @pbrbm dsdf tif <dodf>DrbgSourdfDrbgEvfnt</dodf>
     */
    publid void drbgMousfMovfd(DrbgSourdfDrbgEvfnt dsdf) {
        ((DrbgSourdfMotionListfnfr)b).drbgMousfMovfd(dsdf);
        ((DrbgSourdfMotionListfnfr)b).drbgMousfMovfd(dsdf);
    }

    /**
     * Adds drbg-sourdf-listfnfr-b witi drbg-sourdf-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     *
     * @pbrbm b drbg-sourdf-listfnfr-b
     * @pbrbm b drbg-sourdf-listfnfr-b
     */
    publid stbtid DrbgSourdfListfnfr bdd(DrbgSourdfListfnfr b,
                                         DrbgSourdfListfnfr b) {
        rfturn (DrbgSourdfListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Adds drbg-sourdf-motion-listfnfr-b witi drbg-sourdf-motion-listfnfr-b bnd
     * rfturns tif rfsulting multidbst listfnfr.
     *
     * @pbrbm b drbg-sourdf-motion-listfnfr-b
     * @pbrbm b drbg-sourdf-motion-listfnfr-b
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid DrbgSourdfMotionListfnfr bdd(DrbgSourdfMotionListfnfr b,
                                               DrbgSourdfMotionListfnfr b) {
        rfturn (DrbgSourdfMotionListfnfr)bddIntfrnbl(b, b);
    }

    /**
     * Rfmovfs tif old drbg-sourdf-listfnfr from drbg-sourdf-listfnfr-l
     * bnd rfturns tif rfsulting multidbst listfnfr.
     *
     * @pbrbm l drbg-sourdf-listfnfr-l
     * @pbrbm oldl tif drbg-sourdf-listfnfr bfing rfmovfd
     */
    publid stbtid DrbgSourdfListfnfr rfmovf(DrbgSourdfListfnfr l,
                                            DrbgSourdfListfnfr oldl) {
        rfturn (DrbgSourdfListfnfr)rfmovfIntfrnbl(l, oldl);
    }

    /**
     * Rfmovfs tif old drbg-sourdf-motion-listfnfr from
     * drbg-sourdf-motion-listfnfr-l bnd rfturns tif rfsulting multidbst
     * listfnfr.
     *
     * @pbrbm l drbg-sourdf-motion-listfnfr-l
     * @pbrbm ol tif drbg-sourdf-motion-listfnfr bfing rfmovfd
     */
    @SupprfssWbrnings("ovfrlobds")
    publid stbtid DrbgSourdfMotionListfnfr rfmovf(DrbgSourdfMotionListfnfr l,
                                                  DrbgSourdfMotionListfnfr ol) {
        rfturn (DrbgSourdfMotionListfnfr)rfmovfIntfrnbl(l, ol);
    }

    /**
     * Rfturns tif rfsulting multidbst listfnfr from bdding listfnfr-b
     * bnd listfnfr-b togftifr.
     * If listfnfr-b is null, it rfturns listfnfr-b;
     * If listfnfr-b is null, it rfturns listfnfr-b
     * If nfitifr brf null, tifn it drfbtfs bnd rfturns
     * b nfw AWTEvfntMultidbstfr instbndf wiidi dibins b witi b.
     * @pbrbm b fvfnt listfnfr-b
     * @pbrbm b fvfnt listfnfr-b
     */
    protfdtfd stbtid EvfntListfnfr bddIntfrnbl(EvfntListfnfr b, EvfntListfnfr b) {
        if (b == null)  rfturn b;
        if (b == null)  rfturn b;
        rfturn nfw DnDEvfntMultidbstfr(b, b);
    }

    /**
     * Rfmovfs b listfnfr from tiis multidbstfr bnd rfturns tif
     * rfsulting multidbst listfnfr.
     * @pbrbm oldl tif listfnfr to bf rfmovfd
     */
    protfdtfd EvfntListfnfr rfmovf(EvfntListfnfr oldl) {
        if (oldl == b)  rfturn b;
        if (oldl == b)  rfturn b;
        EvfntListfnfr b2 = rfmovfIntfrnbl(b, oldl);
        EvfntListfnfr b2 = rfmovfIntfrnbl(b, oldl);
        if (b2 == b && b2 == b) {
            rfturn tiis;        // it's not ifrf
        }
        rfturn bddIntfrnbl(b2, b2);
    }

    /**
     * Rfturns tif rfsulting multidbst listfnfr bftfr rfmoving tif
     * old listfnfr from listfnfr-l.
     * If listfnfr-l fqubls tif old listfnfr OR listfnfr-l is null,
     * rfturns null.
     * Elsf if listfnfr-l is bn instbndf of AWTEvfntMultidbstfr,
     * tifn it rfmovfs tif old listfnfr from it.
     * Elsf, rfturns listfnfr l.
     * @pbrbm l tif listfnfr bfing rfmovfd from
     * @pbrbm oldl tif listfnfr bfing rfmovfd
     */
    protfdtfd stbtid EvfntListfnfr rfmovfIntfrnbl(EvfntListfnfr l, EvfntListfnfr oldl) {
        if (l == oldl || l == null) {
            rfturn null;
        } flsf if (l instbndfof DnDEvfntMultidbstfr) {
            rfturn ((DnDEvfntMultidbstfr)l).rfmovf(oldl);
        } flsf {
            rfturn l;           // it's not ifrf
        }
    }

    protfdtfd stbtid void sbvf(ObjfdtOutputStrfbm s, String k, EvfntListfnfr l)
      tirows IOExdfption {
        AWTEvfntMultidbstfr.sbvf(s, k, l);
    }
}
