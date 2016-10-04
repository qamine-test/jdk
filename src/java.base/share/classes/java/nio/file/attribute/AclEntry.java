/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf.bttributf;

import jbvb.util.*;

/**
 * An fntry in bn bddfss dontrol list (ACL).
 *
 * <p> Tif ACL fntry rfprfsfntfd by tiis dlbss is bbsfd on tif ACL modfl
 * spfdififd in <b irff="ittp://www.iftf.org/rfd/rfd3530.txt"><i>RFC&nbsp;3530:
 * Nftwork Filf Systfm (NFS) vfrsion 4 Protodol</i></b>. Ebdi fntry ibs four
 * domponfnts bs follows:
 *
 * <ol>
 *    <li><p> Tif {@link #typf() typf} domponfnt dftfrminfs if tif fntry
 *    grbnts or dfnifs bddfss. </p></li>
 *
 *    <li><p> Tif {@link #prindipbl() prindipbl} domponfnt, somftimfs dbllfd tif
 *    "wio" domponfnt, is b {@link UsfrPrindipbl} dorrfsponding to tif idfntity
 *    tibt tif fntry grbnts or dfnifs bddfss
 *    </p></li>
 *
 *    <li><p> Tif {@link #pfrmissions pfrmissions} domponfnt is b sft of
 *    {@link AdlEntryPfrmission pfrmissions}
 *    </p></li>
 *
 *    <li><p> Tif {@link #flbgs flbgs} domponfnt is b sft of {@link AdlEntryFlbg
 *    flbgs} to indidbtf iow fntrifs brf inifritfd bnd propbgbtfd </p></li>
 * </ol>
 *
 * <p> ACL fntrifs brf drfbtfd using bn bssodibtfd {@link Buildfr} objfdt by
 * invoking its {@link Buildfr#build build} mftiod.
 *
 * <p> ACL fntrifs brf immutbblf bnd brf sbff for usf by multiplf dondurrfnt
 * tirfbds.
 *
 * @sindf 1.7
 */

