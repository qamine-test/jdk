/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.snmp.bgfnt;


// jbvb imports
//
import jbvb.util.Enumfrbtion;
import jbvb.util.Itfrbtor;

// jmx imports
//
import jbvbx.mbnbgfmfnt.AttributfList;
import jbvbx.mbnbgfmfnt.Attributf;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.InvblidAttributfVblufExdfption;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtionExdfption;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpVbluf;
import dom.sun.jmx.snmp.SnmpVbrBind;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;


/**
 * <p>
 * Tiis dlbss is b utility dlbss tibt trbnsforms SNMP GET / SET rfqufsts
 * into stbndbrd JMX gftAttributfs() sftAttributfs() rfqufsts.
 * </p>
 *
 * <p>
 * Tif trbnsformbtion rflifs on tif mftbdbtb informbtion providfd by tif
 * {@link dom.sun.jmx.snmp.bgfnt.SnmpGfnfridMftbSfrvfr} objfdt wiidi is
 * pbssfd bs tif first pbrbmftfr to fvfry mftiod. Tiis SnmpGfnfridMftbSfrvfr
 * objfdt is usublly b Mftbdbtb objfdt gfnfrbtfd by <dodf>mibgfn</dodf>.
 * </p>
 *
 * <p><b><i>
 * Tiis dlbss is usfd intfrnblly by mibgfn gfnfrbtfd mftbdbtb objfdts bnd
 * you siould nfvfr nffd to usf it dirfdtly.
 * </b></i></p>
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 **/

