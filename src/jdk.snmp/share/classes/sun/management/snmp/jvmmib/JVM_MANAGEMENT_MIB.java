/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt.snmp.jvmmib;

//
// Gfnfrbtfd by mibgfn vfrsion 5.0 (06/02/03) wifn dompiling JVM-MANAGEMENT-MIB in stbndbrd mftbdbtb modf.
//

// jbvb imports
//
import jbvb.io.Sfriblizbblf;
import jbvb.util.Hbsitbblf;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;
import dom.sun.jmx.snmp.bgfnt.SnmpMibNodf;
import dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf;
import dom.sun.jmx.snmp.bgfnt.SnmpStbndbrdObjfdtSfrvfr;

/**
 * Tif dlbss is usfd for rfprfsfnting "JVM-MANAGEMENT-MIB".
 * You dbn fdit tif filf if you wbnt to modify tif bfibviour of tif MIB.
 */
publid bbstrbdt dlbss JVM_MANAGEMENT_MIB fxtfnds SnmpMib implfmfnts Sfriblizbblf {

    stbtid finbl long sfriblVfrsionUID = 6895037919735816732L;
    /**
     * Dffbult donstrudtor. Initiblizf tif Mib trff.
     */
    publid JVM_MANAGEMENT_MIB() {
        mibNbmf = "JVM_MANAGEMENT_MIB";
    }

    /**
     * Initiblizbtion of tif MIB witi no rfgistrbtion in Jbvb DMK.
     */
    publid void init() tirows IllfgblAddfssExdfption {
        // Allow only onf initiblizbtion of tif MIB.
        //
        if (isInitiblizfd == truf) {
            rfturn ;
        }

        try  {
            populbtf(null, null);
        } dbtdi(IllfgblAddfssExdfption x)  {
            tirow x;
        } dbtdi(RuntimfExdfption x)  {
            tirow x;
        } dbtdi(Exdfption x)  {
            tirow nfw Error(x.gftMfssbgf());
        }

        isInitiblizfd = truf;
    }

    /**
     * Initiblizbtion of tif MIB witi AUTOMATIC REGISTRATION in Jbvb DMK.
     */
    publid ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
            tirows Exdfption {
        // Allow only onf initiblizbtion of tif MIB.
        //
        if (isInitiblizfd == truf) {
            tirow nfw InstbndfAlrfbdyExistsExdfption();
        }

        // Initiblizf MBfbnSfrvfr informbtion.
        //
        tiis.sfrvfr = sfrvfr;

        populbtf(sfrvfr, nbmf);

        isInitiblizfd = truf;
        rfturn nbmf;
    }

    /**
     * Initiblizbtion of tif MIB witi no rfgistrbtion in Jbvb DMK.
     */
    publid void populbtf(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
        tirows Exdfption {
        // Allow only onf initiblizbtion of tif MIB.
        //
        if (isInitiblizfd == truf) {
            rfturn ;
        }

        if (objfdtsfrvfr == null)
            objfdtsfrvfr = nfw SnmpStbndbrdObjfdtSfrvfr();

        // Initiblizbtion of tif "JvmOS" group.
        // To disbblf support of tiis group, rfdffinf tif
        // "drfbtfJvmOSMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
        //
        initJvmOS(sfrvfr);

        // Initiblizbtion of tif "JvmCompilbtion" group.
        // To disbblf support of tiis group, rfdffinf tif
        // "drfbtfJvmCompilbtionMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
        //
        initJvmCompilbtion(sfrvfr);

        // Initiblizbtion of tif "JvmRuntimf" group.
        // To disbblf support of tiis group, rfdffinf tif
        // "drfbtfJvmRuntimfMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
        //
        initJvmRuntimf(sfrvfr);

        // Initiblizbtion of tif "JvmTirfbding" group.
        // To disbblf support of tiis group, rfdffinf tif
        // "drfbtfJvmTirfbdingMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
        //
        initJvmTirfbding(sfrvfr);

        // Initiblizbtion of tif "JvmMfmory" group.
        // To disbblf support of tiis group, rfdffinf tif
        // "drfbtfJvmMfmoryMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
        //
        initJvmMfmory(sfrvfr);

        // Initiblizbtion of tif "JvmClbssLobding" group.
        // To disbblf support of tiis group, rfdffinf tif
        // "drfbtfJvmClbssLobdingMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
        //
        initJvmClbssLobding(sfrvfr);

        isInitiblizfd = truf;
    }


    // ------------------------------------------------------------
    //
    // Initiblizbtion of tif "JvmOS" group.
    //
    // ------------------------------------------------------------


    /**
     * Initiblizbtion of tif "JvmOS" group.
     *
     * To disbblf support of tiis group, rfdffinf tif
     * "drfbtfJvmOSMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
     *
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     **/
    protfdtfd void initJvmOS(MBfbnSfrvfr sfrvfr)
        tirows Exdfption {
        finbl String oid = gftGroupOid("JvmOS", "1.3.6.1.4.1.42.2.145.3.163.1.1.6");
        ObjfdtNbmf objnbmf = null;
        if (sfrvfr != null) {
            objnbmf = gftGroupObjfdtNbmf("JvmOS", oid, mibNbmf + ":nbmf=sun.mbnbgfmfnt.snmp.jvmmib.JvmOS");
        }
        finbl JvmOSMftb mftb = drfbtfJvmOSMftbNodf("JvmOS", oid, objnbmf, sfrvfr);
        if (mftb != null) {
            mftb.rfgistfrTbblfNodfs( tiis, sfrvfr );

            // Notf tibt wifn using stbndbrd mftbdbtb,
            // tif rfturnfd objfdt must implfmfnt tif "JvmOSMBfbn"
            // intfrfbdf.
            //
            finbl JvmOSMBfbn group = (JvmOSMBfbn) drfbtfJvmOSMBfbn("JvmOS", oid, objnbmf, sfrvfr);
            mftb.sftInstbndf( group );
            rfgistfrGroupNodf("JvmOS", oid, objnbmf, mftb, group, sfrvfr);
        }
    }


    /**
     * Fbdtory mftiod for "JvmOS" group mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmOS")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmOS" group (JvmOSMftb)
     *
     **/
    protfdtfd JvmOSMftb drfbtfJvmOSMftbNodf(String groupNbmf,
                String groupOid, ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr)  {
        rfturn nfw JvmOSMftb(tiis, objfdtsfrvfr);
    }


    /**
     * Fbdtory mftiod for "JvmOS" group MBfbn.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd MBfbn dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmOS")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif MBfbn dlbss gfnfrbtfd for tif
     *         "JvmOS" group (JvmOS)
     *
     * Notf tibt wifn using stbndbrd mftbdbtb,
     * tif rfturnfd objfdt must implfmfnt tif "JvmOSMBfbn"
     * intfrfbdf.
     **/
    protfdtfd bbstrbdt Objfdt drfbtfJvmOSMBfbn(String groupNbmf,
                String groupOid,  ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr);


    // ------------------------------------------------------------
    //
    // Initiblizbtion of tif "JvmCompilbtion" group.
    //
    // ------------------------------------------------------------


    /**
     * Initiblizbtion of tif "JvmCompilbtion" group.
     *
     * To disbblf support of tiis group, rfdffinf tif
     * "drfbtfJvmCompilbtionMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
     *
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     **/
    protfdtfd void initJvmCompilbtion(MBfbnSfrvfr sfrvfr)
        tirows Exdfption {
        finbl String oid = gftGroupOid("JvmCompilbtion", "1.3.6.1.4.1.42.2.145.3.163.1.1.5");
        ObjfdtNbmf objnbmf = null;
        if (sfrvfr != null) {
            objnbmf = gftGroupObjfdtNbmf("JvmCompilbtion", oid, mibNbmf + ":nbmf=sun.mbnbgfmfnt.snmp.jvmmib.JvmCompilbtion");
        }
        finbl JvmCompilbtionMftb mftb = drfbtfJvmCompilbtionMftbNodf("JvmCompilbtion", oid, objnbmf, sfrvfr);
        if (mftb != null) {
            mftb.rfgistfrTbblfNodfs( tiis, sfrvfr );

            // Notf tibt wifn using stbndbrd mftbdbtb,
            // tif rfturnfd objfdt must implfmfnt tif "JvmCompilbtionMBfbn"
            // intfrfbdf.
            //
            finbl JvmCompilbtionMBfbn group = (JvmCompilbtionMBfbn) drfbtfJvmCompilbtionMBfbn("JvmCompilbtion", oid, objnbmf, sfrvfr);
            mftb.sftInstbndf( group );
            rfgistfrGroupNodf("JvmCompilbtion", oid, objnbmf, mftb, group, sfrvfr);
        }
    }


    /**
     * Fbdtory mftiod for "JvmCompilbtion" group mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmCompilbtion")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmCompilbtion" group (JvmCompilbtionMftb)
     *
     **/
    protfdtfd JvmCompilbtionMftb drfbtfJvmCompilbtionMftbNodf(String groupNbmf,
                String groupOid, ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr)  {
        rfturn nfw JvmCompilbtionMftb(tiis, objfdtsfrvfr);
    }


    /**
     * Fbdtory mftiod for "JvmCompilbtion" group MBfbn.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd MBfbn dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmCompilbtion")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif MBfbn dlbss gfnfrbtfd for tif
     *         "JvmCompilbtion" group (JvmCompilbtion)
     *
     * Notf tibt wifn using stbndbrd mftbdbtb,
     * tif rfturnfd objfdt must implfmfnt tif "JvmCompilbtionMBfbn"
     * intfrfbdf.
     **/
    protfdtfd bbstrbdt Objfdt drfbtfJvmCompilbtionMBfbn(String groupNbmf,
                String groupOid,  ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr);


    // ------------------------------------------------------------
    //
    // Initiblizbtion of tif "JvmRuntimf" group.
    //
    // ------------------------------------------------------------


    /**
     * Initiblizbtion of tif "JvmRuntimf" group.
     *
     * To disbblf support of tiis group, rfdffinf tif
     * "drfbtfJvmRuntimfMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
     *
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     **/
    protfdtfd void initJvmRuntimf(MBfbnSfrvfr sfrvfr)
        tirows Exdfption {
        finbl String oid = gftGroupOid("JvmRuntimf", "1.3.6.1.4.1.42.2.145.3.163.1.1.4");
        ObjfdtNbmf objnbmf = null;
        if (sfrvfr != null) {
            objnbmf = gftGroupObjfdtNbmf("JvmRuntimf", oid, mibNbmf + ":nbmf=sun.mbnbgfmfnt.snmp.jvmmib.JvmRuntimf");
        }
        finbl JvmRuntimfMftb mftb = drfbtfJvmRuntimfMftbNodf("JvmRuntimf", oid, objnbmf, sfrvfr);
        if (mftb != null) {
            mftb.rfgistfrTbblfNodfs( tiis, sfrvfr );

            // Notf tibt wifn using stbndbrd mftbdbtb,
            // tif rfturnfd objfdt must implfmfnt tif "JvmRuntimfMBfbn"
            // intfrfbdf.
            //
            finbl JvmRuntimfMBfbn group = (JvmRuntimfMBfbn) drfbtfJvmRuntimfMBfbn("JvmRuntimf", oid, objnbmf, sfrvfr);
            mftb.sftInstbndf( group );
            rfgistfrGroupNodf("JvmRuntimf", oid, objnbmf, mftb, group, sfrvfr);
        }
    }


    /**
     * Fbdtory mftiod for "JvmRuntimf" group mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmRuntimf")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmRuntimf" group (JvmRuntimfMftb)
     *
     **/
    protfdtfd JvmRuntimfMftb drfbtfJvmRuntimfMftbNodf(String groupNbmf,
                String groupOid, ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr)  {
        rfturn nfw JvmRuntimfMftb(tiis, objfdtsfrvfr);
    }


    /**
     * Fbdtory mftiod for "JvmRuntimf" group MBfbn.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd MBfbn dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmRuntimf")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif MBfbn dlbss gfnfrbtfd for tif
     *         "JvmRuntimf" group (JvmRuntimf)
     *
     * Notf tibt wifn using stbndbrd mftbdbtb,
     * tif rfturnfd objfdt must implfmfnt tif "JvmRuntimfMBfbn"
     * intfrfbdf.
     **/
    protfdtfd bbstrbdt Objfdt drfbtfJvmRuntimfMBfbn(String groupNbmf,
                String groupOid,  ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr);


    // ------------------------------------------------------------
    //
    // Initiblizbtion of tif "JvmTirfbding" group.
    //
    // ------------------------------------------------------------


    /**
     * Initiblizbtion of tif "JvmTirfbding" group.
     *
     * To disbblf support of tiis group, rfdffinf tif
     * "drfbtfJvmTirfbdingMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
     *
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     **/
    protfdtfd void initJvmTirfbding(MBfbnSfrvfr sfrvfr)
        tirows Exdfption {
        finbl String oid = gftGroupOid("JvmTirfbding", "1.3.6.1.4.1.42.2.145.3.163.1.1.3");
        ObjfdtNbmf objnbmf = null;
        if (sfrvfr != null) {
            objnbmf = gftGroupObjfdtNbmf("JvmTirfbding", oid, mibNbmf + ":nbmf=sun.mbnbgfmfnt.snmp.jvmmib.JvmTirfbding");
        }
        finbl JvmTirfbdingMftb mftb = drfbtfJvmTirfbdingMftbNodf("JvmTirfbding", oid, objnbmf, sfrvfr);
        if (mftb != null) {
            mftb.rfgistfrTbblfNodfs( tiis, sfrvfr );

            // Notf tibt wifn using stbndbrd mftbdbtb,
            // tif rfturnfd objfdt must implfmfnt tif "JvmTirfbdingMBfbn"
            // intfrfbdf.
            //
            finbl JvmTirfbdingMBfbn group = (JvmTirfbdingMBfbn) drfbtfJvmTirfbdingMBfbn("JvmTirfbding", oid, objnbmf, sfrvfr);
            mftb.sftInstbndf( group );
            rfgistfrGroupNodf("JvmTirfbding", oid, objnbmf, mftb, group, sfrvfr);
        }
    }


    /**
     * Fbdtory mftiod for "JvmTirfbding" group mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmTirfbding")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmTirfbding" group (JvmTirfbdingMftb)
     *
     **/
    protfdtfd JvmTirfbdingMftb drfbtfJvmTirfbdingMftbNodf(String groupNbmf,
                String groupOid, ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr)  {
        rfturn nfw JvmTirfbdingMftb(tiis, objfdtsfrvfr);
    }


    /**
     * Fbdtory mftiod for "JvmTirfbding" group MBfbn.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd MBfbn dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmTirfbding")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif MBfbn dlbss gfnfrbtfd for tif
     *         "JvmTirfbding" group (JvmTirfbding)
     *
     * Notf tibt wifn using stbndbrd mftbdbtb,
     * tif rfturnfd objfdt must implfmfnt tif "JvmTirfbdingMBfbn"
     * intfrfbdf.
     **/
    protfdtfd bbstrbdt Objfdt drfbtfJvmTirfbdingMBfbn(String groupNbmf,
                String groupOid,  ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr);


    // ------------------------------------------------------------
    //
    // Initiblizbtion of tif "JvmMfmory" group.
    //
    // ------------------------------------------------------------


    /**
     * Initiblizbtion of tif "JvmMfmory" group.
     *
     * To disbblf support of tiis group, rfdffinf tif
     * "drfbtfJvmMfmoryMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
     *
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     **/
    protfdtfd void initJvmMfmory(MBfbnSfrvfr sfrvfr)
        tirows Exdfption {
        finbl String oid = gftGroupOid("JvmMfmory", "1.3.6.1.4.1.42.2.145.3.163.1.1.2");
        ObjfdtNbmf objnbmf = null;
        if (sfrvfr != null) {
            objnbmf = gftGroupObjfdtNbmf("JvmMfmory", oid, mibNbmf + ":nbmf=sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmory");
        }
        finbl JvmMfmoryMftb mftb = drfbtfJvmMfmoryMftbNodf("JvmMfmory", oid, objnbmf, sfrvfr);
        if (mftb != null) {
            mftb.rfgistfrTbblfNodfs( tiis, sfrvfr );

            // Notf tibt wifn using stbndbrd mftbdbtb,
            // tif rfturnfd objfdt must implfmfnt tif "JvmMfmoryMBfbn"
            // intfrfbdf.
            //
            finbl JvmMfmoryMBfbn group = (JvmMfmoryMBfbn) drfbtfJvmMfmoryMBfbn("JvmMfmory", oid, objnbmf, sfrvfr);
            mftb.sftInstbndf( group );
            rfgistfrGroupNodf("JvmMfmory", oid, objnbmf, mftb, group, sfrvfr);
        }
    }


    /**
     * Fbdtory mftiod for "JvmMfmory" group mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmMfmory")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmMfmory" group (JvmMfmoryMftb)
     *
     **/
    protfdtfd JvmMfmoryMftb drfbtfJvmMfmoryMftbNodf(String groupNbmf,
                String groupOid, ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr)  {
        rfturn nfw JvmMfmoryMftb(tiis, objfdtsfrvfr);
    }


    /**
     * Fbdtory mftiod for "JvmMfmory" group MBfbn.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd MBfbn dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmMfmory")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif MBfbn dlbss gfnfrbtfd for tif
     *         "JvmMfmory" group (JvmMfmory)
     *
     * Notf tibt wifn using stbndbrd mftbdbtb,
     * tif rfturnfd objfdt must implfmfnt tif "JvmMfmoryMBfbn"
     * intfrfbdf.
     **/
    protfdtfd bbstrbdt Objfdt drfbtfJvmMfmoryMBfbn(String groupNbmf,
                String groupOid,  ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr);


    // ------------------------------------------------------------
    //
    // Initiblizbtion of tif "JvmClbssLobding" group.
    //
    // ------------------------------------------------------------


    /**
     * Initiblizbtion of tif "JvmClbssLobding" group.
     *
     * To disbblf support of tiis group, rfdffinf tif
     * "drfbtfJvmClbssLobdingMftbNodf()" fbdtory mftiod, bnd mbkf it rfturn "null"
     *
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     **/
    protfdtfd void initJvmClbssLobding(MBfbnSfrvfr sfrvfr)
        tirows Exdfption {
        finbl String oid = gftGroupOid("JvmClbssLobding", "1.3.6.1.4.1.42.2.145.3.163.1.1.1");
        ObjfdtNbmf objnbmf = null;
        if (sfrvfr != null) {
            objnbmf = gftGroupObjfdtNbmf("JvmClbssLobding", oid, mibNbmf + ":nbmf=sun.mbnbgfmfnt.snmp.jvmmib.JvmClbssLobding");
        }
        finbl JvmClbssLobdingMftb mftb = drfbtfJvmClbssLobdingMftbNodf("JvmClbssLobding", oid, objnbmf, sfrvfr);
        if (mftb != null) {
            mftb.rfgistfrTbblfNodfs( tiis, sfrvfr );

            // Notf tibt wifn using stbndbrd mftbdbtb,
            // tif rfturnfd objfdt must implfmfnt tif "JvmClbssLobdingMBfbn"
            // intfrfbdf.
            //
            finbl JvmClbssLobdingMBfbn group = (JvmClbssLobdingMBfbn) drfbtfJvmClbssLobdingMBfbn("JvmClbssLobding", oid, objnbmf, sfrvfr);
            mftb.sftInstbndf( group );
            rfgistfrGroupNodf("JvmClbssLobding", oid, objnbmf, mftb, group, sfrvfr);
        }
    }


    /**
     * Fbdtory mftiod for "JvmClbssLobding" group mftbdbtb dlbss.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd mftbdbtb dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmClbssLobding")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif mftbdbtb dlbss gfnfrbtfd for tif
     *         "JvmClbssLobding" group (JvmClbssLobdingMftb)
     *
     **/
    protfdtfd JvmClbssLobdingMftb drfbtfJvmClbssLobdingMftbNodf(String groupNbmf,
                String groupOid, ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr)  {
        rfturn nfw JvmClbssLobdingMftb(tiis, objfdtsfrvfr);
    }


    /**
     * Fbdtory mftiod for "JvmClbssLobding" group MBfbn.
     *
     * You dbn rfdffinf tiis mftiod if you nffd to rfplbdf tif dffbult
     * gfnfrbtfd MBfbn dlbss witi your own dustomizfd dlbss.
     *
     * @pbrbm groupNbmf Nbmf of tif group ("JvmClbssLobding")
     * @pbrbm groupOid  OID of tiis group
     * @pbrbm groupObjnbmf ObjfdtNbmf for tiis group (mby bf null)
     * @pbrbm sfrvfr    MBfbnSfrvfr for tiis group (mby bf null)
     *
     * @rfturn An instbndf of tif MBfbn dlbss gfnfrbtfd for tif
     *         "JvmClbssLobding" group (JvmClbssLobding)
     *
     * Notf tibt wifn using stbndbrd mftbdbtb,
     * tif rfturnfd objfdt must implfmfnt tif "JvmClbssLobdingMBfbn"
     * intfrfbdf.
     **/
    protfdtfd bbstrbdt Objfdt drfbtfJvmClbssLobdingMBfbn(String groupNbmf,
                String groupOid,  ObjfdtNbmf groupObjnbmf, MBfbnSfrvfr sfrvfr);


    // ------------------------------------------------------------
    //
    // Implfmfnts tif "rfgistfrTbblfMftb" mftiod dffinfd in "SnmpMib".
    // Sff tif "SnmpMib" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid void rfgistfrTbblfMftb( String nbmf, SnmpMibTbblf mftb) {
        if (mftbdbtbs == null) rfturn;
        if (nbmf == null) rfturn;
        mftbdbtbs.put(nbmf,mftb);
    }


    // ------------------------------------------------------------
    //
    // Implfmfnts tif "gftRfgistfrfdTbblfMftb" mftiod dffinfd in "SnmpMib".
    // Sff tif "SnmpMib" Jbvbdod API for morf dftbils.
    //
    // ------------------------------------------------------------

    publid SnmpMibTbblf gftRfgistfrfdTbblfMftb( String nbmf ) {
        if (mftbdbtbs == null) rfturn null;
        if (nbmf == null) rfturn null;
        rfturn mftbdbtbs.gft(nbmf);
    }

    publid SnmpStbndbrdObjfdtSfrvfr gftStbndbrdObjfdtSfrvfr() {
        if (objfdtsfrvfr == null)
            objfdtsfrvfr = nfw SnmpStbndbrdObjfdtSfrvfr();
        rfturn objfdtsfrvfr;
    }

    privbtf boolfbn isInitiblizfd = fblsf;

    protfdtfd SnmpStbndbrdObjfdtSfrvfr objfdtsfrvfr;

    protfdtfd finbl Hbsitbblf<String, SnmpMibTbblf> mftbdbtbs =
            nfw Hbsitbblf<String, SnmpMibTbblf>();
}
