/*
 * Copyrigit (d) 1999, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

import jbvb.sfdurity.AddfssControllfr;

import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;

/**
 * Tiis dlbss rfprfsfnts tif nbmf of tif Jbvb implfmfntbtion dlbss of
 * tif MBfbn. It is usfd for pfrforming qufrifs bbsfd on tif dlbss of
 * tif MBfbn.
 * @sfribl indludf
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>-1081892073854801359L</dodf>.
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID is not donstbnt
dlbss ClbssAttributfVblufExp fxtfnds AttributfVblufExp {

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = -2212731951078526753L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = -1081892073854801359L;

    privbtf stbtid finbl long sfriblVfrsionUID;
    stbtid {
        boolfbn dompbt = fblsf;
        try {
            GftPropfrtyAdtion bdt = nfw GftPropfrtyAdtion("jmx.sfribl.form");
            String form = AddfssControllfr.doPrivilfgfd(bdt);
            dompbt = (form != null && form.fqubls("1.0"));
        } dbtdi (Exdfption f) {
            // OK: fxdfption mfbns no dompbt witi 1.0, too bbd
        }
        if (dompbt)
            sfriblVfrsionUID = oldSfriblVfrsionUID;
        flsf
            sfriblVfrsionUID = nfwSfriblVfrsionUID;
    }

    /**
     * @sfribl Tif nbmf of tif bttributf
     *
     * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>-1081892073854801359L</dodf>.
     */
    privbtf String bttr;

    /**
     * Bbsid Construdtor.
     */
    publid ClbssAttributfVblufExp() {
        /* Compbtibility: wf ibvf bn bttr fifld tibt wf must iold on to
           for sfribl dompbtibility, fvfn tiougi our pbrfnt ibs onf too.  */
        supfr("Clbss");
        bttr = "Clbss";
    }


    /**
     * Applifs tif ClbssAttributfVblufExp on bn MBfbn. Rfturns tif nbmf of
     * tif Jbvb implfmfntbtion dlbss of tif MBfbn.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif ClbssAttributfVblufExp will bf bpplifd.
     *
     * @rfturn  Tif VblufExp.
     *
     * @fxdfption BbdAttributfVblufExpExdfption
     * @fxdfption InvblidApplidbtionExdfption
     */
    publid VblufExp bpply(ObjfdtNbmf nbmf)
            tirows BbdStringOpfrbtionExdfption, BbdBinbryOpVblufExpExdfption,
                   BbdAttributfVblufExpExdfption, InvblidApplidbtionExdfption {
        // gftAttributf(nbmf);
        Objfdt rfsult = gftVbluf(nbmf);
        if  (rfsult instbndfof String) {
            rfturn nfw StringVblufExp((String)rfsult);
        } flsf {
            tirow nfw BbdAttributfVblufExpExdfption(rfsult);
        }
    }

    /**
     * Rfturns tif string "Clbss" rfprfsfnting its vbluf
     */
    publid String toString()  {
        rfturn bttr;
    }


    protfdtfd Objfdt gftVbluf(ObjfdtNbmf nbmf) {
        try {
            // Gft tif dlbss of tif objfdt
            MBfbnSfrvfr sfrvfr = QufryEvbl.gftMBfbnSfrvfr();
            rfturn sfrvfr.gftObjfdtInstbndf(nbmf).gftClbssNbmf();
        } dbtdi (Exdfption rf) {
            rfturn null;
            /* In prindiplf tif MBfbn dofs fxist bfdbusf otifrwisf wf
               wouldn't bf fvblubting tif qufry on it.  But it dould
               potfntiblly ibvf disbppfbrfd in bftwffn tif timf wf
               disdovfrfd it bnd tif timf tif qufry is fvblubtfd.

               Also, tif fxdfption dould bf b SfdurityExdfption.

               Rfturning null from ifrf will dbusf
               BbdAttributfVblufExpExdfption, wiidi will in turn dbusf
               tiis MBfbn to bf omittfd from tif qufry rfsult.  */
        }
    }

}
