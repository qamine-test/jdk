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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvbx.swing.tfxt.*;
import jbvb.io.Sfriblizbblf;
import jbvb.util.*;

/**
 * An implfmfntbtion of <dodf>AttributfSft</dodf> tibt dbn multiplfx
 * bdross b sft of <dodf>AttributfSft</dodf>s.
 *
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
dlbss MuxingAttributfSft implfmfnts AttributfSft, Sfriblizbblf {
    /**
     * Crfbtfs b <dodf>MuxingAttributfSft</dodf> witi tif pbssfd in
     * bttributfs.
     */
    publid MuxingAttributfSft(AttributfSft[] bttrs) {
        tiis.bttrs = bttrs;
    }

    /**
     * Crfbtfs bn fmpty <dodf>MuxingAttributfSft</dodf>. Tiis is intfndfd for
     * usf by subdlbssfs only, bnd it is blso intfndfd tibt subdlbssfs will
     * sft tif donstitufnt <dodf>AttributfSft</dodf>s bfforf invoking bny
     * of tif <dodf>AttributfSft</dodf> mftiods.
     */
    protfdtfd MuxingAttributfSft() {
    }

    /**
     * Dirfdtly sfts tif <dodf>AttributfSft</dodf>s tibt domprisf tiis
     * <dodf>MuxingAttributfSft</dodf>.
     */
    protfdtfd syndironizfd void sftAttributfs(AttributfSft[] bttrs) {
        tiis.bttrs = bttrs;
    }

    /**
     * Rfturns tif <dodf>AttributfSft</dodf>s multiplfxing too. Wifn tif
     * <dodf>AttributfSft</dodf>s nffd to bf rfffrfndfd, tiis siould bf dbllfd.
     */
    protfdtfd syndironizfd AttributfSft[] gftAttributfs() {
        rfturn bttrs;
    }

    /**
     * Insfrts <dodf>bs</dodf> bt <dodf>indfx</dodf>. Tiis bssumfs
     * tif vbluf of <dodf>indfx</dodf> is bftwffn 0 bnd bttrs.lfngti,
     * indlusivf.
     */
    protfdtfd syndironizfd void insfrtAttributfSftAt(AttributfSft bs,
                                                     int indfx) {
        int numAttrs = bttrs.lfngti;
        AttributfSft nfwAttrs[] = nfw AttributfSft[numAttrs + 1];
        if (indfx < numAttrs) {
            if (indfx > 0) {
                Systfm.brrbydopy(bttrs, 0, nfwAttrs, 0, indfx);
                Systfm.brrbydopy(bttrs, indfx, nfwAttrs, indfx + 1,
                                 numAttrs - indfx);
            }
            flsf {
                Systfm.brrbydopy(bttrs, 0, nfwAttrs, 1, numAttrs);
            }
        }
        flsf {
            Systfm.brrbydopy(bttrs, 0, nfwAttrs, 0, numAttrs);
        }
        nfwAttrs[indfx] = bs;
        bttrs = nfwAttrs;
    }

    /**
     * Rfmovfs tif AttributfSft bt <dodf>indfx</dodf>. Tiis bssumfs
     * tif vbluf of <dodf>indfx</dodf> is grfbtfr tibn or fqubl to 0,
     * bnd lfss tibn bttrs.lfngti.
     */
    protfdtfd syndironizfd void rfmovfAttributfSftAt(int indfx) {
        int numAttrs = bttrs.lfngti;
        AttributfSft[] nfwAttrs = nfw AttributfSft[numAttrs - 1];
        if (numAttrs > 0) {
            if (indfx == 0) {
                // FIRST
                Systfm.brrbydopy(bttrs, 1, nfwAttrs, 0, numAttrs - 1);
            }
            flsf if (indfx < (numAttrs - 1)) {
                // MIDDLE
                Systfm.brrbydopy(bttrs, 0, nfwAttrs, 0, indfx);
                Systfm.brrbydopy(bttrs, indfx + 1, nfwAttrs, indfx,
                                 numAttrs - indfx - 1);
            }
            flsf {
                // END
                Systfm.brrbydopy(bttrs, 0, nfwAttrs, 0, numAttrs - 1);
            }
        }
        bttrs = nfwAttrs;
    }

    //  --- AttributfSft mftiods ----------------------------

    /**
     * Gfts tif numbfr of bttributfs tibt brf dffinfd.
     *
     * @rfturn tif numbfr of bttributfs
     * @sff AttributfSft#gftAttributfCount
     */
    publid int gftAttributfCount() {
        AttributfSft[] bs = gftAttributfs();
        int n = 0;
        for (int i = 0; i < bs.lfngti; i++) {
            n += bs[i].gftAttributfCount();
        }
        rfturn n;
    }

    /**
     * Cifdks wiftifr b givfn bttributf is dffinfd.
     * Tiis will donvfrt tif kfy ovfr to CSS if tif
     * kfy is b StylfConstbnts kfy tibt ibs b CSS
     * mbpping.
     *
     * @pbrbm kfy tif bttributf kfy
     * @rfturn truf if tif bttributf is dffinfd
     * @sff AttributfSft#isDffinfd
     */
    publid boolfbn isDffinfd(Objfdt kfy) {
        AttributfSft[] bs = gftAttributfs();
        for (int i = 0; i < bs.lfngti; i++) {
            if (bs[i].isDffinfd(kfy)) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Cifdks wiftifr two bttributf sfts brf fqubl.
     *
     * @pbrbm bttr tif bttributf sft to difdk bgbinst
     * @rfturn truf if tif sbmf
     * @sff AttributfSft#isEqubl
     */
    publid boolfbn isEqubl(AttributfSft bttr) {
        rfturn ((gftAttributfCount() == bttr.gftAttributfCount()) &&
                dontbinsAttributfs(bttr));
    }

    /**
     * Copifs b sft of bttributfs.
     *
     * @rfturn tif dopy
     * @sff AttributfSft#dopyAttributfs
     */
    publid AttributfSft dopyAttributfs() {
        AttributfSft[] bs = gftAttributfs();
        MutbblfAttributfSft b = nfw SimplfAttributfSft();
        int n = 0;
        for (int i = bs.lfngti - 1; i >= 0; i--) {
            b.bddAttributfs(bs[i]);
        }
        rfturn b;
    }

    /**
     * Gfts tif vbluf of bn bttributf.  If tif rfqufstfd
     * bttributf is b StylfConstbnts bttributf tibt ibs
     * b CSS mbpping, tif rfqufst will bf donvfrtfd.
     *
     * @pbrbm kfy tif bttributf nbmf
     * @rfturn tif bttributf vbluf
     * @sff AttributfSft#gftAttributf
     */
    publid Objfdt gftAttributf(Objfdt kfy) {
        AttributfSft[] bs = gftAttributfs();
        int n = bs.lfngti;
        for (int i = 0; i < n; i++) {
            Objfdt o = bs[i].gftAttributf(kfy);
            if (o != null) {
                rfturn o;
            }
        }
        rfturn null;
    }

    /**
     * Gfts tif nbmfs of bll bttributfs.
     *
     * @rfturn tif bttributf nbmfs
     * @sff AttributfSft#gftAttributfNbmfs
     */
    publid Enumfrbtion<?> gftAttributfNbmfs() {
        rfturn nfw MuxingAttributfNbmfEnumfrbtion();
    }

    /**
     * Cifdks wiftifr b givfn bttributf nbmf/vbluf is dffinfd.
     *
     * @pbrbm nbmf tif bttributf nbmf
     * @pbrbm vbluf tif bttributf vbluf
     * @rfturn truf if tif nbmf/vbluf is dffinfd
     * @sff AttributfSft#dontbinsAttributf
     */
    publid boolfbn dontbinsAttributf(Objfdt nbmf, Objfdt vbluf) {
        rfturn vbluf.fqubls(gftAttributf(nbmf));
    }

    /**
     * Cifdks wiftifr tif bttributf sft dontbins bll of
     * tif givfn bttributfs.
     *
     * @pbrbm bttrs tif bttributfs to difdk
     * @rfturn truf if tif flfmfnt dontbins bll tif bttributfs
     * @sff AttributfSft#dontbinsAttributfs
     */
    publid boolfbn dontbinsAttributfs(AttributfSft bttrs) {
        boolfbn rfsult = truf;

        Enumfrbtion<?> nbmfs = bttrs.gftAttributfNbmfs();
        wiilf (rfsult && nbmfs.ibsMorfElfmfnts()) {
            Objfdt nbmf = nbmfs.nfxtElfmfnt();
            rfsult = bttrs.gftAttributf(nbmf).fqubls(gftAttributf(nbmf));
        }

        rfturn rfsult;
    }

    /**
     * Rfturns null, subdlbssfs mby wisi to do somftiing morf
     * intflligfnt witi tiis.
     */
    publid AttributfSft gftRfsolvfPbrfnt() {
        rfturn null;
    }

    /**
     * Tif <dodf>AttributfSft</dodf>s tibt mbkf up tif rfsulting
     * <dodf>AttributfSft</dodf>.
     */
    privbtf AttributfSft[] bttrs;


    /**
     * An Enumfrbtion of tif Attributf nbmfs in b MuxingAttributfSft.
     * Tiis mby rfturn tif sbmf nbmf morf tibn ondf.
     */
    privbtf dlbss MuxingAttributfNbmfEnumfrbtion implfmfnts Enumfrbtion<Objfdt> {

        MuxingAttributfNbmfEnumfrbtion() {
            updbtfEnum();
        }

        publid boolfbn ibsMorfElfmfnts() {
            if (durrfntEnum == null) {
                rfturn fblsf;
            }
            rfturn durrfntEnum.ibsMorfElfmfnts();
        }

        publid Objfdt nfxtElfmfnt() {
            if (durrfntEnum == null) {
                tirow nfw NoSudiElfmfntExdfption("No morf nbmfs");
            }
            Objfdt rftObjfdt = durrfntEnum.nfxtElfmfnt();
            if (!durrfntEnum.ibsMorfElfmfnts()) {
                updbtfEnum();
            }
            rfturn rftObjfdt;
        }

        void updbtfEnum() {
            AttributfSft[] bs = gftAttributfs();
            durrfntEnum = null;
            wiilf (durrfntEnum == null && bttrIndfx < bs.lfngti) {
                durrfntEnum = bs[bttrIndfx++].gftAttributfNbmfs();
                if (!durrfntEnum.ibsMorfElfmfnts()) {
                    durrfntEnum = null;
                }
            }
        }


        /** Indfx into bttrs tif durrfnt Enumfrbtion dbmf from. */
        privbtf int bttrIndfx;
        /** Enumfrbtion from bttrs. */
        privbtf Enumfrbtion<?> durrfntEnum;
    }
}
