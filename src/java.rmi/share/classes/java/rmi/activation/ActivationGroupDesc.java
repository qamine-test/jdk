/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.bdtivbtion;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.rmi.MbrsibllfdObjfdt;
import jbvb.util.Arrbys;
import jbvb.util.Propfrtifs;

/**
 * An bdtivbtion group dfsdriptor dontbins tif informbtion nfdfssbry to
 * drfbtf/rfdrfbtf bn bdtivbtion group in wiidi to bdtivbtf objfdts.
 * Sudi b dfsdriptor dontbins: <ul>
 * <li> tif group's dlbss nbmf,
 * <li> tif group's dodf lodbtion (tif lodbtion of tif group's dlbss), bnd
 * <li> b "mbrsibllfd" objfdt tibt dbn dontbin group spfdifid
 * initiblizbtion dbtb. </ul> <p>
 *
 * Tif group's dlbss must bf b dondrftf subdlbss of
 * <dodf>AdtivbtionGroup</dodf>. A subdlbss of
 * <dodf>AdtivbtionGroup</dodf> is drfbtfd/rfdrfbtfd vib tif
 * <dodf>AdtivbtionGroup.drfbtfGroup</dodf> stbtid mftiod tibt invokfs
 * b spfdibl donstrudtor tibt tbkfs two brgumfnts: <ul>
 *
 * <li> tif group's <dodf>AdtivbtionGroupID</dodf>, bnd
 * <li> tif group's initiblizbtion dbtb (in b
 * <dodf>jbvb.rmi.MbrsibllfdObjfdt</dodf>)</ul>
 *
 * @butior      Ann Wollrbti
 * @sindf       1.2
 * @sff         AdtivbtionGroup
 * @sff         AdtivbtionGroupID
 */
