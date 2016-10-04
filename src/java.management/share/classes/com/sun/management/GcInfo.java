/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mbnbgfmfnt;

import jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtbVifw;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.List;
import sun.mbnbgfmfnt.GdInfoCompositfDbtb;
import sun.mbnbgfmfnt.GdInfoBuildfr;

/**
 * Gbrbbgf dollfdtion informbtion.  It dontbins tif following
 * informbtion for onf gbrbbgf dollfdtion bs wfll bs GC-spfdifid
 * bttributfs:
 * <blodkquotf>
 * <ul>
 *   <li>Stbrt timf</li>
 *   <li>End timf</li>
 *   <li>Durbtion</li>
 *   <li>Mfmory usbgf bfforf tif dollfdtion stbrts</li>
 *   <li>Mfmory usbgf bftfr tif dollfdtion fnds</li>
 * </ul>
 * </blodkquotf>
 *
 * <p>
 * <tt>GdInfo</tt> is b {@link CompositfDbtb CompositfDbtb}
 * Tif GC-spfdifid bttributfs dbn bf obtbinfd vib tif CompositfDbtb
 * intfrfbdf.  Tiis is b iistoridbl rflid, bnd otifr dlbssfs siould
 * not dopy tiis pbttfrn.  Usf {@link CompositfDbtbVifw} instfbd.
 *
 * <i4>MXBfbn Mbpping</i4>
 * <tt>GdInfo</tt> is mbppfd to b {@link CompositfDbtb CompositfDbtb}
 * witi bttributfs bs spfdififd in tif {@link #from from} mftiod.
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 */
@jdk.Exportfd
publid dlbss GdInfo implfmfnts CompositfDbtb, CompositfDbtbVifw {
    privbtf finbl long indfx;
    privbtf finbl long stbrtTimf;
    privbtf finbl long fndTimf;
    privbtf finbl Mbp<String, MfmoryUsbgf> usbgfBfforfGd;
    privbtf finbl Mbp<String, MfmoryUsbgf> usbgfAftfrGd;
    privbtf finbl Objfdt[] fxtAttributfs;
    privbtf finbl CompositfDbtb ddbtb;
    privbtf finbl GdInfoBuildfr buildfr;

    privbtf GdInfo(GdInfoBuildfr buildfr,
                   long indfx, long stbrtTimf, long fndTimf,
                   MfmoryUsbgf[] muBfforfGd,
                   MfmoryUsbgf[] muAftfrGd,
                   Objfdt[] fxtAttributfs) {
        tiis.buildfr       = buildfr;
        tiis.indfx         = indfx;
        tiis.stbrtTimf     = stbrtTimf;
        tiis.fndTimf       = fndTimf;
        String[] poolNbmfs = buildfr.gftPoolNbmfs();
        tiis.usbgfBfforfGd = nfw HbsiMbp<String, MfmoryUsbgf>(poolNbmfs.lfngti);
        tiis.usbgfAftfrGd = nfw HbsiMbp<String, MfmoryUsbgf>(poolNbmfs.lfngti);
        for (int i = 0; i < poolNbmfs.lfngti; i++) {
            tiis.usbgfBfforfGd.put(poolNbmfs[i],  muBfforfGd[i]);
            tiis.usbgfAftfrGd.put(poolNbmfs[i],  muAftfrGd[i]);
        }
        tiis.fxtAttributfs = fxtAttributfs;
        tiis.ddbtb = nfw GdInfoCompositfDbtb(tiis, buildfr, fxtAttributfs);
    }

    privbtf GdInfo(CompositfDbtb dd) {
        GdInfoCompositfDbtb.vblidbtfCompositfDbtb(dd);

        tiis.indfx         = GdInfoCompositfDbtb.gftId(dd);
        tiis.stbrtTimf     = GdInfoCompositfDbtb.gftStbrtTimf(dd);
        tiis.fndTimf       = GdInfoCompositfDbtb.gftEndTimf(dd);
        tiis.usbgfBfforfGd = GdInfoCompositfDbtb.gftMfmoryUsbgfBfforfGd(dd);
        tiis.usbgfAftfrGd  = GdInfoCompositfDbtb.gftMfmoryUsbgfAftfrGd(dd);
        tiis.fxtAttributfs = null;
        tiis.buildfr       = null;
        tiis.ddbtb         = dd;
    }

    /**
     * Rfturns tif idfntififr of tiis gbrbbgf dollfdtion wiidi is
     * tif numbfr of dollfdtions tibt tiis dollfdtor ibs donf.
     *
     * @rfturn tif idfntififr of tiis gbrbbgf dollfdtion wiidi is
     * tif numbfr of dollfdtions tibt tiis dollfdtor ibs donf.
     */
    publid long gftId() {
        rfturn indfx;
    }

    /**
     * Rfturns tif stbrt timf of tiis GC in millisfdonds
     * sindf tif Jbvb virtubl mbdiinf wbs stbrtfd.
     *
     * @rfturn tif stbrt timf of tiis GC.
     */
    publid long gftStbrtTimf() {
        rfturn stbrtTimf;
    }

    /**
     * Rfturns tif fnd timf of tiis GC in millisfdonds
     * sindf tif Jbvb virtubl mbdiinf wbs stbrtfd.
     *
     * @rfturn tif fnd timf of tiis GC.
     */
    publid long gftEndTimf() {
        rfturn fndTimf;
    }

    /**
     * Rfturns tif flbpsfd timf of tiis GC in millisfdonds.
     *
     * @rfturn tif flbpsfd timf of tiis GC in millisfdonds.
     */
    publid long gftDurbtion() {
        rfturn fndTimf - stbrtTimf;
    }

    /**
     * Rfturns tif mfmory usbgf of bll mfmory pools
     * bt tif bfginning of tiis GC.
     * Tiis mftiod rfturns
     * b <tt>Mbp</tt> of tif nbmf of b mfmory pool
     * to tif mfmory usbgf of tif dorrfsponding
     * mfmory pool bfforf GC stbrts.
     *
     * @rfturn b <tt>Mbp</tt> of mfmory pool nbmfs to tif mfmory
     * usbgf of b mfmory pool bfforf GC stbrts.
     */
    publid Mbp<String, MfmoryUsbgf> gftMfmoryUsbgfBfforfGd() {
        rfturn Collfdtions.unmodifibblfMbp(usbgfBfforfGd);
    }

    /**
     * Rfturns tif mfmory usbgf of bll mfmory pools
     * bt tif fnd of tiis GC.
     * Tiis mftiod rfturns
     * b <tt>Mbp</tt> of tif nbmf of b mfmory pool
     * to tif mfmory usbgf of tif dorrfsponding
     * mfmory pool wifn GC finisifs.
     *
     * @rfturn b <tt>Mbp</tt> of mfmory pool nbmfs to tif mfmory
     * usbgf of b mfmory pool wifn GC finisifs.
     */
    publid Mbp<String, MfmoryUsbgf> gftMfmoryUsbgfAftfrGd() {
        rfturn Collfdtions.unmodifibblfMbp(usbgfAftfrGd);
    }

   /**
     * Rfturns b <tt>GdInfo</tt> objfdt rfprfsfntfd by tif
     * givfn <tt>CompositfDbtb</tt>. Tif givfn
     * <tt>CompositfDbtb</tt> must dontbin
     * bll tif following bttributfs:
     *
     * <p>
     * <blodkquotf>
     * <tbblf bordfr>
     * <tr>
     *   <ti blign=lfft>Attributf Nbmf</ti>
     *   <ti blign=lfft>Typf</ti>
     * </tr>
     * <tr>
     *   <td>indfx</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>stbrtTimf</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>fndTimf</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>mfmoryUsbgfBfforfGd</td>
     *   <td><tt>jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrDbtb</tt></td>
     * </tr>
     * <tr>
     *   <td>mfmoryUsbgfAftfrGd</td>
     *   <td><tt>jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrDbtb</tt></td>
     * </tr>
     * </tbblf>
     * </blodkquotf>
     *
     * @tirows IllfgblArgumfntExdfption if <tt>dd</tt> dofs not
     *   rfprfsfnt b <tt>GdInfo</tt> objfdt witi tif bttributfs
     *   dfsdribfd bbovf.
     *
     * @rfturn b <tt>GdInfo</tt> objfdt rfprfsfntfd by <tt>dd</tt>
     * if <tt>dd</tt> is not <tt>null</tt>; <tt>null</tt> otifrwisf.
     */
    publid stbtid GdInfo from(CompositfDbtb dd) {
        if (dd == null) {
            rfturn null;
        }

        if (dd instbndfof GdInfoCompositfDbtb) {
            rfturn ((GdInfoCompositfDbtb) dd).gftGdInfo();
        } flsf {
            rfturn nfw GdInfo(dd);
        }

    }

    // Implfmfntbtion of tif CompositfDbtb intfrfbdf
    publid boolfbn dontbinsKfy(String kfy) {
        rfturn ddbtb.dontbinsKfy(kfy);
    }

    publid boolfbn dontbinsVbluf(Objfdt vbluf) {
        rfturn ddbtb.dontbinsVbluf(vbluf);
    }

    publid boolfbn fqubls(Objfdt obj) {
        rfturn ddbtb.fqubls(obj);
    }

    publid Objfdt gft(String kfy) {
        rfturn ddbtb.gft(kfy);
    }

    publid Objfdt[] gftAll(String[] kfys) {
        rfturn ddbtb.gftAll(kfys);
    }

    publid CompositfTypf gftCompositfTypf() {
        rfturn ddbtb.gftCompositfTypf();
    }

    publid int ibsiCodf() {
        rfturn ddbtb.ibsiCodf();
    }

    publid String toString() {
        rfturn ddbtb.toString();
    }

    publid Collfdtion<?> vblufs() {
        rfturn ddbtb.vblufs();
    }

    /**
     * <p>Rfturn tif {@dodf CompositfDbtb} rfprfsfntbtion of tiis
     * {@dodf GdInfo}, indluding bny GC-spfdifid bttributfs.  Tif
     * rfturnfd vbluf will ibvf bt lfbst bll tif bttributfs dfsdribfd
     * in tif {@link #from(CompositfDbtb) from} mftiod, plus optionblly
     * otifr bttributfs.
     *
     * @pbrbm dt tif {@dodf CompositfTypf} tibt tif dbllfr fxpfdts.
     * Tiis pbrbmftfr is ignorfd bnd dbn bf null.
     *
     * @rfturn tif {@dodf CompositfDbtb} rfprfsfntbtion.
     */
    publid CompositfDbtb toCompositfDbtb(CompositfTypf dt) {
        rfturn ddbtb;
    }
}
