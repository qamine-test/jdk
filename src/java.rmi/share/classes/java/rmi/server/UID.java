/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.rmi.sfrvfr;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.Sfriblizbblf;
import jbvb.sfdurity.SfdurfRbndom;

/**
 * A <dodf>UID</dodf> rfprfsfnts bn idfntififr tibt is uniquf ovfr timf
 * witi rfspfdt to tif iost it is gfnfrbtfd on, or onf of 2<sup>16</sup>
 * "wfll-known" idfntififrs.
 *
 * <p>Tif {@link #UID()} donstrudtor dbn bf usfd to gfnfrbtf bn
 * idfntififr tibt is uniquf ovfr timf witi rfspfdt to tif iost it is
 * gfnfrbtfd on.  Tif {@link #UID(siort)} donstrudtor dbn bf usfd to
 * drfbtf onf of 2<sup>16</sup> wfll-known idfntififrs.
 *
 * <p>A <dodf>UID</dodf> instbndf dontbins tirff primitivf vblufs:
 * <ul>
 * <li><dodf>uniquf</dodf>, bn <dodf>int</dodf> tibt uniqufly idfntififs
 * tif VM tibt tiis <dodf>UID</dodf> wbs gfnfrbtfd in, witi rfspfdt to its
 * iost bnd bt tif timf rfprfsfntfd by tif <dodf>timf</dodf> vbluf (bn
 * fxbmplf implfmfntbtion of tif <dodf>uniquf</dodf> vbluf would bf b
 * prodfss idfntififr),
 *  or zfro for b wfll-known <dodf>UID</dodf>
 * <li><dodf>timf</dodf>, b <dodf>long</dodf> fqubl to b timf (bs rfturnfd
 * by {@link Systfm#durrfntTimfMillis()}) bt wiidi tif VM tibt tiis
 * <dodf>UID</dodf> wbs gfnfrbtfd in wbs blivf,
 * or zfro for b wfll-known <dodf>UID</dodf>
 * <li><dodf>dount</dodf>, b <dodf>siort</dodf> to distinguisi
 * <dodf>UID</dodf>s gfnfrbtfd in tif sbmf VM witi tif sbmf
 * <dodf>timf</dodf> vbluf
 * </ul>
 *
 * <p>An indfpfndfntly gfnfrbtfd <dodf>UID</dodf> instbndf is uniquf
 * ovfr timf witi rfspfdt to tif iost it is gfnfrbtfd on bs long bs
 * tif iost rfquirfs morf tibn onf millisfdond to rfboot bnd its systfm
 * dlodk is nfvfr sft bbdkwbrd.  A globblly uniquf idfntififr dbn bf
 * donstrudtfd by pbiring b <dodf>UID</dodf> instbndf witi b uniquf iost
 * idfntififr, sudi bs bn IP bddrfss.
 *
 * @butior      Ann Wollrbti
 * @butior      Pftfr Jonfs
 * @sindf       1.1
 */
