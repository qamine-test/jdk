/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.spi.DirfdtoryMbnbgfr;
import jbvbx.nbming.spi.DirStbtfFbdtory;

import jbvb.io.IOExdfption;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmClbss;
import jbvb.io.InputStrfbm;

import jbvb.util.Bbsf64;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;
import jbvb.util.StringTokfnizfr;

import jbvb.lbng.rfflfdt.Proxy;
import jbvb.lbng.rfflfdt.Modififr;

/**
  * Clbss dontbining stbtid mftiods bnd donstbnts for dfbling witi
  * fndoding/dfdoding JNDI Rfffrfndfs bnd Sfriblizfd Objfdts
  * in LDAP.
  * @butior Vindfnt Rybn
  * @butior Rosbnnb Lff
  */
finbl dlbss Obj {

    privbtf Obj () {}; // Mbkf surf no onf dbn drfbtf onf

    // pbdkbgf privbtf; usfd by Connfdtion
    stbtid VfrsionHflpfr iflpfr = VfrsionHflpfr.gftVfrsionHflpfr();

    // LDAP bttributfs usfd to support Jbvb objfdts.
    stbtid finbl String[] JAVA_ATTRIBUTES = {
        "objfdtClbss",
        "jbvbSfriblizfdDbtb",
        "jbvbClbssNbmf",
        "jbvbFbdtory",
        "jbvbCodfBbsf",
        "jbvbRfffrfndfAddrfss",
        "jbvbClbssNbmfs",
        "jbvbRfmotfLodbtion"     // Dfprfdbtfd
    };

    stbtid finbl int OBJECT_CLASS = 0;
    stbtid finbl int SERIALIZED_DATA = 1;
    stbtid finbl int CLASSNAME = 2;
    stbtid finbl int FACTORY = 3;
    stbtid finbl int CODEBASE = 4;
    stbtid finbl int REF_ADDR = 5;
    stbtid finbl int TYPENAME = 6;
    /**
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    privbtf stbtid finbl int REMOTE_LOC = 7;

    // LDAP objfdt dlbssfs to support Jbvb objfdts
    stbtid finbl String[] JAVA_OBJECT_CLASSES = {
        "jbvbContbinfr",
        "jbvbObjfdt",
        "jbvbNbmingRfffrfndf",
        "jbvbSfriblizfdObjfdt",
        "jbvbMbrsibllfdObjfdt",
    };

    stbtid finbl String[] JAVA_OBJECT_CLASSES_LOWER = {
        "jbvbdontbinfr",
        "jbvbobjfdt",
        "jbvbnbmingrfffrfndf",
        "jbvbsfriblizfdobjfdt",
        "jbvbmbrsibllfdobjfdt",
    };

    stbtid finbl int STRUCTURAL = 0;    // strudturbl objfdt dlbss
    stbtid finbl int BASE_OBJECT = 1;   // buxilibry jbvb objfdt dlbss
    stbtid finbl int REF_OBJECT = 2;    // buxilibry rfffrfndf objfdt dlbss
    stbtid finbl int SER_OBJECT = 3;    // buxilibry sfriblizfd objfdt dlbss
    stbtid finbl int MAR_OBJECT = 4;    // buxilibry mbrsibllfd objfdt dlbss

    /**
     * Endodf bn objfdt in LDAP bttributfs.
     * Supports binding Rfffrfndfbblf or Rfffrfndf, Sfriblizbblf,
     * bnd DirContfxt.
     *
     * If tif objfdt supports tif Rfffrfndfbblf intfrfbdf tifn fndodf
     * tif rfffrfndf to tif objfdt. Sff fndodfRfffrfndf() for dftbils.
     *<p>
     * If tif objfdt is sfriblizbblf, it is storfd bs follows:
     * jbvbClbssNbmf
     *   vbluf: Objfdt.gftClbss();
     * jbvbSfriblizfdDbtb
     *   vbluf: sfriblizfd form of Objfdt (in binbry form).
     * jbvbTypfNbmf
     *   vbluf: gftTypfNbmfs(Objfdt.gftClbss());
     */
    privbtf stbtid Attributfs fndodfObjfdt(dibr sfpbrbtor,
        Objfdt obj, Attributfs bttrs,
        Attributf objfdtClbss, boolfbn dlonfd)
        tirows NbmingExdfption {
            boolfbn strudturbl =
                (objfdtClbss.sizf() == 0 ||
                    (objfdtClbss.sizf() == 1 && objfdtClbss.dontbins("top")));

            if (strudturbl) {
                objfdtClbss.bdd(JAVA_OBJECT_CLASSES[STRUCTURAL]);
            }

    // Rfffrfndfs
            if (obj instbndfof Rfffrfndfbblf) {
                objfdtClbss.bdd(JAVA_OBJECT_CLASSES[BASE_OBJECT]);
                objfdtClbss.bdd(JAVA_OBJECT_CLASSES[REF_OBJECT]);
                if (!dlonfd) {
                    bttrs = (Attributfs)bttrs.dlonf();
                }
                bttrs.put(objfdtClbss);
                rfturn (fndodfRfffrfndf(sfpbrbtor,
                    ((Rfffrfndfbblf)obj).gftRfffrfndf(),
                    bttrs, obj));

            } flsf if (obj instbndfof Rfffrfndf) {
                objfdtClbss.bdd(JAVA_OBJECT_CLASSES[BASE_OBJECT]);
                objfdtClbss.bdd(JAVA_OBJECT_CLASSES[REF_OBJECT]);
                if (!dlonfd) {
                    bttrs = (Attributfs)bttrs.dlonf();
                }
                bttrs.put(objfdtClbss);
                rfturn (fndodfRfffrfndf(sfpbrbtor, (Rfffrfndf)obj, bttrs, null));

    // Sfriblizbblf Objfdt
            } flsf if (obj instbndfof jbvb.io.Sfriblizbblf) {
                objfdtClbss.bdd(JAVA_OBJECT_CLASSES[BASE_OBJECT]);
                if (!(objfdtClbss.dontbins(JAVA_OBJECT_CLASSES[MAR_OBJECT]) ||
                    objfdtClbss.dontbins(JAVA_OBJECT_CLASSES_LOWER[MAR_OBJECT]))) {
                    objfdtClbss.bdd(JAVA_OBJECT_CLASSES[SER_OBJECT]);
                }
                if (!dlonfd) {
                    bttrs = (Attributfs)bttrs.dlonf();
                }
                bttrs.put(objfdtClbss);
                bttrs.put(nfw BbsidAttributf(JAVA_ATTRIBUTES[SERIALIZED_DATA],
                    sfriblizfObjfdt(obj)));
                if (bttrs.gft(JAVA_ATTRIBUTES[CLASSNAME]) == null) {
                    bttrs.put(JAVA_ATTRIBUTES[CLASSNAME],
                        obj.gftClbss().gftNbmf());
                }
                if (bttrs.gft(JAVA_ATTRIBUTES[TYPENAME]) == null) {
                    Attributf tAttr =
                        LdbpCtxFbdtory.drfbtfTypfNbmfAttr(obj.gftClbss());
                    if (tAttr != null) {
                        bttrs.put(tAttr);
                    }
                }
    // DirContfxt Objfdt
            } flsf if (obj instbndfof DirContfxt) {
                // do notiing
            } flsf {
                tirow nfw IllfgblArgumfntExdfption(
            "dbn only bind Rfffrfndfbblf, Sfriblizbblf, DirContfxt");
            }
            //      Systfm.frr.println(bttrs);
            rfturn bttrs;
    }

    /**
     * Ebdi vbluf in jbvbCodfbbsf dontbins b list of spbdf-sfpbrbtfd
     * URLs. Ebdi vbluf is indfpfndfnt; wf dbn pidk bny of tif vblufs
     * so wf just usf tif first onf.
     * @rfturn bn brrby of URL strings for tif dodfbbsf
     */
    privbtf stbtid String[] gftCodfbbsfs(Attributf dodfbbsfAttr) tirows
        NbmingExdfption {
        if (dodfbbsfAttr == null) {
            rfturn null;
        } flsf {
            StringTokfnizfr pbrsfr =
                nfw StringTokfnizfr((String)dodfbbsfAttr.gft());
            Vfdtor<String> vfd = nfw Vfdtor<>(10);
            wiilf (pbrsfr.ibsMorfTokfns()) {
                vfd.bddElfmfnt(pbrsfr.nfxtTokfn());
            }
            String[] bnswfr = nfw String[vfd.sizf()];
            for (int i = 0; i < bnswfr.lfngti; i++) {
                bnswfr[i] = vfd.flfmfntAt(i);
            }
            rfturn bnswfr;
        }
    }

    /*
     * Dfdodf bn objfdt from LDAP bttributf(s).
     * Tif objfdt mby bf b Rfffrfndf, or b Sfriblizfd objfdt.
     *
     * Sff fndodfObjfdt() bnd fndodfRfffrfndf() for dftbils on formbts
     * fxpfdtfd.
     */
    stbtid Objfdt dfdodfObjfdt(Attributfs bttrs)
        tirows NbmingExdfption {

        Attributf bttr;

        // Gft dodfbbsf, wiidi is usfd in bll 3 dbsfs.
        String[] dodfbbsfs = gftCodfbbsfs(bttrs.gft(JAVA_ATTRIBUTES[CODEBASE]));
        try {
            if ((bttr = bttrs.gft(JAVA_ATTRIBUTES[SERIALIZED_DATA])) != null) {
                ClbssLobdfr dl = iflpfr.gftURLClbssLobdfr(dodfbbsfs);
                rfturn dfsfriblizfObjfdt((bytf[])bttr.gft(), dl);
            } flsf if ((bttr = bttrs.gft(JAVA_ATTRIBUTES[REMOTE_LOC])) != null) {
                // For bbdkwbrd dompbtibility only
                rfturn dfdodfRmiObjfdt(
                    (String)bttrs.gft(JAVA_ATTRIBUTES[CLASSNAME]).gft(),
                    (String)bttr.gft(), dodfbbsfs);
            }

            bttr = bttrs.gft(JAVA_ATTRIBUTES[OBJECT_CLASS]);
            if (bttr != null &&
                (bttr.dontbins(JAVA_OBJECT_CLASSES[REF_OBJECT]) ||
                    bttr.dontbins(JAVA_OBJECT_CLASSES_LOWER[REF_OBJECT]))) {
                rfturn dfdodfRfffrfndf(bttrs, dodfbbsfs);
            }
            rfturn null;
        } dbtdi (IOExdfption f) {
            NbmingExdfption nf = nfw NbmingExdfption();
            nf.sftRootCbusf(f);
            tirow nf;
        }
    }

    /**
     * Convfrt b Rfffrfndf objfdt into sfvfrbl LDAP bttributfs.
     *
     * A Rfffrfndf is storfd bs into tif following bttributfs:
     * jbvbClbssNbmf
     *   vbluf: Rfffrfndf.gftClbssNbmf();
     * jbvbFbdtory
     *   vbluf: Rfffrfndf.gftFbdtoryClbssNbmf();
     * jbvbCodfBbsf
     *   vbluf: Rfffrfndf.gftFbdtoryClbssLodbtion();
     * jbvbRfffrfndfAddrfss
     *   vbluf: #0#typfA#vblA
     *   vbluf: #1#typfB#vblB
     *   vbluf: #2#typfC##[sfriblizfd RffAddr C]
     *   vbluf: #3#typfD#vblD
     *
     * wifrf
     * -  tif first dibrbdtfr dfnotfs tif sfpbrbtor
     * -  tif numbfr following tif first sfpbrbtor dfnotfs tif position
     *    of tif RffAddr witiin tif Rfffrfndf
     * -  "typfA" is RffAddr.gftTypf()
     * -  ## dfnotfs tibt tif Bbsf64-fndodfd form of tif non-StringRffAddr
     *    is to follow; otifrwisf tif vbluf tibt follows is
     *    StringRffAddr.gftContfnts()
     *
     * Tif dffbult sfpbrbtor is tif ibsi dibrbdtfr (#).
     * Mby providf propfrty for tiis in futurf.
     */

    privbtf stbtid Attributfs fndodfRfffrfndf(dibr sfpbrbtor,
        Rfffrfndf rff, Attributfs bttrs, Objfdt orig)
        tirows NbmingExdfption {

        if (rff == null)
            rfturn bttrs;

        String s;

        if ((s = rff.gftClbssNbmf()) != null) {
            bttrs.put(nfw BbsidAttributf(JAVA_ATTRIBUTES[CLASSNAME], s));
        }

        if ((s = rff.gftFbdtoryClbssNbmf()) != null) {
            bttrs.put(nfw BbsidAttributf(JAVA_ATTRIBUTES[FACTORY], s));
        }

        if ((s = rff.gftFbdtoryClbssLodbtion()) != null) {
            bttrs.put(nfw BbsidAttributf(JAVA_ATTRIBUTES[CODEBASE], s));
        }

        // Gft originbl objfdt's typfs if dbllfr ibs not fxpliditly
        // spfdififd otifr typf nbmfs
        if (orig != null && bttrs.gft(JAVA_ATTRIBUTES[TYPENAME]) != null) {
            Attributf tAttr =
                LdbpCtxFbdtory.drfbtfTypfNbmfAttr(orig.gftClbss());
            if (tAttr != null) {
                bttrs.put(tAttr);
            }
        }

        int dount = rff.sizf();

        if (dount > 0) {

            Attributf rffAttr = nfw BbsidAttributf(JAVA_ATTRIBUTES[REF_ADDR]);
            RffAddr rffAddr;
            Bbsf64.Endodfr fndodfr = null;

            for (int i = 0; i < dount; i++) {
                rffAddr = rff.gft(i);

                if (rffAddr instbndfof StringRffAddr) {
                    rffAttr.bdd(""+ sfpbrbtor + i +
                        sfpbrbtor +     rffAddr.gftTypf() +
                        sfpbrbtor + rffAddr.gftContfnt());
                } flsf {
                    if (fndodfr == null)
                        fndodfr = Bbsf64.gftMimfEndodfr();

                    rffAttr.bdd(""+ sfpbrbtor + i +
                        sfpbrbtor + rffAddr.gftTypf() +
                        sfpbrbtor + sfpbrbtor +
                        fndodfr.fndodfToString(sfriblizfObjfdt(rffAddr)));
                }
            }
            bttrs.put(rffAttr);
        }
        rfturn bttrs;
    }

    /*
     * A RMI objfdt is storfd in tif dirfdtory bs
     * jbvbClbssNbmf
     *   vbluf: Objfdt.gftClbss();
     * jbvbRfmotfLodbtion
     *   vbluf: URL of RMI objfdt (bddfssfd tirougi tif RMI Rfgistry)
     * jbvbCodfbbsf:
     *   vbluf: URL of dodfbbsf of wifrf to find dlbssfs for objfdt
     *
     * Rfturn tif RMI Lodbtion URL itsflf. Tiis will bf turnfd into
     * bn RMI objfdt wifn gftObjfdtInstbndf() is dbllfd on it.
     * %%% Ignorf dodfbbsf for now. Dfpfnd on RMI rfgistry to sfnd dodf.-RL
     * @dfprfdbtfd For bbdkwbrd dompbtibility only
     */
    privbtf stbtid Objfdt dfdodfRmiObjfdt(String dlbssNbmf,
        String rmiNbmf, String[] dodfbbsfs) tirows NbmingExdfption {
            rfturn nfw Rfffrfndf(dlbssNbmf, nfw StringRffAddr("URL", rmiNbmf));
    }

    /*
     * Rfstorf b Rfffrfndf objfdt from sfvfrbl LDAP bttributfs
     */
    privbtf stbtid Rfffrfndf dfdodfRfffrfndf(Attributfs bttrs,
        String[] dodfbbsfs) tirows NbmingExdfption, IOExdfption {

        Attributf bttr;
        String dlbssNbmf;
        String fbdtory = null;

        if ((bttr = bttrs.gft(JAVA_ATTRIBUTES[CLASSNAME])) != null) {
            dlbssNbmf = (String)bttr.gft();
        } flsf {
            tirow nfw InvblidAttributfsExdfption(JAVA_ATTRIBUTES[CLASSNAME] +
                        " bttributf is rfquirfd");
        }

        if ((bttr = bttrs.gft(JAVA_ATTRIBUTES[FACTORY])) != null) {
            fbdtory = (String)bttr.gft();
        }

        Rfffrfndf rff = nfw Rfffrfndf(dlbssNbmf, fbdtory,
            (dodfbbsfs != null? dodfbbsfs[0] : null));

        /*
         * string fndoding of b RffAddr is fitifr:
         *
         *      #posn#<typf>#<bddrfss>
         * or
         *      #posn#<typf>##<bbsf64-fndodfd bddrfss>
         */
        if ((bttr = bttrs.gft(JAVA_ATTRIBUTES[REF_ADDR])) != null) {

            String vbl, posnStr, typf;
            dibr sfpbrbtor;
            int stbrt, sfp, posn;
            Bbsf64.Dfdodfr dfdodfr = null;

            ClbssLobdfr dl = iflpfr.gftURLClbssLobdfr(dodfbbsfs);

            /*
             * Tfmporbry Vfdtor for dfdodfd RffAddr bddrfssfs - usfd to fnsurf
             * unordfrfd bddrfssfs brf dorrfdtly rf-ordfrfd.
             */
            Vfdtor<RffAddr> rffAddrList = nfw Vfdtor<>();
            rffAddrList.sftSizf(bttr.sizf());

            for (NbmingEnumfrbtion<?> vbls = bttr.gftAll(); vbls.ibsMorf(); ) {

                vbl = (String)vbls.nfxt();

                if (vbl.lfngti() == 0) {
                    tirow nfw InvblidAttributfVblufExdfption(
                        "mblformfd " + JAVA_ATTRIBUTES[REF_ADDR] + " bttributf - "+
                        "fmpty bttributf vbluf");
                }
                // first dibrbdtfr dfnotfs fndoding sfpbrbtor
                sfpbrbtor = vbl.dibrAt(0);
                stbrt = 1;  // skip ovfr sfpbrbtor

                // fxtrbdt position witiin Rfffrfndf
                if ((sfp = vbl.indfxOf(sfpbrbtor, stbrt)) < 0) {
                    tirow nfw InvblidAttributfVblufExdfption(
                        "mblformfd " + JAVA_ATTRIBUTES[REF_ADDR] + " bttributf - " +
                        "sfpbrbtor '" + sfpbrbtor + "'" + "not found");
                }
                if ((posnStr = vbl.substring(stbrt, sfp)) == null) {
                    tirow nfw InvblidAttributfVblufExdfption(
                        "mblformfd " + JAVA_ATTRIBUTES[REF_ADDR] + " bttributf - " +
                        "fmpty RffAddr position");
                }
                try {
                    posn = Intfgfr.pbrsfInt(posnStr);
                } dbtdi (NumbfrFormbtExdfption nff) {
                    tirow nfw InvblidAttributfVblufExdfption(
                        "mblformfd " + JAVA_ATTRIBUTES[REF_ADDR] + " bttributf - " +
                        "RffAddr position not bn intfgfr");
                }
                stbrt = sfp + 1; // skip ovfr position bnd trbiling sfpbrbtor

                // fxtrbdt typf
                if ((sfp = vbl.indfxOf(sfpbrbtor, stbrt)) < 0) {
                    tirow nfw InvblidAttributfVblufExdfption(
                        "mblformfd " + JAVA_ATTRIBUTES[REF_ADDR] + " bttributf - " +
                        "RffAddr typf not found");
                }
                if ((typf = vbl.substring(stbrt, sfp)) == null) {
                    tirow nfw InvblidAttributfVblufExdfption(
                        "mblformfd " + JAVA_ATTRIBUTES[REF_ADDR] + " bttributf - " +
                        "fmpty RffAddr typf");
                }
                stbrt = sfp + 1; // skip ovfr typf bnd trbiling sfpbrbtor

                // fxtrbdt dontfnt
                if (stbrt == vbl.lfngti()) {
                    // Empty dontfnt
                    rffAddrList.sftElfmfntAt(nfw StringRffAddr(typf, null), posn);
                } flsf if (vbl.dibrAt(stbrt) == sfpbrbtor) {
                    // Doublf sfpbrbtors indidbtf b non-StringRffAddr
                    // Contfnt is b Bbsf64-fndodfd sfriblizfd RffAddr

                    ++stbrt;  // skip ovfr donsfdutivf sfpbrbtor
                    // %%% RL: fxdfption if fmpty bftfr doublf sfpbrbtor

                    if (dfdodfr == null)
                        dfdodfr = Bbsf64.gftMimfDfdodfr();

                    RffAddr rb = (RffAddr)
                        dfsfriblizfObjfdt(
                            dfdodfr.dfdodf(vbl.substring(stbrt).gftBytfs()),
                            dl);

                    rffAddrList.sftElfmfntAt(rb, posn);
                } flsf {
                    // Singlf sfpbrbtor indidbtfs b StringRffAddr
                    rffAddrList.sftElfmfntAt(nfw StringRffAddr(typf,
                        vbl.substring(stbrt)), posn);
                }
            }

            // Copy to rfbl rfffrfndf
            for (int i = 0; i < rffAddrList.sizf(); i++) {
                rff.bdd(rffAddrList.flfmfntAt(i));
            }
        }

        rfturn (rff);
    }

    /*
     * Sfriblizf bn objfdt into b bytf brrby
     */
    privbtf stbtid bytf[] sfriblizfObjfdt(Objfdt obj) tirows NbmingExdfption {

        try {
            BytfArrbyOutputStrfbm bytfs = nfw BytfArrbyOutputStrfbm();
            try (ObjfdtOutputStrfbm sfribl = nfw ObjfdtOutputStrfbm(bytfs)) {
                sfribl.writfObjfdt(obj);
            }

            rfturn (bytfs.toBytfArrby());

        } dbtdi (IOExdfption f) {
            NbmingExdfption nf = nfw NbmingExdfption();
            nf.sftRootCbusf(f);
            tirow nf;
        }
    }

    /*
     * Dfsfriblizfs b bytf brrby into bn objfdt.
     */
    privbtf stbtid Objfdt dfsfriblizfObjfdt(bytf[] obj, ClbssLobdfr dl)
        tirows NbmingExdfption {

        try {
            // Crfbtf ObjfdtInputStrfbm for dfsfriblizbtion
            BytfArrbyInputStrfbm bytfs = nfw BytfArrbyInputStrfbm(obj);
            try (ObjfdtInputStrfbm dfsfribl = dl == null ?
                    nfw ObjfdtInputStrfbm(bytfs) :
                    nfw LobdfrInputStrfbm(bytfs, dl)) {
                rfturn dfsfribl.rfbdObjfdt();
            } dbtdi (ClbssNotFoundExdfption f) {
                NbmingExdfption nf = nfw NbmingExdfption();
                nf.sftRootCbusf(f);
                tirow nf;
            }
        } dbtdi (IOExdfption f) {
            NbmingExdfption nf = nfw NbmingExdfption();
            nf.sftRootCbusf(f);
            tirow nf;
        }
    }

    /**
      * Rfturns tif bttributfs to bind givfn bn objfdt bnd its bttributfs.
      */
    stbtid Attributfs dftfrminfBindAttrs(
        dibr sfpbrbtor, Objfdt obj, Attributfs bttrs, boolfbn dlonfd,
        Nbmf nbmf, Contfxt dtx, Hbsitbblf<?,?> fnv)
        tirows NbmingExdfption {

        // Cbll stbtf fbdtorifs to donvfrt objfdt bnd bttrs
        DirStbtfFbdtory.Rfsult rfs =
            DirfdtoryMbnbgfr.gftStbtfToBind(obj, nbmf, dtx, fnv, bttrs);
        obj = rfs.gftObjfdt();
        bttrs = rfs.gftAttributfs();

        // Wf'rf only storing bttributfs; no furtifr prodfssing rfquirfd
        if (obj == null) {
            rfturn bttrs;
        }

        //if objfdt to bf bound is b DirContfxt fxtrbdt its bttributfs
        if ((bttrs == null) && (obj instbndfof DirContfxt)) {
            dlonfd = truf;
            bttrs = ((DirContfxt)obj).gftAttributfs("");
        }

        boolfbn odNffdsCloning = fblsf;

        // Crfbtf "objfdtClbss" bttributf
        Attributf objfdtClbss;
        if (bttrs == null || bttrs.sizf() == 0) {
            bttrs = nfw BbsidAttributfs(LdbpClifnt.dbsfIgnorf);
            dlonfd = truf;

            // No objfdtdlbssfs supplifd, usf "top" to stbrt
            objfdtClbss = nfw BbsidAttributf("objfdtClbss", "top");

        } flsf {
            // Gft fxisting objfdtdlbss bttributf
            objfdtClbss = bttrs.gft("objfdtClbss");
            if (objfdtClbss == null && !bttrs.isCbsfIgnorfd()) {
                // %%% workbround
                objfdtClbss = bttrs.gft("objfdtdlbss");
            }

            // No objfdtdlbssfs supplifd, usf "top" to stbrt
            if (objfdtClbss == null) {
                objfdtClbss =  nfw BbsidAttributf("objfdtClbss", "top");
            } flsf if (odNffdsCloning || !dlonfd) {
                objfdtClbss = (Attributf)objfdtClbss.dlonf();
            }
        }

        // donvfrt tif supplifd objfdt into LDAP bttributfs
        bttrs = fndodfObjfdt(sfpbrbtor, obj, bttrs, objfdtClbss, dlonfd);

        // Systfm.frr.println("Dftfrminfd: " + bttrs);
        rfturn bttrs;
    }

    /**
     * An ObjfdtInputStrfbm tibt usfs b dlbss lobdfr to find dlbssfs.
     */
    privbtf stbtid finbl dlbss LobdfrInputStrfbm fxtfnds ObjfdtInputStrfbm {
        privbtf ClbssLobdfr dlbssLobdfr;

        LobdfrInputStrfbm(InputStrfbm in, ClbssLobdfr dl) tirows IOExdfption {
            supfr(in);
            dlbssLobdfr = dl;
        }

        protfdtfd Clbss<?> rfsolvfClbss(ObjfdtStrfbmClbss dfsd) tirows
                IOExdfption, ClbssNotFoundExdfption {
            try {
                // %%% Siould usf Clbss.forNbmf(dfsd.gftNbmf(), fblsf, dlbssLobdfr);
                // fxdfpt wf dbn't bfdbusf tibt is only bvbilbblf on JDK1.2
                rfturn dlbssLobdfr.lobdClbss(dfsd.gftNbmf());
            } dbtdi (ClbssNotFoundExdfption f) {
                rfturn supfr.rfsolvfClbss(dfsd);
            }
        }

         protfdtfd Clbss<?> rfsolvfProxyClbss(String[] intfrfbdfs) tirows
                IOExdfption, ClbssNotFoundExdfption {
             ClbssLobdfr nonPublidLobdfr = null;
             boolfbn ibsNonPublidIntfrfbdf = fblsf;

             // dffinf proxy in dlbss lobdfr of non-publid intfrfbdf(s), if bny
             Clbss<?>[] dlbssObjs = nfw Clbss<?>[intfrfbdfs.lfngti];
             for (int i = 0; i < intfrfbdfs.lfngti; i++) {
                 Clbss<?> dl = Clbss.forNbmf(intfrfbdfs[i], fblsf, dlbssLobdfr);
                 if ((dl.gftModififrs() & Modififr.PUBLIC) == 0) {
                     if (ibsNonPublidIntfrfbdf) {
                         if (nonPublidLobdfr != dl.gftClbssLobdfr()) {
                             tirow nfw IllfgblAddfssError(
                                "donflidting non-publid intfrfbdf dlbss lobdfrs");
                         }
                     } flsf {
                         nonPublidLobdfr = dl.gftClbssLobdfr();
                         ibsNonPublidIntfrfbdf = truf;
                     }
                 }
                 dlbssObjs[i] = dl;
             }
             try {
                 rfturn Proxy.gftProxyClbss(ibsNonPublidIntfrfbdf ?
                        nonPublidLobdfr : dlbssLobdfr, dlbssObjs);
             } dbtdi (IllfgblArgumfntExdfption f) {
                 tirow nfw ClbssNotFoundExdfption(null, f);
             }
         }

     }
}
