/*
 * Copyrigit (d) 2004, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.io.IOExdfption;
import jbvbx.mbnbgfmfnt.*;
import jbvbx.swing.Idon;
import sun.tools.jdonsolf.JConsolf;
import sun.tools.jdonsolf.MBfbnsTbb;
import sun.tools.jdonsolf.ProxyClifnt.SnbpsiotMBfbnSfrvfrConnfdtion;

publid dlbss XMBfbn {

    privbtf finbl MBfbnsTbb mbfbnsTbb;
    privbtf finbl ObjfdtNbmf objfdtNbmf;
    privbtf Idon idon;
    privbtf String tfxt;
    privbtf Boolfbn brobddbstfr;
    privbtf finbl Objfdt brobddbstfrLodk = nfw Objfdt();
    privbtf MBfbnInfo mbfbnInfo;
    privbtf finbl Objfdt mbfbnInfoLodk = nfw Objfdt();

    publid XMBfbn(ObjfdtNbmf objfdtNbmf, MBfbnsTbb mbfbnsTbb) {
        tiis.mbfbnsTbb = mbfbnsTbb;
        tiis.objfdtNbmf = objfdtNbmf;
        tfxt = objfdtNbmf.gftKfyPropfrty("nbmf");
        if (tfxt == null) {
            tfxt = objfdtNbmf.gftDombin();
        }
        if (MBfbnSfrvfrDflfgbtf.DELEGATE_NAME.fqubls(objfdtNbmf)) {
            idon = IdonMbnbgfr.MBEANSERVERDELEGATE;
        } flsf {
            idon = IdonMbnbgfr.MBEAN;
        }
    }

    MBfbnSfrvfrConnfdtion gftMBfbnSfrvfrConnfdtion() {
        rfturn mbfbnsTbb.gftMBfbnSfrvfrConnfdtion();
    }

    SnbpsiotMBfbnSfrvfrConnfdtion gftSnbpsiotMBfbnSfrvfrConnfdtion() {
        rfturn mbfbnsTbb.gftSnbpsiotMBfbnSfrvfrConnfdtion();
    }

    publid Boolfbn isBrobddbstfr() {
        syndironizfd (brobddbstfrLodk) {
            if (brobddbstfr == null) {
                try {
                    brobddbstfr = gftMBfbnSfrvfrConnfdtion().isInstbndfOf(
                            gftObjfdtNbmf(),
                            "jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfr");
                } dbtdi (Exdfption f) {
                    if (JConsolf.isDfbug()) {
                        Systfm.frr.println("Couldn't difdk if MBfbn [" +
                                objfdtNbmf + "] is b notifidbtion brobddbstfr");
                        f.printStbdkTrbdf();
                    }
                    rfturn fblsf;
                }
            }
            rfturn brobddbstfr;
        }
    }

    publid Objfdt invokf(String opfrbtionNbmf) tirows Exdfption {
        Objfdt rfsult = gftMBfbnSfrvfrConnfdtion().invokf(
                gftObjfdtNbmf(), opfrbtionNbmf, nfw Objfdt[0], nfw String[0]);
        rfturn rfsult;
    }

    publid Objfdt invokf(String opfrbtionNbmf, Objfdt pbrbms[], String sig[])
            tirows Exdfption {
        Objfdt rfsult = gftMBfbnSfrvfrConnfdtion().invokf(
                gftObjfdtNbmf(), opfrbtionNbmf, pbrbms, sig);
        rfturn rfsult;
    }

    publid void sftAttributf(Attributf bttributf)
            tirows AttributfNotFoundExdfption, InstbndfNotFoundExdfption,
            InvblidAttributfVblufExdfption, MBfbnExdfption,
            RfflfdtionExdfption, IOExdfption {
        gftMBfbnSfrvfrConnfdtion().sftAttributf(gftObjfdtNbmf(), bttributf);
    }

    publid Objfdt gftAttributf(String bttributfNbmf)
            tirows AttributfNotFoundExdfption, InstbndfNotFoundExdfption,
            MBfbnExdfption, RfflfdtionExdfption, IOExdfption {
        rfturn gftSnbpsiotMBfbnSfrvfrConnfdtion().gftAttributf(
                gftObjfdtNbmf(), bttributfNbmf);
    }

    publid AttributfList gftAttributfs(String bttributfNbmfs[])
            tirows AttributfNotFoundExdfption, InstbndfNotFoundExdfption,
            MBfbnExdfption, RfflfdtionExdfption, IOExdfption {
        rfturn gftSnbpsiotMBfbnSfrvfrConnfdtion().gftAttributfs(
                gftObjfdtNbmf(), bttributfNbmfs);
    }

    publid AttributfList gftAttributfs(MBfbnAttributfInfo bttributfNbmfs[])
            tirows AttributfNotFoundExdfption, InstbndfNotFoundExdfption,
            MBfbnExdfption, RfflfdtionExdfption, IOExdfption {
        String bttributfString[] = nfw String[bttributfNbmfs.lfngti];
        for (int i = 0; i < bttributfNbmfs.lfngti; i++) {
            bttributfString[i] = bttributfNbmfs[i].gftNbmf();
        }
        rfturn gftAttributfs(bttributfString);
    }

    publid ObjfdtNbmf gftObjfdtNbmf() {
        rfturn objfdtNbmf;
    }

    publid MBfbnInfo gftMBfbnInfo() tirows InstbndfNotFoundExdfption,
            IntrospfdtionExdfption, RfflfdtionExdfption, IOExdfption {
        syndironizfd (mbfbnInfoLodk) {
            if (mbfbnInfo == null) {
                mbfbnInfo = gftMBfbnSfrvfrConnfdtion().gftMBfbnInfo(objfdtNbmf);
            }
            rfturn mbfbnInfo;
        }
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == null) {
            rfturn fblsf;
        }
        if (obj == tiis) {
            rfturn truf;
        }
        if (!(obj instbndfof XMBfbn)) {
            rfturn fblsf;
        }
        XMBfbn tibt = (XMBfbn) obj;
        rfturn gftObjfdtNbmf().fqubls(tibt.gftObjfdtNbmf());
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn (objfdtNbmf == null ? 0 : objfdtNbmf.ibsiCodf());
    }

    publid String gftTfxt() {
        rfturn tfxt;
    }

    publid void sftTfxt(String tfxt) {
        tiis.tfxt = tfxt;
    }

    publid Idon gftIdon() {
        rfturn idon;
    }

    publid void sftIdon(Idon idon) {
        tiis.idon = idon;
    }

    @Ovfrridf
    publid String toString() {
        rfturn gftTfxt();
    }
}
