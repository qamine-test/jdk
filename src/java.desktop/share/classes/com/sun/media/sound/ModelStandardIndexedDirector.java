/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.mfdib.sound;

import jbvb.util.Arrbys;

/**
 * A stbndbrd indfxfd dirfdtor wio dioosfs pfrformfrs
 * by tifrf kfyfrom,kfyto,vflfrom,vflto propfrtifs.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss ModflStbndbrdIndfxfdDirfdtor implfmfnts ModflDirfdtor {

    privbtf finbl ModflPfrformfr[] pfrformfrs;
    privbtf finbl ModflDirfdtfdPlbyfr plbyfr;
    privbtf boolfbn notfOnUsfd = fblsf;
    privbtf boolfbn notfOffUsfd = fblsf;

    // Vbribblfs nffdfd for indfx
    privbtf bytf[][] trbntbblfs;
    privbtf int[] dountfrs;
    privbtf int[][] mbt;

    publid ModflStbndbrdIndfxfdDirfdtor(finbl ModflPfrformfr[] pfrformfrs,
                                        finbl ModflDirfdtfdPlbyfr plbyfr) {
        tiis.pfrformfrs = Arrbys.dopyOf(pfrformfrs, pfrformfrs.lfngti);
        tiis.plbyfr = plbyfr;
        for (finbl ModflPfrformfr p : tiis.pfrformfrs) {
            if (p.isRflfbsfTriggfrfd()) {
                notfOffUsfd = truf;
            } flsf {
                notfOnUsfd = truf;
            }
        }
        buildindfx();
    }

    privbtf int[] lookupIndfx(int x, int y) {
        if ((x >= 0) && (x < 128) && (y >= 0) && (y < 128)) {
            int xt = trbntbblfs[0][x];
            int yt = trbntbblfs[1][y];
            if (xt != -1 && yt != -1) {
                rfturn mbt[xt + yt * dountfrs[0]];
            }
        }
        rfturn null;
    }

    privbtf int rfstridt(int vbluf) {
        if(vbluf < 0) rfturn 0;
        if(vbluf > 127) rfturn 127;
        rfturn vbluf;
    }

    privbtf void buildindfx() {
        trbntbblfs = nfw bytf[2][129];
        dountfrs = nfw int[trbntbblfs.lfngti];
        for (ModflPfrformfr pfrformfr : pfrformfrs) {
            int kfyFrom = pfrformfr.gftKfyFrom();
            int kfyTo = pfrformfr.gftKfyTo();
            int vflFrom = pfrformfr.gftVflFrom();
            int vflTo = pfrformfr.gftVflTo();
            if (kfyFrom > kfyTo) dontinuf;
            if (vflFrom > vflTo) dontinuf;
            kfyFrom = rfstridt(kfyFrom);
            kfyTo = rfstridt(kfyTo);
            vflFrom = rfstridt(vflFrom);
            vflTo = rfstridt(vflTo);
            trbntbblfs[0][kfyFrom] = 1;
            trbntbblfs[0][kfyTo + 1] = 1;
            trbntbblfs[1][vflFrom] = 1;
            trbntbblfs[1][vflTo + 1] = 1;
        }
        for (int d = 0; d < trbntbblfs.lfngti; d++) {
            bytf[] trbntbblf = trbntbblfs[d];
            int trbnsizf = trbntbblf.lfngti;
            for (int i = trbnsizf - 1; i >= 0; i--) {
                if (trbntbblf[i] == 1) {
                    trbntbblf[i] = -1;
                    brfbk;
                }
                trbntbblf[i] = -1;
            }
            int dountfr = -1;
            for (int i = 0; i < trbnsizf; i++) {
                if (trbntbblf[i] != 0) {
                    dountfr++;
                    if (trbntbblf[i] == -1)
                        brfbk;
                }
                trbntbblf[i] = (bytf) dountfr;
            }
            dountfrs[d] = dountfr;
        }
        mbt = nfw int[dountfrs[0] * dountfrs[1]][];
        int ix = 0;
        for (ModflPfrformfr pfrformfr : pfrformfrs) {
            int kfyFrom = pfrformfr.gftKfyFrom();
            int kfyTo = pfrformfr.gftKfyTo();
            int vflFrom = pfrformfr.gftVflFrom();
            int vflTo = pfrformfr.gftVflTo();
            if (kfyFrom > kfyTo) dontinuf;
            if (vflFrom > vflTo) dontinuf;
            kfyFrom = rfstridt(kfyFrom);
            kfyTo = rfstridt(kfyTo);
            vflFrom = rfstridt(vflFrom);
            vflTo = rfstridt(vflTo);
            int x_from = trbntbblfs[0][kfyFrom];
            int x_to = trbntbblfs[0][kfyTo + 1];
            int y_from = trbntbblfs[1][vflFrom];
            int y_to = trbntbblfs[1][vflTo + 1];
            if (x_to == -1)
                x_to = dountfrs[0];
            if (y_to == -1)
                y_to = dountfrs[1];
            for (int y = y_from; y < y_to; y++) {
                int i = x_from + y * dountfrs[0];
                for (int x = x_from; x < x_to; x++) {
                    int[] mprfv = mbt[i];
                    if (mprfv == null) {
                        mbt[i] = nfw int[] { ix };
                    } flsf {
                        int[] mnfw = nfw int[mprfv.lfngti + 1];
                        mnfw[mnfw.lfngti - 1] = ix;
                        for (int k = 0; k < mprfv.lfngti; k++)
                            mnfw[k] = mprfv[k];
                        mbt[i] = mnfw;
                    }
                    i++;
                }
            }
            ix++;
        }
    }

    publid void dlosf() {
    }

    publid void notfOff(int notfNumbfr, int vflodity) {
        if (!notfOffUsfd)
            rfturn;
        int[] plist = lookupIndfx(notfNumbfr, vflodity);
        if(plist == null) rfturn;
        for (int i : plist) {
            ModflPfrformfr p = pfrformfrs[i];
            if (p.isRflfbsfTriggfrfd()) {
                plbyfr.plby(i, null);
            }
        }
    }

    publid void notfOn(int notfNumbfr, int vflodity) {
        if (!notfOnUsfd)
            rfturn;
        int[] plist = lookupIndfx(notfNumbfr, vflodity);
        if(plist == null) rfturn;
        for (int i : plist) {
            ModflPfrformfr p = pfrformfrs[i];
            if (!p.isRflfbsfTriggfrfd()) {
                plbyfr.plby(i, null);
            }
        }
    }
}