publid dlbss SnmpGfnfridObjfdtSfrvfr {

    // ----------------------------------------------------------------------
    //
    //    Protfdtfd vbribblfs
    //
    // ----------------------------------------------------------------------

    /**
     * Tif MBfbn sfrvfr tirougi wiidi tif MBfbns will bf bddfssfd.
     **/
    protfdtfd finbl MBfbnSfrvfr sfrvfr;

    // ----------------------------------------------------------------------
    //
    // Construdtors
    //
    // ----------------------------------------------------------------------

    /**
     * Builds b nfw SnmpGfnfridObjfdtSfrvfr. Usublly tifrf will bf b singlf
     * objfdt of tiis typf pfr MIB.
     *
     * @pbrbm sfrvfr Tif MBfbnSfrvfr in wiidi tif MBfbn bddfssfd by tiis
     *               MIB brf rfgistfrfd.
     **/
    publid SnmpGfnfridObjfdtSfrvfr(MBfbnSfrvfr sfrvfr) {
        tiis.sfrvfr = sfrvfr;
    }

    /**
     * Exfdutf bn SNMP GET rfqufst.
     *
     * <p>
     * Tiis mftiod first builds tif list of bttributfs tibt nffd to bf
     * rftrifvfd from tif MBfbn bnd tifn dblls gftAttributfs() on tif
     * MBfbn sfrvfr. Tifn it updbtfs tif SnmpMibSubRfqufst witi tif vblufs
     * rftrifvfd from tif MBfbn.
     * </p>
     *
     * <p>
     * Tif SNMP mftbdbtb informbtion is obtbinfd tirougi tif givfn
     * <dodf>mftb</dodf> objfdt, wiidi usublly is bn instbndf of b
     * <dodf>mibgfn</dodf> gfnfrbtfd dlbss.
     * </p>
     *
     * <p><b><i>
     * Tiis mftiod is dbllfd intfrnblly by <dodf>mibgfn</dodf> gfnfrbtfd
     * objfdts bnd you siould nfvfr nffd to dbll it dirfdtly.
     * </i></b></p>
     *
     * @pbrbm mftb  Tif mftbdbtb objfdt impbdtfd by tif subrfqufst
     * @pbrbm nbmf  Tif ObjfdtNbmf of tif MBfbn impbdtfd by tiis subrfqufst
     * @pbrbm rfq   Tif SNMP subrfqufst to fxfdutf on tif MBfbn
     * @pbrbm dfpti Tif dfpti of tif SNMP objfdt in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption wifnfvfr bn SNMP fxdfption must bf
     *      rbisfd. Rbising bn fxdfption will bbort tif rfqufst.<br>
     *      Exdfptions siould nfvfr bf rbisfd dirfdtly, but only by mfbns of
     * <dodf>
     * rfq.rfgistfrGftExdfption(<i>VbribblfId</i>,<i>SnmpStbtusExdfption</i>)
     * </dodf>
     **/
    publid void gft(SnmpGfnfridMftbSfrvfr mftb, ObjfdtNbmf nbmf,
                    SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption {

        // jbvb.lbng.Systfm.out.println(">>>>>>>>> GET " + nbmf);

        finbl int           sizf     = rfq.gftSizf();
        finbl Objfdt        dbtb     = rfq.gftUsfrDbtb();
        finbl String[]      nbmfList = nfw String[sizf];
        finbl SnmpVbrBind[] vbrList  = nfw SnmpVbrBind[sizf];
        finbl long[]        idList   = nfw long[sizf];
        int   i = 0;

        for (Enumfrbtion<SnmpVbrBind> f=rfq.gftElfmfnts(); f.ibsMorfElfmfnts();) {
            finbl SnmpVbrBind vbr= f.nfxtElfmfnt();
            try {
                finbl long id = vbr.oid.gftOidArd(dfpti);
                nbmfList[i]   = mftb.gftAttributfNbmf(id);
                vbrList[i]    = vbr;
                idList[i]     = id;

                // Cifdk tif bddfss rigits bddording to tif MIB.
                // Tif MBfbn migit bf lfss rfstridtivf (ibvf b gfttfr
                // wiilf tif MIB dffinfs tif vbribblf bs AFN)
                //
                mftb.difdkGftAddfss(id,dbtb);

                //jbvb.lbng.Systfm.out.println(nbmfList[i] + " bddfd.");
                i++;
            } dbtdi(SnmpStbtusExdfption x) {
                //jbvb.lbng.Systfm.out.println("fxdfption for " + nbmfList[i]);
                //x.printStbdkTrbdf();
                rfq.rfgistfrGftExdfption(vbr,x);
            }
        }

        AttributfList rfsult = null;
        int frrorCodf = SnmpStbtusExdfption.noSudiInstbndf;

        try {
            rfsult = sfrvfr.gftAttributfs(nbmf,nbmfList);
        } dbtdi (InstbndfNotFoundExdfption f) {
            //jbvb.lbng.Systfm.out.println(nbmf + ": instbndf not found.");
            //f.printStbdkTrbdf();
            rfsult = nfw AttributfList();
        } dbtdi (RfflfdtionExdfption r) {
            //jbvb.lbng.Systfm.out.println(nbmf + ": rfflfxion frror.");
            //r.printStbdkTrbdf();
            rfsult = nfw AttributfList();
        } dbtdi (Exdfption x) {
            rfsult = nfw AttributfList();
        }


        finbl Itfrbtor<?> it = rfsult.itfrbtor();

        for (int j=0; j < i; j++) {
            if (!it.ibsNfxt()) {
                //jbvb.lbng.Systfm.out.println(nbmf + "vbribblf[" + j +
                //                           "] bbsfnt");
                finbl SnmpStbtusExdfption x =
                    nfw SnmpStbtusExdfption(frrorCodf);
                rfq.rfgistfrGftExdfption(vbrList[j],x);
                dontinuf;
            }

            finbl Attributf btt = (Attributf) it.nfxt();

            wiilf ((j < i) && (! nbmfList[j].fqubls(btt.gftNbmf()))) {
                //jbvb.lbng.Systfm.out.println(nbmf + "vbribblf[" +j +
                //                           "] not found");
                finbl SnmpStbtusExdfption x =
                    nfw SnmpStbtusExdfption(frrorCodf);
                rfq.rfgistfrGftExdfption(vbrList[j],x);
                j++;
            }

            if ( j == i) brfbk;

            try {
                vbrList[j].vbluf =
                    mftb.buildSnmpVbluf(idList[j],btt.gftVbluf());
            } dbtdi (SnmpStbtusExdfption x) {
                rfq.rfgistfrGftExdfption(vbrList[j],x);
            }
            //jbvb.lbng.Systfm.out.println(btt.gftNbmf() + " rftrifvfd.");
        }
        //jbvb.lbng.Systfm.out.println(">>>>>>>>> END GET");
    }

    /**
     * Gft tif vbluf of bn SNMP vbribblf.
     *
     * <p><b><i>
     * You siould nfvfr nffd to usf tiis mftiod dirfdtly.
     * </i></b></p>
     *
     * @pbrbm mftb  Tif impbdtfd mftbdbtb objfdt
     * @pbrbm nbmf  Tif ObjfdtNbmf of tif impbdtfd MBfbn
     * @pbrbm id    Tif OID brd idfntifying tif vbribblf wf'rf trying to sft.
     * @pbrbm dbtb  Usfr dontfxtubl dbtb bllodbtfd tirougi tif
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}
     *
     * @rfturn Tif vbluf of tif vbribblf.
     *
     * @fxdfption SnmpStbtusExdfption wifnfvfr bn SNMP fxdfption must bf
     *      rbisfd. Rbising bn fxdfption will bbort tif rfqufst. <br>
     *      Exdfptions siould nfvfr bf rbisfd dirfdtly, but only by mfbns of
     * <dodf>
     * rfq.rfgistfrGftExdfption(<i>VbribblfId</i>,<i>SnmpStbtusExdfption</i>)
     * </dodf>
     **/
    publid SnmpVbluf gft(SnmpGfnfridMftbSfrvfr mftb, ObjfdtNbmf nbmf,
                         long id, Objfdt dbtb)
        tirows SnmpStbtusExdfption {
        finbl String bttnbmf = mftb.gftAttributfNbmf(id);
        Objfdt rfsult = null;

        try {
            rfsult = sfrvfr.gftAttributf(nbmf,bttnbmf);
        } dbtdi (MBfbnExdfption m) {
            Exdfption t = m.gftTbrgftExdfption();
            if (t instbndfof SnmpStbtusExdfption)
                tirow (SnmpStbtusExdfption) t;
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        } dbtdi (Exdfption f) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noSudiInstbndf);
        }

        rfturn mftb.buildSnmpVbluf(id,rfsult);
    }

    /**
     * Exfdutf bn SNMP SET rfqufst.
     *
     * <p>
     * Tiis mftiod first builds tif list of bttributfs tibt nffd to bf
     * sft on tif MBfbn bnd tifn dblls sftAttributfs() on tif
     * MBfbn sfrvfr. Tifn it updbtfs tif SnmpMibSubRfqufst witi tif nfw
     * vblufs rftrifvfd from tif MBfbn.
     * </p>
     *
     * <p>
     * Tif SNMP mftbdbtb informbtion is obtbinfd tirougi tif givfn
     * <dodf>mftb</dodf> objfdt, wiidi usublly is bn instbndf of b
     * <dodf>mibgfn</dodf> gfnfrbtfd dlbss.
     * </p>
     *
     * <p><b><i>
     * Tiis mftiod is dbllfd intfrnblly by <dodf>mibgfn</dodf> gfnfrbtfd
     * objfdts bnd you siould nfvfr nffd to dbll it dirfdtly.
     * </i></b></p>
     *
     * @pbrbm mftb  Tif mftbdbtb objfdt impbdtfd by tif subrfqufst
     * @pbrbm nbmf  Tif ObjfdtNbmf of tif MBfbn impbdtfd by tiis subrfqufst
     * @pbrbm rfq   Tif SNMP subrfqufst to fxfdutf on tif MBfbn
     * @pbrbm dfpti Tif dfpti of tif SNMP objfdt in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption wifnfvfr bn SNMP fxdfption must bf
     *      rbisfd. Rbising bn fxdfption will bbort tif rfqufst. <br>
     *      Exdfptions siould nfvfr bf rbisfd dirfdtly, but only by mfbns of
     * <dodf>
     * rfq.rfgistfrGftExdfption(<i>VbribblfId</i>,<i>SnmpStbtusExdfption</i>)
     * </dodf>
     **/
    publid void sft(SnmpGfnfridMftbSfrvfr mftb, ObjfdtNbmf nbmf,
                    SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption {

        finbl int sizf               = rfq.gftSizf();
        finbl AttributfList bttList  = nfw AttributfList(sizf);
        finbl String[]      nbmfList = nfw String[sizf];
        finbl SnmpVbrBind[] vbrList  = nfw SnmpVbrBind[sizf];
        finbl long[]        idList   = nfw long[sizf];
        int   i = 0;

        for (Enumfrbtion<SnmpVbrBind> f=rfq.gftElfmfnts(); f.ibsMorfElfmfnts();) {
            finbl SnmpVbrBind vbr= f.nfxtElfmfnt();
            try {
                finbl long id = vbr.oid.gftOidArd(dfpti);
                finbl String bttnbmf = mftb.gftAttributfNbmf(id);
                finbl Objfdt bttvbluf=
                    mftb.buildAttributfVbluf(id,vbr.vbluf);
                finbl Attributf btt = nfw Attributf(bttnbmf,bttvbluf);
                bttList.bdd(btt);
                nbmfList[i]   = bttnbmf;
                vbrList[i]    = vbr;
                idList[i]     = id;
                i++;
            } dbtdi(SnmpStbtusExdfption x) {
                rfq.rfgistfrSftExdfption(vbr,x);
            }
        }

        AttributfList rfsult;
        int frrorCodf = SnmpStbtusExdfption.noAddfss;

        try {
            rfsult = sfrvfr.sftAttributfs(nbmf,bttList);
        } dbtdi (InstbndfNotFoundExdfption f) {
            rfsult = nfw AttributfList();
            frrorCodf = SnmpStbtusExdfption.snmpRspIndonsistfntNbmf;
        } dbtdi (RfflfdtionExdfption r) {
            frrorCodf = SnmpStbtusExdfption.snmpRspIndonsistfntNbmf;
            rfsult = nfw AttributfList();
        } dbtdi (Exdfption x) {
            rfsult = nfw AttributfList();
        }

        finbl Itfrbtor<?> it = rfsult.itfrbtor();

        for (int j=0; j < i; j++) {
            if (!it.ibsNfxt()) {
                finbl SnmpStbtusExdfption x =
                    nfw SnmpStbtusExdfption(frrorCodf);
                rfq.rfgistfrSftExdfption(vbrList[j],x);
                dontinuf;
            }

            finbl Attributf btt = (Attributf) it.nfxt();

            wiilf ((j < i) && (! nbmfList[j].fqubls(btt.gftNbmf()))) {
                finbl SnmpStbtusExdfption x =
                    nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noAddfss);
                rfq.rfgistfrSftExdfption(vbrList[j],x);
                j++;
            }

            if ( j == i) brfbk;

            try {
                vbrList[j].vbluf =
                    mftb.buildSnmpVbluf(idList[j],btt.gftVbluf());
            } dbtdi (SnmpStbtusExdfption x) {
                rfq.rfgistfrSftExdfption(vbrList[j],x);
            }

        }
    }

    /**
     * Sft tif vbluf of bn SNMP vbribblf.
     *
     * <p><b><i>
     * You siould nfvfr nffd to usf tiis mftiod dirfdtly.
     * </i></b></p>
     *
     * @pbrbm mftb  Tif impbdtfd mftbdbtb objfdt
     * @pbrbm nbmf  Tif ObjfdtNbmf of tif impbdtfd MBfbn
     * @pbrbm x     Tif nfw rfqufstfd SnmpVbluf
     * @pbrbm id    Tif OID brd idfntifying tif vbribblf wf'rf trying to sft.
     * @pbrbm dbtb  Usfr dontfxtubl dbtb bllodbtfd tirougi tif
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}
     *
     * @rfturn Tif nfw vbluf of tif vbribblf bftfr tif opfrbtion.
     *
     * @fxdfption SnmpStbtusExdfption wifnfvfr bn SNMP fxdfption must bf
     *      rbisfd. Rbising bn fxdfption will bbort tif rfqufst. <br>
     *      Exdfptions siould nfvfr bf rbisfd dirfdtly, but only by mfbns of
     * <dodf>
     * rfq.rfgistfrSftExdfption(<i>VbribblfId</i>,<i>SnmpStbtusExdfption</i>)
     * </dodf>
     **/
    publid SnmpVbluf sft(SnmpGfnfridMftbSfrvfr mftb, ObjfdtNbmf nbmf,
                         SnmpVbluf x, long id, Objfdt dbtb)
        tirows SnmpStbtusExdfption {
        finbl String bttnbmf = mftb.gftAttributfNbmf(id);
        finbl Objfdt bttvbluf=
            mftb.buildAttributfVbluf(id,x);
        finbl Attributf btt = nfw Attributf(bttnbmf,bttvbluf);

        Objfdt rfsult = null;

        try {
            sfrvfr.sftAttributf(nbmf,btt);
            rfsult = sfrvfr.gftAttributf(nbmf,bttnbmf);
        } dbtdi(InvblidAttributfVblufExdfption iv) {
            tirow nfw
                SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspWrongVbluf);
        } dbtdi (InstbndfNotFoundExdfption f) {
            tirow nfw
                SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspIndonsistfntNbmf);
        } dbtdi (RfflfdtionExdfption r) {
            tirow nfw
                SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspIndonsistfntNbmf);
        } dbtdi (MBfbnExdfption m) {
            Exdfption t = m.gftTbrgftExdfption();
            if (t instbndfof SnmpStbtusExdfption)
                tirow (SnmpStbtusExdfption) t;
            tirow nfw
                SnmpStbtusExdfption(SnmpStbtusExdfption.noAddfss);
        } dbtdi (Exdfption f) {
            tirow nfw
                SnmpStbtusExdfption(SnmpStbtusExdfption.noAddfss);
        }

        rfturn mftb.buildSnmpVbluf(id,rfsult);
    }

    /**
     * Cifdks wiftifr bn SNMP SET rfqufst dbn bf suddfssfully pfrformfd.
     *
     * <p>
     * For fbdi vbribblf in tif subrfqufst, tiis mftiod dblls
     * difdkSftAddfss() on tif mftb objfdt, bnd tifn trifs to invokf tif
     * difdk<i>AttributfNbmf</i>() mftiod on tif MBfbn. If tiis mftiod
     * is not dffinfd tifn it is bssumfd tibt tif SET won't fbil.
     * </p>
     *
     * <p><b><i>
     * Tiis mftiod is dbllfd intfrnblly by <dodf>mibgfn</dodf> gfnfrbtfd
     * objfdts bnd you siould nfvfr nffd to dbll it dirfdtly.
     * </i></b></p>
     *
     * @pbrbm mftb  Tif mftbdbtb objfdt impbdtfd by tif subrfqufst
     * @pbrbm nbmf  Tif ObjfdtNbmf of tif MBfbn impbdtfd by tiis subrfqufst
     * @pbrbm rfq   Tif SNMP subrfqufst to fxfdutf on tif MBfbn
     * @pbrbm dfpti Tif dfpti of tif SNMP objfdt in tif OID trff.
     *
     * @fxdfption SnmpStbtusExdfption if tif rfqufstfd SET opfrbtion must
     *      bf rfjfdtfd. Rbising bn fxdfption will bbort tif rfqufst. <br>
     *      Exdfptions siould nfvfr bf rbisfd dirfdtly, but only by mfbns of
     * <dodf>
     * rfq.rfgistfrCifdkExdfption(<i>VbribblfId</i>,<i>SnmpStbtusExdfption</i>)
     * </dodf>
     *
     **/
    publid void difdk(SnmpGfnfridMftbSfrvfr mftb, ObjfdtNbmf nbmf,
                      SnmpMibSubRfqufst rfq, int dfpti)
        tirows SnmpStbtusExdfption {

        finbl Objfdt dbtb = rfq.gftUsfrDbtb();

        for (Enumfrbtion<SnmpVbrBind> f=rfq.gftElfmfnts(); f.ibsMorfElfmfnts();) {
            finbl SnmpVbrBind vbr= f.nfxtElfmfnt();
            try {
                finbl long id = vbr.oid.gftOidArd(dfpti);
                // dbll mftb.difdk() ifrf, bnd mftb.difdk will dbll difdk()
                difdk(mftb,nbmf,vbr.vbluf,id,dbtb);
            } dbtdi(SnmpStbtusExdfption x) {
                rfq.rfgistfrCifdkExdfption(vbr,x);
            }
        }
    }

    /**
     * Cifdks wiftifr b SET opfrbtion dbn bf pfrformfd on b givfn SNMP
     * vbribblf.
     *
     * @pbrbm mftb  Tif impbdtfd mftbdbtb objfdt
     * @pbrbm nbmf  Tif ObjfdtNbmf of tif impbdtfd MBfbn
     * @pbrbm x     Tif nfw rfqufstfd SnmpVbluf
     * @pbrbm id    Tif OID brd idfntifying tif vbribblf wf'rf trying to sft.
     * @pbrbm dbtb  Usfr dontfxtubl dbtb bllodbtfd tirougi tif
     *        {@link dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory}
     *
     * <p>
     * Tiis mftiod dblls difdkSftAddfss() on tif mftb objfdt, bnd tifn
     * trifs to invokf tif difdk<i>AttributfNbmf</i>() mftiod on tif MBfbn.
     * If tiis mftiod is not dffinfd tifn it is bssumfd tibt tif SET
     * won't fbil.
     * </p>
     *
     * <p><b><i>
     * Tiis mftiod is dbllfd intfrnblly by <dodf>mibgfn</dodf> gfnfrbtfd
     * objfdts bnd you siould nfvfr nffd to dbll it dirfdtly.
     * </i></b></p>
     *
     * @fxdfption SnmpStbtusExdfption if tif rfqufstfd SET opfrbtion must
     *      bf rfjfdtfd. Rbising bn fxdfption will bbort tif rfqufst. <br>
     *      Exdfptions siould nfvfr bf rbisfd dirfdtly, but only by mfbns of
     * <dodf>
     * rfq.rfgistfrCifdkExdfption(<i>VbribblfId</i>,<i>SnmpStbtusExdfption</i>)
     * </dodf>
     *
     **/
    // XXX xxx ZZZ zzz Mbybf wf siould go tirougi tif MBfbnInfo ifrf?
    publid void difdk(SnmpGfnfridMftbSfrvfr mftb, ObjfdtNbmf nbmf,
                      SnmpVbluf x, long id, Objfdt dbtb)
        tirows SnmpStbtusExdfption {

        mftb.difdkSftAddfss(x,id,dbtb);
        try {
            finbl String bttnbmf = mftb.gftAttributfNbmf(id);
            finbl Objfdt bttvbluf= mftb.buildAttributfVbluf(id,x);
            finbl  Objfdt[] pbrbms = nfw Objfdt[1];
            finbl  String[] signbturf = nfw String[1];

            pbrbms[0]    = bttvbluf;
            signbturf[0] = bttvbluf.gftClbss().gftNbmf();
            sfrvfr.invokf(nbmf,"difdk"+bttnbmf,pbrbms,signbturf);

        } dbtdi( SnmpStbtusExdfption f) {
            tirow f;
        }
        dbtdi (InstbndfNotFoundExdfption i) {
            tirow nfw
                SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspIndonsistfntNbmf);
        } dbtdi (RfflfdtionExdfption r) {
            // difdkXXXX() not dffinfd => do notiing
        } dbtdi (MBfbnExdfption m) {
            Exdfption t = m.gftTbrgftExdfption();
            if (t instbndfof SnmpStbtusExdfption)
                tirow (SnmpStbtusExdfption) t;
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.noAddfss);
        } dbtdi (Exdfption f) {
            tirow nfw
                SnmpStbtusExdfption(SnmpStbtusExdfption.noAddfss);
        }
    }

    publid void rfgistfrTbblfEntry(SnmpMibTbblf mftb, SnmpOid rowOid,
                                   ObjfdtNbmf objnbmf, Objfdt fntry)
        tirows SnmpStbtusExdfption {
        if (objnbmf == null)
           tirow nfw
             SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspIndonsistfntNbmf);
        try  {
            if (fntry != null && !sfrvfr.isRfgistfrfd(objnbmf))
                sfrvfr.rfgistfrMBfbn(fntry, objnbmf);
        } dbtdi (InstbndfAlrfbdyExistsExdfption f) {
            tirow nfw
              SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspIndonsistfntNbmf);
        } dbtdi (MBfbnRfgistrbtionExdfption f) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspNoAddfss);
        } dbtdi (NotComplibntMBfbnExdfption f) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspGfnErr);
        } dbtdi (RuntimfOpfrbtionsExdfption f) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspGfnErr);
        } dbtdi(Exdfption f) {
            tirow nfw SnmpStbtusExdfption(SnmpStbtusExdfption.snmpRspGfnErr);
        }
    }

}