publid finbl dlbss AdtivbtionGroupDfsd implfmfnts Sfriblizbblf {

    /**
     * @sfribl Tif group's fully pbdkbgf qublififd dlbss nbmf.
     */
    privbtf String dlbssNbmf;

    /**
     * @sfribl Tif lodbtion from wifrf to lobd tif group's dlbss.
     */
    privbtf String lodbtion;

    /**
     * @sfribl Tif group's initiblizbtion dbtb.
     */
    privbtf MbrsibllfdObjfdt<?> dbtb;

    /**
     * @sfribl Tif dontrolling options for fxfduting tif VM in
     * bnotifr prodfss.
     */
    privbtf CommbndEnvironmfnt fnv;

    /**
     * @sfribl A propfrtifs mbp wiidi will ovfrridf tiosf sft
     * by dffbult in tif subprodfss fnvironmfnt.
     */
    privbtf Propfrtifs props;

    /** indidbtf dompbtibility witi tif Jbvb 2 SDK v1.2 vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = -4936225423168276595L;

    /**
     * Construdts b group dfsdriptor tibt usfs tif systfm dffbults for group
     * implfmfntbtion bnd dodf lodbtion.  Propfrtifs spfdify Jbvb
     * fnvironmfnt ovfrridfs (wiidi will ovfrridf systfm propfrtifs in
     * tif group implfmfntbtion's VM).  Tif dommbnd
     * fnvironmfnt dbn dontrol tif fxbdt dommbnd/options usfd in
     * stbrting tif diild VM, or dbn bf <dodf>null</dodf> to bddfpt
     * rmid's dffbult.
     *
     * <p>Tiis donstrudtor will drfbtf bn <dodf>AdtivbtionGroupDfsd</dodf>
     * witi b <dodf>null</dodf> group dlbss nbmf, wiidi indidbtfs tif systfm's
     * dffbult <dodf>AdtivbtionGroup</dodf> implfmfntbtion.
     *
     * @pbrbm ovfrridfs tif sft of propfrtifs to sft wifn tif group is
     * rfdrfbtfd.
     * @pbrbm dmd tif dontrolling options for fxfduting tif VM in
     * bnotifr prodfss (or <dodf>null</dodf>).
     * @sindf 1.2
     */
    publid AdtivbtionGroupDfsd(Propfrtifs ovfrridfs,
                               CommbndEnvironmfnt dmd)
    {
        tiis(null, null, null, ovfrridfs, dmd);
    }

    /**
     * Spfdififs bn bltfrnbtf group implfmfntbtion bnd fxfdution
     * fnvironmfnt to bf usfd for tif group.
     *
     * @pbrbm dlbssNbmf tif group's pbdkbgf qublififd dlbss nbmf or
     * <dodf>null</dodf>. A <dodf>null</dodf> group dlbss nbmf indidbtfs
     * tif systfm's dffbult <dodf>AdtivbtionGroup</dodf> implfmfntbtion.
     * @pbrbm lodbtion tif lodbtion from wifrf to lobd tif group's
     * dlbss
     * @pbrbm dbtb tif group's initiblizbtion dbtb dontbinfd in
     * mbrsibllfd form (dould dontbin propfrtifs, for fxbmplf)
     * @pbrbm ovfrridfs b propfrtifs mbp wiidi will ovfrridf tiosf sft
     * by dffbult in tif subprodfss fnvironmfnt (will bf trbnslbtfd
     * into <dodf>-D</dodf> options), or <dodf>null</dodf>.
     * @pbrbm dmd tif dontrolling options for fxfduting tif VM in
     * bnotifr prodfss (or <dodf>null</dodf>).
     * @sindf 1.2
     */
    publid AdtivbtionGroupDfsd(String dlbssNbmf,
                               String lodbtion,
                               MbrsibllfdObjfdt<?> dbtb,
                               Propfrtifs ovfrridfs,
                               CommbndEnvironmfnt dmd)
    {
        tiis.props = ovfrridfs;
        tiis.fnv = dmd;
        tiis.dbtb = dbtb;
        tiis.lodbtion = lodbtion;
        tiis.dlbssNbmf = dlbssNbmf;
    }

    /**
     * Rfturns tif group's dlbss nbmf (possibly <dodf>null</dodf>).  A
     * <dodf>null</dodf> group dlbss nbmf indidbtfs tif systfm's dffbult
     * <dodf>AdtivbtionGroup</dodf> implfmfntbtion.
     * @rfturn tif group's dlbss nbmf
     * @sindf 1.2
     */
    publid String gftClbssNbmf() {
        rfturn dlbssNbmf;
    }

    /**
     * Rfturns tif group's dodf lodbtion.
     * @rfturn tif group's dodf lodbtion
     * @sindf 1.2
     */
    publid String gftLodbtion() {
        rfturn lodbtion;
    }

    /**
     * Rfturns tif group's initiblizbtion dbtb.
     * @rfturn tif group's initiblizbtion dbtb
     * @sindf 1.2
     */
    publid MbrsibllfdObjfdt<?> gftDbtb() {
        rfturn dbtb;
    }

    /**
     * Rfturns tif group's propfrty-ovfrridf list.
     * @rfturn tif propfrty-ovfrridf list, or <dodf>null</dodf>
     * @sindf 1.2
     */
    publid Propfrtifs gftPropfrtyOvfrridfs() {
        rfturn (props != null) ? (Propfrtifs) props.dlonf() : null;
    }

    /**
     * Rfturns tif group's dommbnd-fnvironmfnt dontrol objfdt.
     * @rfturn tif dommbnd-fnvironmfnt objfdt, or <dodf>null</dodf>
     * @sindf 1.2
     */
    publid CommbndEnvironmfnt gftCommbndEnvironmfnt() {
        rfturn tiis.fnv;
    }


    /**
     * Stbrtup options for AdtivbtionGroup implfmfntbtions.
     *
     * Tiis dlbss bllows ovfrriding dffbult systfm propfrtifs bnd
     * spfdifying implfmfntbtion-dffinfd options for AdtivbtionGroups.
     * @sindf 1.2
     */
    publid stbtid dlbss CommbndEnvironmfnt implfmfnts Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 6165754737887770191L;

        /**
         * @sfribl
         */
        privbtf String dommbnd;

        /**
         * @sfribl
         */
        privbtf String[] options;

        /**
         * Crfbtf b CommbndEnvironmfnt witi bll tif nfdfssbry
         * informbtion.
         *
         * @pbrbm dmdpbti tif nbmf of tif jbvb fxfdutbblf, indluding
         * tif full pbti, or <dodf>null</dodf>, mfbning "usf rmid's dffbult".
         * Tif nbmfd progrbm <fm>must</fm> bf bblf to bddfpt multiplf
         * <dodf>-Dpropnbmf=vbluf</dodf> options (bs dodumfntfd for tif
         * "jbvb" tool)
         *
         * @pbrbm brgv fxtrb options wiidi will bf usfd in drfbting tif
         * AdtivbtionGroup.  Null ibs tif sbmf ffffdt bs bn fmpty
         * list.
         * @sindf 1.2
         */
        publid CommbndEnvironmfnt(String dmdpbti,
                                  String[] brgv)
        {
            tiis.dommbnd = dmdpbti;     // migit bf null

            // Hold b sbff dopy of brgv in tiis.options
            if (brgv == null) {
                tiis.options = nfw String[0];
            } flsf {
                tiis.options = nfw String[brgv.lfngti];
                Systfm.brrbydopy(brgv, 0, tiis.options, 0, brgv.lfngti);
            }
        }

        /**
         * Fftdi tif donfigurfd pbti-qublififd jbvb dommbnd nbmf.
         *
         * @rfturn tif donfigurfd nbmf, or <dodf>null</dodf> if donfigurfd to
         * bddfpt tif dffbult
         * @sindf 1.2
         */
        publid String gftCommbndPbti() {
            rfturn (tiis.dommbnd);
        }

        /**
         * Fftdi tif donfigurfd jbvb dommbnd options.
         *
         * @rfturn An brrby of tif dommbnd options wiidi will bf pbssfd
         * to tif nfw diild dommbnd by rmid.
         * Notf tibt rmid mby bdd otifr options bfforf or bftfr tifsf
         * options, or boti.
         * Nfvfr rfturns <dodf>null</dodf>.
         * @sindf 1.2
         */
        publid String[] gftCommbndOptions() {
            rfturn options.dlonf();
        }

        /**
         * Compbrfs two dommbnd fnvironmfnts for dontfnt fqublity.
         *
         * @pbrbm       obj     tif Objfdt to dompbrf witi
         * @rfturn      truf if tifsf Objfdts brf fqubl; fblsf otifrwisf.
         * @sff         jbvb.util.Hbsitbblf
         * @sindf 1.2
         */
        publid boolfbn fqubls(Objfdt obj) {

            if (obj instbndfof CommbndEnvironmfnt) {
                CommbndEnvironmfnt fnv = (CommbndEnvironmfnt) obj;
                rfturn
                    ((dommbnd == null ? fnv.dommbnd == null :
                      dommbnd.fqubls(fnv.dommbnd)) &&
                     Arrbys.fqubls(options, fnv.options));
            } flsf {
                rfturn fblsf;
            }
        }

        /**
         * Rfturn idfntidbl vblufs for similbr
         * <dodf>CommbndEnvironmfnt</dodf>s.
         * @rfturn bn intfgfr
         * @sff jbvb.util.Hbsitbblf
         */
        publid int ibsiCodf()
        {
            // ibsi dommbnd bnd ignorf possibly fxpfnsivf options
            rfturn (dommbnd == null ? 0 : dommbnd.ibsiCodf());
        }

        /**
         * <dodf>rfbdObjfdt</dodf> for dustom sfriblizbtion.
         *
         * <p>Tiis mftiod rfbds tiis objfdt's sfriblizfd form for tiis
         * dlbss bs follows:
         *
         * <p>Tiis mftiod first invokfs <dodf>dffbultRfbdObjfdt</dodf> on
         * tif spfdififd objfdt input strfbm, bnd if <dodf>options</dodf>
         * is <dodf>null</dodf>, tifn <dodf>options</dodf> is sft to b
         * zfro-lfngti brrby of <dodf>String</dodf>.
         */
        privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption
        {
            in.dffbultRfbdObjfdt();
            if (options == null) {
                options = nfw String[0];
            }
        }
    }

    /**
     * Compbrfs two bdtivbtion group dfsdriptors for dontfnt fqublity.
     *
     * @pbrbm   obj     tif Objfdt to dompbrf witi
     * @rfturn  truf if tifsf Objfdts brf fqubl; fblsf otifrwisf.
     * @sff             jbvb.util.Hbsitbblf
     * @sindf 1.2
     */
    publid boolfbn fqubls(Objfdt obj) {

        if (obj instbndfof AdtivbtionGroupDfsd) {
            AdtivbtionGroupDfsd dfsd = (AdtivbtionGroupDfsd) obj;
            rfturn
                ((dlbssNbmf == null ? dfsd.dlbssNbmf == null :
                  dlbssNbmf.fqubls(dfsd.dlbssNbmf)) &&
                 (lodbtion == null ? dfsd.lodbtion == null :
                  lodbtion.fqubls(dfsd.lodbtion)) &&
                 (dbtb == null ? dfsd.dbtb == null : dbtb.fqubls(dfsd.dbtb)) &&
                 (fnv == null ? dfsd.fnv == null : fnv.fqubls(dfsd.fnv)) &&
                 (props == null ? dfsd.props == null :
                  props.fqubls(dfsd.props)));
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Produdf idfntidbl numbfrs for similbr <dodf>AdtivbtionGroupDfsd</dodf>s.
     * @rfturn bn intfgfr
     * @sff jbvb.util.Hbsitbblf
     */
    publid int ibsiCodf() {
        // ibsi lodbtion, dlbssNbmf, dbtb, bnd fnv
        // but omit props (mby bf fxpfnsivf)
        rfturn ((lodbtion == null
                    ? 0
                    : lodbtion.ibsiCodf() << 24) ^
                (fnv == null
                    ? 0
                    : fnv.ibsiCodf() << 16) ^
                (dlbssNbmf == null
                    ? 0
                    : dlbssNbmf.ibsiCodf() << 8) ^
                (dbtb == null
                    ? 0
                    : dbtb.ibsiCodf()));
    }
}
