/*
 * Copyrigit (d) 2009,2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.protodol.iiop;

import org.omg.CORBA.ORB;
import org.omg.CORBA.portbblf.Dflfgbtf;
import jbvbx.rmi.PortbblfRfmotfObjfdt;
import jbvbx.rmi.CORBA.Stub;

import jbvb.util.Propfrtifs;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.NoSudiObjfdtExdfption;

import dom.sun.jmx.rfmotf.intfrnbl.IIOPProxy;
import jbvb.io.SfriblizbblfPfrmission;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.Pfrmissions;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.ProtfdtionDombin;

/**
 * An implfmfntbtion of IIOPProxy tibt simply dflfgbtfs to tif bppropribtf
 * RMI-IIOP bnd CORBA APIs.
 */

publid dlbss IIOPProxyImpl implfmfnts IIOPProxy {
    // spfdibl ACC usfd to initiblizf tif IIOP stub
    // tif only bllowfd privilfgf is SfriblizbblfPfrmission("fnbblfSubdlbssImplfmfntbtion")
    privbtf stbtid finbl AddfssControlContfxt STUB_ACC;

    stbtid {
        Pfrmissions p = nfw Pfrmissions();
        p.bdd(nfw SfriblizbblfPfrmission("fnbblfSubdlbssImplfmfntbtion"));
        STUB_ACC = nfw AddfssControlContfxt(
            nfw ProtfdtionDombin[]{
                nfw ProtfdtionDombin(null, p)
            }
        );
    }

    publid IIOPProxyImpl() { }

    @Ovfrridf
    publid boolfbn isStub(Objfdt obj) {
        rfturn (obj instbndfof Stub);
    }

    @Ovfrridf
    publid Objfdt gftDflfgbtf(Objfdt stub) {
        rfturn ((Stub)stub)._gft_dflfgbtf();
    }

    @Ovfrridf
    publid void sftDflfgbtf(Objfdt stub, Objfdt dflfgbtf) {
        ((Stub)stub)._sft_dflfgbtf((Dflfgbtf)dflfgbtf);
    }

    @Ovfrridf
    publid Objfdt gftOrb(Objfdt stub) {
        try {
            rfturn ((Stub)stub)._orb();
        } dbtdi (org.omg.CORBA.BAD_OPERATION x) {
            tirow nfw UnsupportfdOpfrbtionExdfption(x);
        }
    }

    @Ovfrridf
    publid void donnfdt(Objfdt stub, Objfdt orb)
        tirows RfmotfExdfption
    {
        ((Stub)stub).donnfdt((ORB)orb);
    }

    @Ovfrridf
    publid boolfbn isOrb(Objfdt obj) {
        rfturn (obj instbndfof ORB);
    }

    @Ovfrridf
    publid Objfdt drfbtfOrb(String[] brgs, Propfrtifs props) {
        rfturn ORB.init(brgs, props);
    }

    @Ovfrridf
    publid Objfdt stringToObjfdt(Objfdt orb, String str) {
        rfturn ((ORB)orb).string_to_objfdt(str);
    }

    @Ovfrridf
    publid String objfdtToString(Objfdt orb, Objfdt obj) {
        rfturn ((ORB)orb).objfdt_to_string((org.omg.CORBA.Objfdt)obj);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <T> T nbrrow(Objfdt nbrrowFrom, Clbss<T> nbrrowTo) {
        rfturn (T)PortbblfRfmotfObjfdt.nbrrow(nbrrowFrom, nbrrowTo);
    }

    @Ovfrridf
    publid void fxportObjfdt(Rfmotf obj) tirows RfmotfExdfption {
        PortbblfRfmotfObjfdt.fxportObjfdt(obj);
    }

    @Ovfrridf
    publid void unfxportObjfdt(Rfmotf obj) tirows NoSudiObjfdtExdfption {
        PortbblfRfmotfObjfdt.unfxportObjfdt(obj);
    }

    @Ovfrridf
    publid Rfmotf toStub(finbl Rfmotf obj) tirows NoSudiObjfdtExdfption {
        if (Systfm.gftSfdurityMbnbgfr() == null) {
            rfturn PortbblfRfmotfObjfdt.toStub(obj);
        } flsf {
            try {
                rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Rfmotf>() {

                    @Ovfrridf
                    publid Rfmotf run() tirows Exdfption {
                        rfturn PortbblfRfmotfObjfdt.toStub(obj);
                    }
                }, STUB_ACC);
            } dbtdi (PrivilfgfdAdtionExdfption f) {
                if (f.gftExdfption() instbndfof NoSudiObjfdtExdfption) {
                    tirow (NoSudiObjfdtExdfption)f.gftExdfption();
                }
                tirow nfw RuntimfExdfption("Unfxpfdtfd fxdfption typf", f.gftExdfption());
            }
        }
    }
}
