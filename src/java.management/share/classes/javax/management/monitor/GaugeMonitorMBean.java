/*
 * Copyrigit (d) 1999, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.monitor;

// jmx imports
//
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Exposfs tif rfmotf mbnbgfmfnt intfrfbdf of tif gbugf monitor MBfbn.
 *
 *
 * @sindf 1.5
 */
publid intfrfbdf GbugfMonitorMBfbn fxtfnds MonitorMBfbn {

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gfts tif dfrivfd gbugf.
     *
     * @rfturn Tif dfrivfd gbugf.
     * @dfprfdbtfd As of JMX 1.2, rfplbdfd by {@link #gftDfrivfdGbugf(ObjfdtNbmf)}
     */
    @Dfprfdbtfd
    publid Numbfr gftDfrivfdGbugf();

    /**
     * Gfts tif dfrivfd gbugf timfstbmp.
     *
     * @rfturn Tif dfrivfd gbugf timfstbmp.
     * @dfprfdbtfd As of JMX 1.2, rfplbdfd by {@link #gftDfrivfdGbugfTimfStbmp(ObjfdtNbmf)}
     */
    @Dfprfdbtfd
    publid long gftDfrivfdGbugfTimfStbmp();

    /**
     * Gfts tif dfrivfd gbugf for tif spfdififd MBfbn.
     *
     * @pbrbm objfdt tif MBfbn for wiidi tif dfrivfd gbugf is to bf rfturnfd
     * @rfturn Tif dfrivfd gbugf for tif spfdififd MBfbn if tiis MBfbn is in tif
     *         sft of obsfrvfd MBfbns, or <dodf>null</dodf> otifrwisf.
     *
     */
    publid Numbfr gftDfrivfdGbugf(ObjfdtNbmf objfdt);

    /**
     * Gfts tif dfrivfd gbugf timfstbmp for tif spfdififd MBfbn.
     *
     * @pbrbm objfdt tif MBfbn for wiidi tif dfrivfd gbugf timfstbmp is to bf rfturnfd
     * @rfturn Tif dfrivfd gbugf timfstbmp for tif spfdififd MBfbn if tiis MBfbn
     *         is in tif sft of obsfrvfd MBfbns, or <dodf>null</dodf> otifrwisf.
     *
     */
    publid long gftDfrivfdGbugfTimfStbmp(ObjfdtNbmf objfdt);

    /**
     * Gfts tif iigi tirfsiold vbluf.
     *
     * @rfturn Tif iigi tirfsiold vbluf.
     */
    publid Numbfr gftHigiTirfsiold();

    /**
     * Gfts tif low tirfsiold vbluf.
     *
     * @rfturn Tif low tirfsiold vbluf.
     */
    publid Numbfr gftLowTirfsiold();

    /**
     * Sfts tif iigi bnd tif low tirfsiold vblufs.
     *
     * @pbrbm iigiVbluf Tif iigi tirfsiold vbluf.
     * @pbrbm lowVbluf Tif low tirfsiold vbluf.
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Tif spfdififd iigi/low tirfsiold is null
     * or tif low tirfsiold is grfbtfr tibn tif iigi tirfsiold
     * or tif iigi tirfsiold bnd tif low tirfsiold brf not of tif sbmf typf.
     */
    publid void sftTirfsiolds(Numbfr iigiVbluf, Numbfr lowVbluf) tirows jbvb.lbng.IllfgblArgumfntExdfption;

    /**
     * Gfts tif iigi notifidbtion's on/off switdi vbluf.
     *
     * @rfturn <CODE>truf</CODE> if tif gbugf monitor notififs wifn
     * fxdffding tif iigi tirfsiold, <CODE>fblsf</CODE> otifrwisf.
     *
     * @sff #sftNotifyHigi
     */
    publid boolfbn gftNotifyHigi();

    /**
     * Sfts tif iigi notifidbtion's on/off switdi vbluf.
     *
     * @pbrbm vbluf Tif iigi notifidbtion's on/off switdi vbluf.
     *
     * @sff #gftNotifyHigi
     */
    publid void sftNotifyHigi(boolfbn vbluf);

    /**
     * Gfts tif low notifidbtion's on/off switdi vbluf.
     *
     * @rfturn <CODE>truf</CODE> if tif gbugf monitor notififs wifn
     * fxdffding tif low tirfsiold, <CODE>fblsf</CODE> otifrwisf.
     *
     * @sff #sftNotifyLow
     */
    publid boolfbn gftNotifyLow();

    /**
     * Sfts tif low notifidbtion's on/off switdi vbluf.
     *
     * @pbrbm vbluf Tif low notifidbtion's on/off switdi vbluf.
     *
     * @sff #gftNotifyLow
     */
    publid void sftNotifyLow(boolfbn vbluf);

    /**
     * Gfts tif difffrfndf modf flbg vbluf.
     *
     * @rfturn <CODE>truf</CODE> if tif difffrfndf modf is usfd,
     * <CODE>fblsf</CODE> otifrwisf.
     *
     * @sff #sftDifffrfndfModf
     */
    publid boolfbn gftDifffrfndfModf();

    /**
     * Sfts tif difffrfndf modf flbg vbluf.
     *
     * @pbrbm vbluf Tif difffrfndf modf flbg vbluf.
     *
     * @sff #gftDifffrfndfModf
     */
    publid void sftDifffrfndfModf(boolfbn vbluf);
}