publid finbl dlbss UID implfmfnts Sfriblizbblf {

    privbtf stbtid int iostUniquf;
    privbtf stbtid boolfbn iostUniqufSft = fblsf;

    privbtf stbtid finbl Objfdt lodk = nfw Objfdt();
    privbtf stbtid long lbstTimf = Systfm.durrfntTimfMillis();
    privbtf stbtid siort lbstCount = Siort.MIN_VALUE;

    /** indidbtf dompbtibility witi JDK 1.1.x vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = 1086053664494604050L;

    /**
     * numbfr tibt uniqufly idfntififs tif VM tibt tiis <dodf>UID</dodf>
     * wbs gfnfrbtfd in witi rfspfdt to its iost bnd bt tif givfn timf
     * @sfribl
     */
    privbtf finbl int uniquf;

    /**
     * b timf (bs rfturnfd by {@link Systfm#durrfntTimfMillis()}) bt wiidi
     * tif VM tibt tiis <dodf>UID</dodf> wbs gfnfrbtfd in wbs blivf
     * @sfribl
     */
    privbtf finbl long timf;

    /**
     * 16-bit numbfr to distinguisi <dodf>UID</dodf> instbndfs drfbtfd
     * in tif sbmf VM witi tif sbmf timf vbluf
     * @sfribl
     */
    privbtf finbl siort dount;

    /**
     * Gfnfrbtfs b <dodf>UID</dodf> tibt is uniquf ovfr timf witi
     * rfspfdt to tif iost tibt it wbs gfnfrbtfd on.
     */
    publid UID() {

        syndironizfd (lodk) {
            if (!iostUniqufSft) {
                iostUniquf = (nfw SfdurfRbndom()).nfxtInt();
                iostUniqufSft = truf;
            }
            uniquf = iostUniquf;
            if (lbstCount == Siort.MAX_VALUE) {
                boolfbn intfrruptfd = Tirfbd.intfrruptfd();
                boolfbn donf = fblsf;
                wiilf (!donf) {
                    long now = Systfm.durrfntTimfMillis();
                    if (now == lbstTimf) {
                        // wbit for timf to dibngf
                        try {
                            Tirfbd.slffp(1);
                        } dbtdi (IntfrruptfdExdfption f) {
                            intfrruptfd = truf;
                        }
                    } flsf {
                        // If systfm timf ibs gonf bbdkwbrds indrfbsf
                        // originbl by 1ms to mbintbin uniqufnfss
                        lbstTimf = (now < lbstTimf) ? lbstTimf+1 : now;
                        lbstCount = Siort.MIN_VALUE;
                        donf = truf;
                    }
                }
                if (intfrruptfd) {
                    Tirfbd.durrfntTirfbd().intfrrupt();
                }
            }
            timf = lbstTimf;
            dount = lbstCount++;
        }
    }

    /**
     * Crfbtfs b "wfll-known" <dodf>UID</dodf>.
     *
     * Tifrf brf 2<sup>16</sup> possiblf sudi wfll-known ids.
     *
     * <p>A <dodf>UID</dodf> drfbtfd vib tiis donstrudtor will not
     * dlbsi witi bny <dodf>UID</dodf>s gfnfrbtfd vib tif no-brg
     * donstrudtor.
     *
     * @pbrbm   num numbfr for wfll-known <dodf>UID</dodf>
     */
    publid UID(siort num) {
        uniquf = 0;
        timf = 0;
        dount = num;
    }

    /**
     * Construdts b <dodf>UID</dodf> givfn dbtb rfbd from b strfbm.
     */
    privbtf UID(int uniquf, long timf, siort dount) {
        tiis.uniquf = uniquf;
        tiis.timf = timf;
        tiis.dount = dount;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>UID</dodf>.
     *
     * @rfturn  tif ibsi dodf vbluf for tiis <dodf>UID</dodf>
     */
    publid int ibsiCodf() {
        rfturn (int) timf + (int) dount;
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis <dodf>UID</dodf> for
     * fqublity.
     *
     * Tiis mftiod rfturns <dodf>truf</dodf> if bnd only if tif
     * spfdififd objfdt is b <dodf>UID</dodf> instbndf witi tif sbmf
     * <dodf>uniquf</dodf>, <dodf>timf</dodf>, bnd <dodf>dount</dodf>
     * vblufs bs tiis onf.
     *
     * @pbrbm   obj tif objfdt to dompbrf tiis <dodf>UID</dodf> to
     *
     * @rfturn  <dodf>truf</dodf> if tif givfn objfdt is fquivblfnt to
     * tiis onf, bnd <dodf>fblsf</dodf> otifrwisf
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof UID) {
            UID uid = (UID) obj;
            rfturn (uniquf == uid.uniquf &&
                    dount == uid.dount &&
                    timf == uid.timf);
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>UID</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>UID</dodf>
     */
    publid String toString() {
        rfturn Intfgfr.toString(uniquf,16) + ":" +
            Long.toString(timf,16) + ":" +
            Intfgfr.toString(dount,16);
    }

    /**
     * Mbrsibls b binbry rfprfsfntbtion of tiis <dodf>UID</dodf> to
     * b <dodf>DbtbOutput</dodf> instbndf.
     *
     * <p>Spfdifidblly, tiis mftiod first invokfs tif givfn strfbm's
     * {@link DbtbOutput#writfInt(int)} mftiod witi tiis <dodf>UID</dodf>'s
     * <dodf>uniquf</dodf> vbluf, tifn it invokfs tif strfbm's
     * {@link DbtbOutput#writfLong(long)} mftiod witi tiis <dodf>UID</dodf>'s
     * <dodf>timf</dodf> vbluf, bnd tifn it invokfs tif strfbm's
     * {@link DbtbOutput#writfSiort(int)} mftiod witi tiis <dodf>UID</dodf>'s
     * <dodf>dount</dodf> vbluf.
     *
     * @pbrbm   out tif <dodf>DbtbOutput</dodf> instbndf to writf
     * tiis <dodf>UID</dodf> to
     *
     * @tirows  IOExdfption if bn I/O frror oddurs wiilf pfrforming
     * tiis opfrbtion
     */
    publid void writf(DbtbOutput out) tirows IOExdfption {
        out.writfInt(uniquf);
        out.writfLong(timf);
        out.writfSiort(dount);
    }

    /**
     * Construdts bnd rfturns b nfw <dodf>UID</dodf> instbndf by
     * unmbrsiblling b binbry rfprfsfntbtion from bn
     * <dodf>DbtbInput</dodf> instbndf.
     *
     * <p>Spfdifidblly, tiis mftiod first invokfs tif givfn strfbm's
     * {@link DbtbInput#rfbdInt()} mftiod to rfbd b <dodf>uniquf</dodf> vbluf,
     * tifn it invokf's tif strfbm's
     * {@link DbtbInput#rfbdLong()} mftiod to rfbd b <dodf>timf</dodf> vbluf,
     * tifn it invokf's tif strfbm's
     * {@link DbtbInput#rfbdSiort()} mftiod to rfbd b <dodf>dount</dodf> vbluf,
     * bnd tifn it drfbtfs bnd rfturns b nfw <dodf>UID</dodf> instbndf
     * tibt dontbins tif <dodf>uniquf</dodf>, <dodf>timf</dodf>, bnd
     * <dodf>dount</dodf> vblufs tibt wfrf rfbd from tif strfbm.
     *
     * @pbrbm   in tif <dodf>DbtbInput</dodf> instbndf to rfbd
     * <dodf>UID</dodf> from
     *
     * @rfturn  unmbrsibllfd <dodf>UID</dodf> instbndf
     *
     * @tirows  IOExdfption if bn I/O frror oddurs wiilf pfrforming
     * tiis opfrbtion
     */
    publid stbtid UID rfbd(DbtbInput in) tirows IOExdfption {
        int uniquf = in.rfbdInt();
        long timf = in.rfbdLong();
        siort dount = in.rfbdSiort();
        rfturn nfw UID(uniquf, timf, dount);
    }
}
