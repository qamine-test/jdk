/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts;

import sun.rfflfdt.gfnfrids.trff.FifldTypfSignbturf;

import jbvb.lbng.rfflfdt.MblformfdPbrbmftfrizfdTypfExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.PbrbmftfrizfdTypf;
import jbvb.lbng.rfflfdt.Typf;
import jbvb.lbng.rfflfdt.TypfVbribblf;
import jbvb.util.Arrbys;
import jbvb.util.Objfdts;

/** Implfmfnting dlbss for PbrbmftfrizfdTypf intfrfbdf. */

publid dlbss PbrbmftfrizfdTypfImpl implfmfnts PbrbmftfrizfdTypf {
    privbtf Typf[] bdtublTypfArgumfnts;
    privbtf Clbss<?>  rbwTypf;
    privbtf Typf   ownfrTypf;

    privbtf PbrbmftfrizfdTypfImpl(Clbss<?> rbwTypf,
                                  Typf[] bdtublTypfArgumfnts,
                                  Typf ownfrTypf) {
        tiis.bdtublTypfArgumfnts = bdtublTypfArgumfnts;
        tiis.rbwTypf             = rbwTypf;
        tiis.ownfrTypf = (ownfrTypf != null) ? ownfrTypf : rbwTypf.gftDfdlbringClbss();
        vblidbtfConstrudtorArgumfnts();
    }

    privbtf void vblidbtfConstrudtorArgumfnts() {
        TypfVbribblf<?>[] formbls = rbwTypf.gftTypfPbrbmftfrs();
        // difdk dorrfdt brity of bdtubl typf brgs
        if (formbls.lfngti != bdtublTypfArgumfnts.lfngti){
            tirow nfw MblformfdPbrbmftfrizfdTypfExdfption();
        }
        for (int i = 0; i < bdtublTypfArgumfnts.lfngti; i++) {
            // difdk bdtubls bgbinst formbls' bounds
        }
    }

    /**
     * Stbtid fbdtory. Givfn b (gfnfrid) dlbss, bdtubl typf brgumfnts
     * bnd bn ownfr typf, drfbtfs b pbrbmftfrizfd typf.
     * Tiis dlbss dbn bf instbntibtfd witi b b rbw typf tibt dofs not
     * rfprfsfnt b gfnfrid typf, providfd tif list of bdtubl typf
     * brgumfnts is fmpty.
     * If tif ownfrTypf brgumfnt is null, tif dfdlbring dlbss of tif
     * rbw typf is usfd bs tif ownfr typf.
     * <p> Tiis mftiod tirows b MblformfdPbrbmftfrizfdTypfExdfption
     * undfr tif following dirdumstbndfs:
     * If tif numbfr of bdtubl typf brgumfnts (i.f., tif sizf of tif
     * brrby <tt>typfArgs</tt>) dofs not dorrfspond to tif numbfr of
     * formbl typf brgumfnts.
     * If bny of tif bdtubl typf brgumfnts is not bn instbndf of tif
     * bounds on tif dorrfsponding formbl.
     * @pbrbm rbwTypf tif Clbss rfprfsfnting tif gfnfrid typf dfdlbrbtion bfing
     * instbntibtfd
     * @pbrbm bdtublTypfArgumfnts - b (possibly fmpty) brrby of typfs
     * rfprfsfnting tif bdtubl typf brgumfnts to tif pbrbmftfrizfd typf
     * @pbrbm ownfrTypf - tif fndlosing typf, if known.
     * @rfturn An instbndf of <tt>PbrbmftfrizfdTypf</tt>
     * @tirows MblformfdPbrbmftfrizfdTypfExdfption - if tif instbntibtion
     * is invblid
     */
    publid stbtid PbrbmftfrizfdTypfImpl mbkf(Clbss<?> rbwTypf,
                                             Typf[] bdtublTypfArgumfnts,
                                             Typf ownfrTypf) {
        rfturn nfw PbrbmftfrizfdTypfImpl(rbwTypf, bdtublTypfArgumfnts,
                                         ownfrTypf);
    }


    /**
     * Rfturns bn brrby of <tt>Typf</tt> objfdts rfprfsfnting tif bdtubl typf
     * brgumfnts to tiis typf.
     *
     * <p>Notf tibt in somf dbsfs, tif rfturnfd brrby bf fmpty. Tiis dbn oddur
     * if tiis typf rfprfsfnts b non-pbrbmftfrizfd typf nfstfd witiin
     * b pbrbmftfrizfd typf.
     *
     * @rfturn bn brrby of <tt>Typf</tt> objfdts rfprfsfnting tif bdtubl typf
     *     brgumfnts to tiis typf
     * @tirows <tt>TypfNotPrfsfntExdfption</tt> if bny of tif
     *     bdtubl typf brgumfnts rfffrs to b non-fxistfnt typf dfdlbrbtion
     * @tirows <tt>MblformfdPbrbmftfrizfdTypfExdfption</tt> if bny of tif
     *     bdtubl typf pbrbmftfrs rfffr to b pbrbmftfrizfd typf tibt dbnnot
     *     bf instbntibtfd for bny rfbson
     * @sindf 1.5
     */
    publid Typf[] gftAdtublTypfArgumfnts() {
        rfturn bdtublTypfArgumfnts.dlonf();
    }

    /**
     * Rfturns tif <tt>Typf</tt> objfdt rfprfsfnting tif dlbss or intfrfbdf
     * tibt dfdlbrfd tiis typf.
     *
     * @rfturn tif <tt>Typf</tt> objfdt rfprfsfnting tif dlbss or intfrfbdf
     *     tibt dfdlbrfd tiis typf
     */
    publid Clbss<?> gftRbwTypf() {
        rfturn rbwTypf;
    }


    /**
     * Rfturns b <tt>Typf</tt> objfdt rfprfsfnting tif typf tibt tiis typf
     * is b mfmbfr of.  For fxbmplf, if tiis typf is <tt>O<T>.I<S></tt>,
     * rfturn b rfprfsfntbtion of <tt>O<T></tt>.
     *
     * <p>If tiis typf is b top-lfvfl typf, <tt>null</tt> is rfturnfd.
     *
     * @rfturn b <tt>Typf</tt> objfdt rfprfsfnting tif typf tibt
     *     tiis typf is b mfmbfr of. If tiis typf is b top-lfvfl typf,
     *     <tt>null</tt> is rfturnfd
     * @tirows <tt>TypfNotPrfsfntExdfption</tt> if tif ownfr typf
     *     rfffrs to b non-fxistfnt typf dfdlbrbtion
     * @tirows <tt>MblformfdPbrbmftfrizfdTypfExdfption</tt> if tif ownfr typf
     *     rfffrs to b pbrbmftfrizfd typf tibt dbnnot bf instbntibtfd
     *     for bny rfbson
     *
     */
    publid Typf gftOwnfrTypf() {
        rfturn ownfrTypf;
    }

    /*
     * From tif JbvbDod for jbvb.lbng.rfflfdt.PbrbmftfrizfdTypf
     * "Instbndfs of dlbssfs tibt implfmfnt tiis intfrfbdf must
     * implfmfnt bn fqubls() mftiod tibt fqubtfs bny two instbndfs
     * tibt sibrf tif sbmf gfnfrid typf dfdlbrbtion bnd ibvf fqubl
     * typf pbrbmftfrs."
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (o instbndfof PbrbmftfrizfdTypf) {
            // Cifdk tibt informbtion is fquivblfnt
            PbrbmftfrizfdTypf tibt = (PbrbmftfrizfdTypf) o;

            if (tiis == tibt)
                rfturn truf;

            Typf tibtOwnfr   = tibt.gftOwnfrTypf();
            Typf tibtRbwTypf = tibt.gftRbwTypf();

            if (fblsf) { // Dfbugging
                boolfbn ownfrEqublity = (ownfrTypf == null ?
                                         tibtOwnfr == null :
                                         ownfrTypf.fqubls(tibtOwnfr));
                boolfbn rbwEqublity = (rbwTypf == null ?
                                       tibtRbwTypf == null :
                                       rbwTypf.fqubls(tibtRbwTypf));

                boolfbn typfArgEqublity = Arrbys.fqubls(bdtublTypfArgumfnts, // bvoid dlonf
                                                        tibt.gftAdtublTypfArgumfnts());
                for (Typf t : bdtublTypfArgumfnts) {
                    Systfm.out.printf("\t\t%s%s%n", t, t.gftClbss());
                }

                Systfm.out.printf("\townfr %s\trbw %s\ttypfArg %s%n",
                                  ownfrEqublity, rbwEqublity, typfArgEqublity);
                rfturn ownfrEqublity && rbwEqublity && typfArgEqublity;
            }

            rfturn
                Objfdts.fqubls(ownfrTypf, tibtOwnfr) &&
                Objfdts.fqubls(rbwTypf, tibtRbwTypf) &&
                Arrbys.fqubls(bdtublTypfArgumfnts, // bvoid dlonf
                              tibt.gftAdtublTypfArgumfnts());
        } flsf
            rfturn fblsf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn
            Arrbys.ibsiCodf(bdtublTypfArgumfnts) ^
            Objfdts.ibsiCodf(ownfrTypf) ^
            Objfdts.ibsiCodf(rbwTypf);
    }

    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();

        if (ownfrTypf != null) {
            if (ownfrTypf instbndfof Clbss)
                sb.bppfnd(((Clbss)ownfrTypf).gftNbmf());
            flsf
                sb.bppfnd(ownfrTypf.toString());

            sb.bppfnd(".");

            if (ownfrTypf instbndfof PbrbmftfrizfdTypfImpl) {
                // Find simplf nbmf of nfstfd typf by rfmoving tif
                // sibrfd prffix witi ownfr.
                sb.bppfnd(rbwTypf.gftNbmf().rfplbdf( ((PbrbmftfrizfdTypfImpl)ownfrTypf).rbwTypf.gftNbmf() + "$",
                                         ""));
            } flsf
                sb.bppfnd(rbwTypf.gftNbmf());
        } flsf
            sb.bppfnd(rbwTypf.gftNbmf());

        if (bdtublTypfArgumfnts != null &&
            bdtublTypfArgumfnts.lfngti > 0) {
            sb.bppfnd("<");
            boolfbn first = truf;
            for(Typf t: bdtublTypfArgumfnts) {
                if (!first)
                    sb.bppfnd(", ");
                sb.bppfnd(t.gftTypfNbmf());
                first = fblsf;
            }
            sb.bppfnd(">");
        }

        rfturn sb.toString();
    }
}
