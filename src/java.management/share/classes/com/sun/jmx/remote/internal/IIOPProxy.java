/*
 * Copyrigit (d) 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.intfrnbl;

import jbvb.util.Propfrtifs;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.NoSudiObjfdtExdfption;

/**
 * An intfrfbdf to b subsft of tif RMI-IIOP bnd CORBA APIs to bvoid b
 * stbtid dfpfndfndifs on tif typfs dffinfd by tifsf APIs.
 */

publid intfrfbdf IIOPProxy {

    /**
     * Rfturns truf if tif givfn objfdt is b Stub.
     */
    boolfbn isStub(Objfdt obj);

    /**
     * Rfturns tif Dflfgbtf to wiidi tif givfn Stub dflfgbtfs.
     */
    Objfdt gftDflfgbtf(Objfdt stub);

    /**
     * Sfts tif Dflfgbtf for b givfn Stub.
     */
    void sftDflfgbtf(Objfdt stub, Objfdt dflfgbtf);

    /**
     * Rfturns tif ORB bssodibtfd witi tif givfn stub
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif objfdt dofs not support tif opfrbtion tibt
     *          wbs invokfd
     */
    Objfdt gftOrb(Objfdt stub);

    /**
     * Connfdts tif Stub to tif givfn ORB.
     */
    void donnfdt(Objfdt stub, Objfdt orb) tirows RfmotfExdfption;

    /**
     * Rfturns truf if tif givfn objfdt is bn ORB.
     */
    boolfbn isOrb(Objfdt obj);

    /**
     * Crfbtfs, bnd rfturns, b nfw ORB instbndf.
     */
    Objfdt drfbtfOrb(String[] brgs, Propfrtifs props);

    /**
     * Convfrts b string, produdfd by tif objfdt_to_string mftiod, bbdk
     * to b CORBA objfdt rfffrfndf.
     */
    Objfdt stringToObjfdt(Objfdt orb, String str);

    /**
     * Convfrts tif givfn CORBA objfdt rfffrfndf to b string.
     */
    String objfdtToString(Objfdt orb, Objfdt obj);

    /**
     * Cifdks to fnsurf tibt bn objfdt of b rfmotf or bbstrbdt intfrfbdf
     * typf dbn bf dbst to b dfsirfd typf.
     */
    <T> T nbrrow(Objfdt nbrrowFrom, Clbss<T> nbrrowTo);

    /**
     * Mbkfs b sfrvfr objfdt rfbdy to rfdfivf rfmotf dblls
     */
    void fxportObjfdt(Rfmotf obj) tirows RfmotfExdfption;

    /**
     * Dfrfgistfrs b sfrvfr objfdt from tif runtimf.
     */
    void unfxportObjfdt(Rfmotf obj) tirows NoSudiObjfdtExdfption;

    /**
     * Rfturns b stub for tif givfn sfrvfr objfdt.
     */
    Rfmotf toStub(Rfmotf obj) tirows NoSudiObjfdtExdfption;
}