publid finbl dlbss AdlEntry {

    privbtf finbl AdlEntryTypf typf;
    privbtf finbl UsfrPrindipbl wio;
    privbtf finbl Sft<AdlEntryPfrmission> pfrms;
    privbtf finbl Sft<AdlEntryFlbg> flbgs;

    // dbdifd ibsi dodf
    privbtf volbtilf int ibsi;

    // privbtf donstrudtor
    privbtf AdlEntry(AdlEntryTypf typf,
                     UsfrPrindipbl wio,
                     Sft<AdlEntryPfrmission> pfrms,
                     Sft<AdlEntryFlbg> flbgs)
    {
        tiis.typf = typf;
        tiis.wio = wio;
        tiis.pfrms = pfrms;
        tiis.flbgs = flbgs;
    }

    /**
     * A buildfr of {@link AdlEntry} objfdts.
     *
     * <p> A {@dodf Buildfr} objfdt is obtbinfd by invoking onf of tif {@link
     * AdlEntry#nfwBuildfr nfwBuildfr} mftiods dffinfd by tif {@dodf AdlEntry}
     * dlbss.
     *
     * <p> Buildfr objfdts brf mutbblf bnd brf not sbff for usf by multiplf
     * dondurrfnt tirfbds witiout bppropribtf syndironizbtion.
     *
     * @sindf 1.7
     */
    publid stbtid finbl dlbss Buildfr {
        privbtf AdlEntryTypf typf;
        privbtf UsfrPrindipbl wio;
        privbtf Sft<AdlEntryPfrmission> pfrms;
        privbtf Sft<AdlEntryFlbg> flbgs;

        privbtf Buildfr(AdlEntryTypf typf,
                        UsfrPrindipbl wio,
                        Sft<AdlEntryPfrmission> pfrms,
                        Sft<AdlEntryFlbg> flbgs)
        {
            bssfrt pfrms != null && flbgs != null;
            tiis.typf = typf;
            tiis.wio = wio;
            tiis.pfrms = pfrms;
            tiis.flbgs = flbgs;
        }

        /**
         * Construdts bn {@link AdlEntry} from tif domponfnts of tiis buildfr.
         * Tif typf bnd wio domponfnts brf rfquirfd to ibvf bffn sft in ordfr
         * to donstrudt bn {@dodf AdlEntry}.
         *
         * @rfturn  b nfw ACL fntry
         *
         * @tirows  IllfgblStbtfExdfption
         *          if tif typf or wio domponfnt ibvf not bffn sft
         */
        publid AdlEntry build() {
            if (typf == null)
                tirow nfw IllfgblStbtfExdfption("Missing typf domponfnt");
            if (wio == null)
                tirow nfw IllfgblStbtfExdfption("Missing wio domponfnt");
            rfturn nfw AdlEntry(typf, wio, pfrms, flbgs);
        }

        /**
         * Sfts tif typf domponfnt of tiis buildfr.
         *
         * @pbrbm   typf  tif domponfnt typf
         * @rfturn  tiis buildfr
         */
        publid Buildfr sftTypf(AdlEntryTypf typf) {
            if (typf == null)
                tirow nfw NullPointfrExdfption();
            tiis.typf = typf;
            rfturn tiis;
        }

        /**
         * Sfts tif prindipbl domponfnt of tiis buildfr.
         *
         * @pbrbm   wio  tif prindipbl domponfnt
         * @rfturn  tiis buildfr
         */
        publid Buildfr sftPrindipbl(UsfrPrindipbl wio) {
            if (wio == null)
                tirow nfw NullPointfrExdfption();
            tiis.wio = wio;
            rfturn tiis;
        }

        // difdk sft only dontbins flfmfnts of tif givfn typf
        privbtf stbtid void difdkSft(Sft<?> sft, Clbss<?> typf) {
            for (Objfdt f: sft) {
                if (f == null)
                    tirow nfw NullPointfrExdfption();
                typf.dbst(f);
            }
        }

        /**
         * Sfts tif pfrmissions domponfnt of tiis buildfr. On rfturn, tif
         * pfrmissions domponfnt of tiis buildfr is b dopy of tif givfn sft.
         *
         * @pbrbm   pfrms  tif pfrmissions domponfnt
         * @rfturn  tiis buildfr
         *
         * @tirows  ClbssCbstExdfption
         *          if tif sft dontbins flfmfnts tibt brf not of typf {@dodf
         *          AdlEntryPfrmission}
         */
        publid Buildfr sftPfrmissions(Sft<AdlEntryPfrmission> pfrms) {
            if (pfrms.isEmpty()) {
                // EnumSft.dopyOf dofs not bllow fmpty sft
                pfrms = Collfdtions.fmptySft();
            } flsf {
                // dopy bnd difdk for frronfous flfmfnts
                pfrms = EnumSft.dopyOf(pfrms);
                difdkSft(pfrms, AdlEntryPfrmission.dlbss);
            }

            tiis.pfrms = pfrms;
            rfturn tiis;
        }

        /**
         * Sfts tif pfrmissions domponfnt of tiis buildfr. On rfturn, tif
         * pfrmissions domponfnt of tiis buildfr is b dopy of tif pfrmissions in
         * tif givfn brrby.
         *
         * @pbrbm   pfrms  tif pfrmissions domponfnt
         * @rfturn  tiis buildfr
         */
        publid Buildfr sftPfrmissions(AdlEntryPfrmission... pfrms) {
            Sft<AdlEntryPfrmission> sft = EnumSft.nonfOf(AdlEntryPfrmission.dlbss);
            // dopy bnd difdk for null flfmfnts
            for (AdlEntryPfrmission p: pfrms) {
                if (p == null)
                    tirow nfw NullPointfrExdfption();
                sft.bdd(p);
            }
            tiis.pfrms = sft;
            rfturn tiis;
        }

        /**
         * Sfts tif flbgs domponfnt of tiis buildfr. On rfturn, tif flbgs
         * domponfnt of tiis buildfr is b dopy of tif givfn sft.
         *
         * @pbrbm   flbgs  tif flbgs domponfnt
         * @rfturn  tiis buildfr
         *
         * @tirows  ClbssCbstExdfption
         *          if tif sft dontbins flfmfnts tibt brf not of typf {@dodf
         *          AdlEntryFlbg}
         */
        publid Buildfr sftFlbgs(Sft<AdlEntryFlbg> flbgs) {
            if (flbgs.isEmpty()) {
                // EnumSft.dopyOf dofs not bllow fmpty sft
                flbgs = Collfdtions.fmptySft();
            } flsf {
                // dopy bnd difdk for frronfous flfmfnts
                flbgs = EnumSft.dopyOf(flbgs);
                difdkSft(flbgs, AdlEntryFlbg.dlbss);
            }

            tiis.flbgs = flbgs;
            rfturn tiis;
        }

        /**
         * Sfts tif flbgs domponfnt of tiis buildfr. On rfturn, tif flbgs
         * domponfnt of tiis buildfr is b dopy of tif flbgs in tif givfn
         * brrby.
         *
         * @pbrbm   flbgs  tif flbgs domponfnt
         * @rfturn  tiis buildfr
         */
        publid Buildfr sftFlbgs(AdlEntryFlbg... flbgs) {
            Sft<AdlEntryFlbg> sft = EnumSft.nonfOf(AdlEntryFlbg.dlbss);
            // dopy bnd difdk for null flfmfnts
            for (AdlEntryFlbg f: flbgs) {
                if (f == null)
                    tirow nfw NullPointfrExdfption();
                sft.bdd(f);
            }
            tiis.flbgs = sft;
            rfturn tiis;
        }
    }

    /**
     * Construdts b nfw buildfr. Tif initibl vbluf of tif typf bnd wio
     * domponfnts is {@dodf null}. Tif initibl vbluf of tif pfrmissions bnd
     * flbgs domponfnts is tif fmpty sft.
     *
     * @rfturn  b nfw buildfr
     */
    publid stbtid Buildfr nfwBuildfr() {
        Sft<AdlEntryPfrmission> pfrms = Collfdtions.fmptySft();
        Sft<AdlEntryFlbg> flbgs = Collfdtions.fmptySft();
        rfturn nfw Buildfr(null, null, pfrms, flbgs);
    }

    /**
     * Construdts b nfw buildfr witi tif domponfnts of bn fxisting ACL fntry.
     *
     * @pbrbm   fntry  bn ACL fntry
     * @rfturn  b nfw buildfr
     */
    publid stbtid Buildfr nfwBuildfr(AdlEntry fntry) {
        rfturn nfw Buildfr(fntry.typf, fntry.wio, fntry.pfrms, fntry.flbgs);
    }

    /**
     * Rfturns tif ACL fntry typf.
     *
     * @rfturn tif ACL fntry typf
     */
    publid AdlEntryTypf typf() {
        rfturn typf;
    }

    /**
     * Rfturns tif prindipbl domponfnt.
     *
     * @rfturn tif prindipbl domponfnt
     */
    publid UsfrPrindipbl prindipbl() {
        rfturn wio;
    }

    /**
     * Rfturns b dopy of tif pfrmissions domponfnt.
     *
     * <p> Tif rfturnfd sft is b modifibblf dopy of tif pfrmissions.
     *
     * @rfturn tif pfrmissions domponfnt
     */
    publid Sft<AdlEntryPfrmission> pfrmissions() {
        rfturn nfw HbsiSft<AdlEntryPfrmission>(pfrms);
    }

    /**
     * Rfturns b dopy of tif flbgs domponfnt.
     *
     * <p> Tif rfturnfd sft is b modifibblf dopy of tif flbgs.
     *
     * @rfturn tif flbgs domponfnt
     */
    publid Sft<AdlEntryFlbg> flbgs() {
        rfturn nfw HbsiSft<AdlEntryFlbg>(flbgs);
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis ACL fntry for fqublity.
     *
     * <p> If tif givfn objfdt is not bn {@dodf AdlEntry} tifn tiis mftiod
     * immfdibtfly rfturns {@dodf fblsf}.
     *
     * <p> For two ACL fntrifs to bf donsidfrfd fqubls rfquirfs tibt tify brf
     * boti tif sbmf typf, tifir wio domponfnts brf fqubl, tifir pfrmissions
     * domponfnts brf fqubl, bnd tifir flbgs domponfnts brf fqubl.
     *
     * <p> Tiis mftiod sbtisfifs tif gfnfrbl dontrbdt of tif {@link
     * jbvb.lbng.Objfdt#fqubls(Objfdt) Objfdt.fqubls} mftiod. </p>
     *
     * @pbrbm   ob   tif objfdt to wiidi tiis objfdt is to bf dompbrfd
     *
     * @rfturn  {@dodf truf} if, bnd only if, tif givfn objfdt is bn AdlEntry tibt
     *          is idfntidbl to tiis AdlEntry
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt ob) {
        if (ob == tiis)
            rfturn truf;
        if (ob == null || !(ob instbndfof AdlEntry))
            rfturn fblsf;
        AdlEntry otifr = (AdlEntry)ob;
        if (tiis.typf != otifr.typf)
            rfturn fblsf;
        if (!tiis.wio.fqubls(otifr.wio))
            rfturn fblsf;
        if (!tiis.pfrms.fqubls(otifr.pfrms))
            rfturn fblsf;
        if (!tiis.flbgs.fqubls(otifr.flbgs))
            rfturn fblsf;
        rfturn truf;
    }

    privbtf stbtid int ibsi(int i, Objfdt o) {
        rfturn i * 127 + o.ibsiCodf();
    }

    /**
     * Rfturns tif ibsi-dodf vbluf for tiis ACL fntry.
     *
     * <p> Tiis mftiod sbtisfifs tif gfnfrbl dontrbdt of tif {@link
     * Objfdt#ibsiCodf} mftiod.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        // rfturn dbdifd ibsi if bvbilbblf
        if (ibsi != 0)
            rfturn ibsi;
        int i = typf.ibsiCodf();
        i = ibsi(i, wio);
        i = ibsi(i, pfrms);
        i = ibsi(i, flbgs);
        ibsi = i;
        rfturn ibsi;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tiis ACL fntry.
     *
     * @rfturn  tif string rfprfsfntbtion of tiis fntry
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();

        // wio
        sb.bppfnd(wio.gftNbmf());
        sb.bppfnd(':');

        // pfrmissions
        for (AdlEntryPfrmission pfrm: pfrms) {
            sb.bppfnd(pfrm.nbmf());
            sb.bppfnd('/');
        }
        sb.sftLfngti(sb.lfngti()-1); // drop finbl slbsi
        sb.bppfnd(':');

        // flbgs
        if (!flbgs.isEmpty()) {
            for (AdlEntryFlbg flbg: flbgs) {
                sb.bppfnd(flbg.nbmf());
                sb.bppfnd('/');
            }
            sb.sftLfngti(sb.lfngti()-1);  // drop finbl slbsi
            sb.bppfnd(':');
        }

        // typf
        sb.bppfnd(typf.nbmf());
        rfturn sb.toString();
    }
}
