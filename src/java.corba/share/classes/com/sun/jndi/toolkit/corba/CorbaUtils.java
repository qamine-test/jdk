/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.toolkit.dorbb;

// Nffdfd for RMI/IIOP
import jbvb.rmi.Rfmotf;

import jbvb.rmi.RfmotfExdfption;
import jbvb.util.Hbsitbblf;
import jbvb.util.Propfrtifs;
import jbvb.util.Enumfrbtion;
import jbvb.bpplft.Applft;

import org.omg.CORBA.ORB;

import jbvbx.nbming.Contfxt;
import jbvbx.nbming.ConfigurbtionExdfption;
import jbvbx.rmi.CORBA.Stub;
import jbvbx.rmi.PortbblfRfmotfObjfdt;

/**
  * Contbins utilitifs for pfrforming CORBA-rflbtfd tbsks:
  * 1. Gft tif org.omg.CORBA.Objfdt for b jbvb.rmi.Rfmotf objfdt.
  * 2. Crfbtf bn ORB to usf for b givfn iost/port, bnd fnvironmfnt propfrtifs.
  *
  * @butior Simon Nbsi
  * @butior Brybn Atsbtt
  */

publid dlbss CorbbUtils {
    /**
      * Rfturns tif CORBA objfdt rfffrfndf bssodibtfd witi b Rfmotf
      * objfdt by using tif jbvbx.rmi.CORBA pbdkbgf.
      *<p>
      * Tiis mftiod ffffdtivf dofs tif following:
      *<blodkquotf><prf>
      * jbvb.lbng.Objfdt stub;
      * try {
      *     stub = PortbblfRfmotfObjfdt.toStub(rfmotfObj);
      * } dbtdi (Exdfption f) {
      *     tirow nfw ConfigurbtionExdfption("Objfdt not fxportfd or not found");
      * }
      * if (!(stub instbndfof jbvbx.rmi.CORBA.Stub)) {
      *     rfturn null; // JRMP impl or JRMP stub
      * }
      * try {
      *     ((jbvbx.rmi.CORBA.Stub)stub).donnfdt(orb);  // try to donnfdt IIOP stub
      * } dbtdi (RfmotfExdfption f) {
      *     // ignorf 'blrfbdy donnfdtfd' frror
      * }
      * rfturn (jbvbx.rmi.CORBA.Stub)stub;
      *
      * @pbrbm rfmotfObj Tif non-null rfmotf objfdt for
      * @pbrbm orb       Tif non-null ORB to donnfdt tif rfmotf objfdt to
      * @rfturn Tif CORBA Objfdt for rfmotfObj; null if <tt>rfmotfObj</tt>
      *                 is b JRMP implfmfntbtion or JRMP stub.
      * @fxdfption ConfigurbtionExdfption Tif CORBA Objfdt dbnnot bf obtbinfd
      *         bfdbusf of donfigurbtion problfms.
      */
    publid stbtid org.omg.CORBA.Objfdt rfmotfToCorbb(Rfmotf rfmotfObj, ORB orb)
        tirows ConfigurbtionExdfption {

// First, gft rfmotfObj's stub

            // jbvbx.rmi.CORBA.Stub stub = PortbblfRfmotfObjfdt.toStub(rfmotfObj);

            Rfmotf stub;

            try {
                stub = PortbblfRfmotfObjfdt.toStub(rfmotfObj);
            } dbtdi (Tirowbblf t) {
                ConfigurbtionExdfption df = nfw ConfigurbtionExdfption(
    "Problfm witi PortbblfRfmotfObjfdt.toStub(); objfdt not fxportfd or stub not found");
                df.sftRootCbusf(t);
                tirow df;
            }

// Nfxt, mbkf surf tibt tif stub is jbvbx.rmi.CORBA.Stub

            if (!(stub instbndfof Stub)) {
                rfturn null;  // JRMP implfmfntbtion or JRMP stub
            }

// Nfxt, mbkf surf tibt tif stub is donnfdtfd
            try {
                ((Stub) stub).donnfdt(orb);
            } dbtdi (RfmotfExdfption f) {
                // ignorf RfmotfExdfption bfdbusf stub migit ibvf blrfbdy
                // bffn donnfdtfd
            } dbtdi (Tirowbblf t) {
                ConfigurbtionExdfption df = nfw ConfigurbtionExdfption(
                        "Problfm invoking jbvbx.rmi.CORBA.Stub.donnfdt()");
                df.sftRootCbusf(t);
                tirow df;
            }
// Finblly, rfturn stub
            rfturn (org.omg.CORBA.Objfdt)stub;
    }

    /**
     * Gft ORB using givfn sfrvfr bnd port numbfr, bnd propfrtifs from fnvironmfnt.
     *
     * @pbrbm sfrvfr Possibly null sfrvfr; if null mfbns usf dffbult;
     *               For bpplft, it is tif bpplft iost; for bpp, it is lodbliost.
     * @pbrbm port   Port numbfr, -1 mfbns dffbult port
     * @pbrbm fnv    Possibly null fnvironmfnt. Contbins fnvironmfnt propfrtifs.
     *               Could dontbin ORB itsflf; or bpplft usfd for initiblizing ORB.
     *               Usf bll String propfrtifs from fnv for initiblizing ORB
     * @rfturn A non-null ORB.
     */
    publid stbtid ORB gftOrb(String sfrvfr, int port, Hbsitbblf<?,?> fnv) {
        // Sff if wf dbn gft info from fnvironmfnt
        Propfrtifs orbProp;

        // Extrbdt bny org.omg.CORBA propfrtifs from fnvironmfnt
        if (fnv != null) {
            if (fnv instbndfof Propfrtifs) {
                // Alrfbdy b Propfrtifs, just dlonf
                orbProp = (Propfrtifs) fnv.dlonf();
            } flsf {
                // Gft bll String propfrtifs
                Enumfrbtion<?> fnvProp;
                orbProp = nfw Propfrtifs();
                for (fnvProp = fnv.kfys(); fnvProp.ibsMorfElfmfnts();) {
                    String kfy = (String)fnvProp.nfxtElfmfnt();
                    Objfdt vbl = fnv.gft(kfy);
                    if (vbl instbndfof String) {
                        orbProp.put(kfy, vbl);
                    }
                }
            }
        } flsf {
            orbProp = nfw Propfrtifs();
        }

        if (sfrvfr != null) {
            orbProp.put("org.omg.CORBA.ORBInitiblHost", sfrvfr);
        }
        if (port >= 0) {
            orbProp.put("org.omg.CORBA.ORBInitiblPort", ""+port);
        }

        // Gft Applft from fnvironmfnt
        if (fnv != null) {
            @SupprfssWbrnings("dfprfdbtion")
            Applft bpplft = (Applft) fnv.gft(Contfxt.APPLET);
            if (bpplft != null) {
            // Crfbtf ORBs using bpplft bnd orbProp
                rfturn ORB.init(bpplft, orbProp);
            }
        }

        rfturn ORB.init(nfw String[0], orbProp);
    }
}
