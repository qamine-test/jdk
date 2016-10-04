/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

import jbvb.io.ObjfdtStrfbmClbss.WfbkClbssKfy;
import jbvb.lbng.rff.RfffrfndfQufuf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import stbtid jbvb.io.ObjfdtStrfbmClbss.prodfssQufuf;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * An ObjfdtOutputStrfbm writfs primitivf dbtb typfs bnd grbpis of Jbvb objfdts
 * to bn OutputStrfbm.  Tif objfdts dbn bf rfbd (rfdonstitutfd) using bn
 * ObjfdtInputStrfbm.  Pfrsistfnt storbgf of objfdts dbn bf bddomplisifd by
 * using b filf for tif strfbm.  If tif strfbm is b nftwork sodkft strfbm, tif
 * objfdts dbn bf rfdonstitutfd on bnotifr iost or in bnotifr prodfss.
 *
 * <p>Only objfdts tibt support tif jbvb.io.Sfriblizbblf intfrfbdf dbn bf
 * writtfn to strfbms.  Tif dlbss of fbdi sfriblizbblf objfdt is fndodfd
 * indluding tif dlbss nbmf bnd signbturf of tif dlbss, tif vblufs of tif
 * objfdt's fiflds bnd brrbys, bnd tif dlosurf of bny otifr objfdts rfffrfndfd
 * from tif initibl objfdts.
 *
 * <p>Tif mftiod writfObjfdt is usfd to writf bn objfdt to tif strfbm.  Any
 * objfdt, indluding Strings bnd brrbys, is writtfn witi writfObjfdt. Multiplf
 * objfdts or primitivfs dbn bf writtfn to tif strfbm.  Tif objfdts must bf
 * rfbd bbdk from tif dorrfsponding ObjfdtInputstrfbm witi tif sbmf typfs bnd
 * in tif sbmf ordfr bs tify wfrf writtfn.
 *
 * <p>Primitivf dbtb typfs dbn blso bf writtfn to tif strfbm using tif
 * bppropribtf mftiods from DbtbOutput. Strings dbn blso bf writtfn using tif
 * writfUTF mftiod.
 *
 * <p>Tif dffbult sfriblizbtion mfdibnism for bn objfdt writfs tif dlbss of tif
 * objfdt, tif dlbss signbturf, bnd tif vblufs of bll non-trbnsifnt bnd
 * non-stbtid fiflds.  Rfffrfndfs to otifr objfdts (fxdfpt in trbnsifnt or
 * stbtid fiflds) dbusf tiosf objfdts to bf writtfn blso. Multiplf rfffrfndfs
 * to b singlf objfdt brf fndodfd using b rfffrfndf sibring mfdibnism so tibt
 * grbpis of objfdts dbn bf rfstorfd to tif sbmf sibpf bs wifn tif originbl wbs
 * writtfn.
 *
 * <p>For fxbmplf to writf bn objfdt tibt dbn bf rfbd by tif fxbmplf in
 * ObjfdtInputStrfbm:
 * <br>
 * <prf>
 *      FilfOutputStrfbm fos = nfw FilfOutputStrfbm("t.tmp");
 *      ObjfdtOutputStrfbm oos = nfw ObjfdtOutputStrfbm(fos);
 *
 *      oos.writfInt(12345);
 *      oos.writfObjfdt("Todby");
 *      oos.writfObjfdt(nfw Dbtf());
 *
 *      oos.dlosf();
 * </prf>
 *
 * <p>Clbssfs tibt rfquirf spfdibl ibndling during tif sfriblizbtion bnd
 * dfsfriblizbtion prodfss must implfmfnt spfdibl mftiods witi tifsf fxbdt
 * signbturfs:
 * <br>
 * <prf>
 * privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm strfbm)
 *     tirows IOExdfption, ClbssNotFoundExdfption;
 * privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm strfbm)
 *     tirows IOExdfption
 * privbtf void rfbdObjfdtNoDbtb()
 *     tirows ObjfdtStrfbmExdfption;
 * </prf>
 *
 * <p>Tif writfObjfdt mftiod is rfsponsiblf for writing tif stbtf of tif objfdt
 * for its pbrtidulbr dlbss so tibt tif dorrfsponding rfbdObjfdt mftiod dbn
 * rfstorf it.  Tif mftiod dofs not nffd to dondfrn itsflf witi tif stbtf
 * bflonging to tif objfdt's supfrdlbssfs or subdlbssfs.  Stbtf is sbvfd by
 * writing tif individubl fiflds to tif ObjfdtOutputStrfbm using tif
 * writfObjfdt mftiod or by using tif mftiods for primitivf dbtb typfs
 * supportfd by DbtbOutput.
 *
 * <p>Sfriblizbtion dofs not writf out tif fiflds of bny objfdt tibt dofs not
 * implfmfnt tif jbvb.io.Sfriblizbblf intfrfbdf.  Subdlbssfs of Objfdts tibt
 * brf not sfriblizbblf dbn bf sfriblizbblf. In tiis dbsf tif non-sfriblizbblf
 * dlbss must ibvf b no-brg donstrudtor to bllow its fiflds to bf initiblizfd.
 * In tiis dbsf it is tif rfsponsibility of tif subdlbss to sbvf bnd rfstorf
 * tif stbtf of tif non-sfriblizbblf dlbss. It is frfqufntly tif dbsf tibt tif
 * fiflds of tibt dlbss brf bddfssiblf (publid, pbdkbgf, or protfdtfd) or tibt
 * tifrf brf gft bnd sft mftiods tibt dbn bf usfd to rfstorf tif stbtf.
 *
 * <p>Sfriblizbtion of bn objfdt dbn bf prfvfntfd by implfmfnting writfObjfdt
 * bnd rfbdObjfdt mftiods tibt tirow tif NotSfriblizbblfExdfption.  Tif
 * fxdfption will bf dbugit by tif ObjfdtOutputStrfbm bnd bbort tif
 * sfriblizbtion prodfss.
 *
 * <p>Implfmfnting tif Extfrnblizbblf intfrfbdf bllows tif objfdt to bssumf
 * domplftf dontrol ovfr tif dontfnts bnd formbt of tif objfdt's sfriblizfd
 * form.  Tif mftiods of tif Extfrnblizbblf intfrfbdf, writfExtfrnbl bnd
 * rfbdExtfrnbl, brf dbllfd to sbvf bnd rfstorf tif objfdts stbtf.  Wifn
 * implfmfntfd by b dlbss tify dbn writf bnd rfbd tifir own stbtf using bll of
 * tif mftiods of ObjfdtOutput bnd ObjfdtInput.  It is tif rfsponsibility of
 * tif objfdts to ibndlf bny vfrsioning tibt oddurs.
 *
 * <p>Enum donstbnts brf sfriblizfd difffrfntly tibn ordinbry sfriblizbblf or
 * fxtfrnblizbblf objfdts.  Tif sfriblizfd form of bn fnum donstbnt donsists
 * solfly of its nbmf; fifld vblufs of tif donstbnt brf not trbnsmittfd.  To
 * sfriblizf bn fnum donstbnt, ObjfdtOutputStrfbm writfs tif string rfturnfd by
 * tif donstbnt's nbmf mftiod.  Likf otifr sfriblizbblf or fxtfrnblizbblf
 * objfdts, fnum donstbnts dbn fundtion bs tif tbrgfts of bbdk rfffrfndfs
 * bppfbring subsfqufntly in tif sfriblizbtion strfbm.  Tif prodfss by wiidi
 * fnum donstbnts brf sfriblizfd dbnnot bf dustomizfd; bny dlbss-spfdifid
 * writfObjfdt bnd writfRfplbdf mftiods dffinfd by fnum typfs brf ignorfd
 * during sfriblizbtion.  Similbrly, bny sfriblPfrsistfntFiflds or
 * sfriblVfrsionUID fifld dfdlbrbtions brf blso ignorfd--bll fnum typfs ibvf b
 * fixfd sfriblVfrsionUID of 0L.
 *
 * <p>Primitivf dbtb, fxdluding sfriblizbblf fiflds bnd fxtfrnblizbblf dbtb, is
 * writtfn to tif ObjfdtOutputStrfbm in blodk-dbtb rfdords. A blodk dbtb rfdord
 * is domposfd of b ifbdfr bnd dbtb. Tif blodk dbtb ifbdfr donsists of b mbrkfr
 * bnd tif numbfr of bytfs to follow tif ifbdfr.  Consfdutivf primitivf dbtb
 * writfs brf mfrgfd into onf blodk-dbtb rfdord.  Tif blodking fbdtor usfd for
 * b blodk-dbtb rfdord will bf 1024 bytfs.  Ebdi blodk-dbtb rfdord will bf
 * fillfd up to 1024 bytfs, or bf writtfn wifnfvfr tifrf is b tfrminbtion of
 * blodk-dbtb modf.  Cblls to tif ObjfdtOutputStrfbm mftiods writfObjfdt,
 * dffbultWritfObjfdt bnd writfFiflds initiblly tfrminbtf bny fxisting
 * blodk-dbtb rfdord.
 *
 * @butior      Mikf Wbrrfs
 * @butior      Rogfr Riggs
 * @sff jbvb.io.DbtbOutput
 * @sff jbvb.io.ObjfdtInputStrfbm
 * @sff jbvb.io.Sfriblizbblf
 * @sff jbvb.io.Extfrnblizbblf
 * @sff <b irff="../../../plbtform/sfriblizbtion/spfd/output.itml">Objfdt Sfriblizbtion Spfdifidbtion, Sfdtion 2, Objfdt Output Clbssfs</b>
 * @sindf       1.1
 */
publid dlbss ObjfdtOutputStrfbm
    fxtfnds OutputStrfbm implfmfnts ObjfdtOutput, ObjfdtStrfbmConstbnts
{

    privbtf stbtid dlbss Cbdifs {
        /** dbdif of subdlbss sfdurity budit rfsults */
        stbtid finbl CondurrfntMbp<WfbkClbssKfy,Boolfbn> subdlbssAudits =
            nfw CondurrfntHbsiMbp<>();

        /** qufuf for WfbkRfffrfndfs to buditfd subdlbssfs */
        stbtid finbl RfffrfndfQufuf<Clbss<?>> subdlbssAuditsQufuf =
            nfw RfffrfndfQufuf<>();
    }

    /** filtfr strfbm for ibndling blodk dbtb donvfrsion */
    privbtf finbl BlodkDbtbOutputStrfbm bout;
    /** obj -> wirf ibndlf mbp */
    privbtf finbl HbndlfTbblf ibndlfs;
    /** obj -> rfplbdfmfnt obj mbp */
    privbtf finbl RfplbdfTbblf subs;
    /** strfbm protodol vfrsion */
    privbtf int protodol = PROTOCOL_VERSION_2;
    /** rfdursion dfpti */
    privbtf int dfpti;

    /** bufffr for writing primitivf fifld vblufs */
    privbtf bytf[] primVbls;

    /** if truf, invokf writfObjfdtOvfrridf() instfbd of writfObjfdt() */
    privbtf finbl boolfbn fnbblfOvfrridf;
    /** if truf, invokf rfplbdfObjfdt() */
    privbtf boolfbn fnbblfRfplbdf;

    // vblufs bflow vblid only during updblls to writfObjfdt()/writfExtfrnbl()
    /**
     * Contfxt during updblls to dlbss-dffinfd writfObjfdt mftiods; iolds
     * objfdt durrfntly bfing sfriblizfd bnd dfsdriptor for durrfnt dlbss.
     * Null wifn not during writfObjfdt updbll.
     */
    privbtf SfriblCbllbbdkContfxt durContfxt;
    /** durrfnt PutFifld objfdt */
    privbtf PutFifldImpl durPut;

    /** dustom storbgf for dfbug trbdf info */
    privbtf finbl DfbugTrbdfInfoStbdk dfbugInfoStbdk;

    /**
     * vbluf of "sun.io.sfriblizbtion.fxtfndfdDfbugInfo" propfrty,
     * bs truf or fblsf for fxtfndfd informbtion bbout fxdfption's plbdf
     */
    privbtf stbtid finbl boolfbn fxtfndfdDfbugInfo =
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftBoolfbnAdtion(
                "sun.io.sfriblizbtion.fxtfndfdDfbugInfo")).boolfbnVbluf();

    /**
     * Crfbtfs bn ObjfdtOutputStrfbm tibt writfs to tif spfdififd OutputStrfbm.
     * Tiis donstrudtor writfs tif sfriblizbtion strfbm ifbdfr to tif
     * undfrlying strfbm; dbllfrs mby wisi to flusi tif strfbm immfdibtfly to
     * fnsurf tibt donstrudtors for rfdfiving ObjfdtInputStrfbms will not blodk
     * wifn rfbding tif ifbdfr.
     *
     * <p>If b sfdurity mbnbgfr is instbllfd, tiis donstrudtor will difdk for
     * tif "fnbblfSubdlbssImplfmfntbtion" SfriblizbblfPfrmission wifn invokfd
     * dirfdtly or indirfdtly by tif donstrudtor of b subdlbss wiidi ovfrridfs
     * tif ObjfdtOutputStrfbm.putFiflds or ObjfdtOutputStrfbm.writfUnsibrfd
     * mftiods.
     *
     * @pbrbm   out output strfbm to writf to
     * @tirows  IOExdfption if bn I/O frror oddurs wiilf writing strfbm ifbdfr
     * @tirows  SfdurityExdfption if untrustfd subdlbss illfgblly ovfrridfs
     *          sfdurity-sfnsitivf mftiods
     * @tirows  NullPointfrExdfption if <dodf>out</dodf> is <dodf>null</dodf>
     * @sindf   1.4
     * @sff     ObjfdtOutputStrfbm#ObjfdtOutputStrfbm()
     * @sff     ObjfdtOutputStrfbm#putFiflds()
     * @sff     ObjfdtInputStrfbm#ObjfdtInputStrfbm(InputStrfbm)
     */
    publid ObjfdtOutputStrfbm(OutputStrfbm out) tirows IOExdfption {
        vfrifySubdlbss();
        bout = nfw BlodkDbtbOutputStrfbm(out);
        ibndlfs = nfw HbndlfTbblf(10, (flobt) 3.00);
        subs = nfw RfplbdfTbblf(10, (flobt) 3.00);
        fnbblfOvfrridf = fblsf;
        writfStrfbmHfbdfr();
        bout.sftBlodkDbtbModf(truf);
        if (fxtfndfdDfbugInfo) {
            dfbugInfoStbdk = nfw DfbugTrbdfInfoStbdk();
        } flsf {
            dfbugInfoStbdk = null;
        }
    }

    /**
     * Providf b wby for subdlbssfs tibt brf domplftfly rfimplfmfnting
     * ObjfdtOutputStrfbm to not ibvf to bllodbtf privbtf dbtb just usfd by
     * tiis implfmfntbtion of ObjfdtOutputStrfbm.
     *
     * <p>If tifrf is b sfdurity mbnbgfr instbllfd, tiis mftiod first dblls tif
     * sfdurity mbnbgfr's <dodf>difdkPfrmission</dodf> mftiod witi b
     * <dodf>SfriblizbblfPfrmission("fnbblfSubdlbssImplfmfntbtion")</dodf>
     * pfrmission to fnsurf it's ok to fnbblf subdlbssing.
     *
     * @tirows  SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *          <dodf>difdkPfrmission</dodf> mftiod dfnifs fnbbling
     *          subdlbssing.
     * @tirows  IOExdfption if bn I/O frror oddurs wiilf drfbting tiis strfbm
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff jbvb.io.SfriblizbblfPfrmission
     */
    protfdtfd ObjfdtOutputStrfbm() tirows IOExdfption, SfdurityExdfption {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(SUBCLASS_IMPLEMENTATION_PERMISSION);
        }
        bout = null;
        ibndlfs = null;
        subs = null;
        fnbblfOvfrridf = truf;
        dfbugInfoStbdk = null;
    }

    /**
     * Spfdify strfbm protodol vfrsion to usf wifn writing tif strfbm.
     *
     * <p>Tiis routinf providfs b iook to fnbblf tif durrfnt vfrsion of
     * Sfriblizbtion to writf in b formbt tibt is bbdkwbrds dompbtiblf to b
     * prfvious vfrsion of tif strfbm formbt.
     *
     * <p>Evfry fffort will bf mbdf to bvoid introduding bdditionbl
     * bbdkwbrds indompbtibilitifs; iowfvfr, somftimfs tifrf is no
     * otifr bltfrnbtivf.
     *
     * @pbrbm   vfrsion usf ProtodolVfrsion from jbvb.io.ObjfdtStrfbmConstbnts.
     * @tirows  IllfgblStbtfExdfption if dbllfd bftfr bny objfdts
     *          ibvf bffn sfriblizfd.
     * @tirows  IllfgblArgumfntExdfption if invblid vfrsion is pbssfd in.
     * @tirows  IOExdfption if I/O frrors oddur
     * @sff jbvb.io.ObjfdtStrfbmConstbnts#PROTOCOL_VERSION_1
     * @sff jbvb.io.ObjfdtStrfbmConstbnts#PROTOCOL_VERSION_2
     * @sindf   1.2
     */
    publid void usfProtodolVfrsion(int vfrsion) tirows IOExdfption {
        if (ibndlfs.sizf() != 0) {
            // REMIND: implfmfnt bfttfr difdk for pristinf strfbm?
            tirow nfw IllfgblStbtfExdfption("strfbm non-fmpty");
        }
        switdi (vfrsion) {
            dbsf PROTOCOL_VERSION_1:
            dbsf PROTOCOL_VERSION_2:
                protodol = vfrsion;
                brfbk;

            dffbult:
                tirow nfw IllfgblArgumfntExdfption(
                    "unknown vfrsion: " + vfrsion);
        }
    }

    /**
     * Writf tif spfdififd objfdt to tif ObjfdtOutputStrfbm.  Tif dlbss of tif
     * objfdt, tif signbturf of tif dlbss, bnd tif vblufs of tif non-trbnsifnt
     * bnd non-stbtid fiflds of tif dlbss bnd bll of its supfrtypfs brf
     * writtfn.  Dffbult sfriblizbtion for b dlbss dbn bf ovfrriddfn using tif
     * writfObjfdt bnd tif rfbdObjfdt mftiods.  Objfdts rfffrfndfd by tiis
     * objfdt brf writtfn trbnsitivfly so tibt b domplftf fquivblfnt grbpi of
     * objfdts dbn bf rfdonstrudtfd by bn ObjfdtInputStrfbm.
     *
     * <p>Exdfptions brf tirown for problfms witi tif OutputStrfbm bnd for
     * dlbssfs tibt siould not bf sfriblizfd.  All fxdfptions brf fbtbl to tif
     * OutputStrfbm, wiidi is lfft in bn indftfrminbtf stbtf, bnd it is up to
     * tif dbllfr to ignorf or rfdovfr tif strfbm stbtf.
     *
     * @tirows  InvblidClbssExdfption Somftiing is wrong witi b dlbss usfd by
     *          sfriblizbtion.
     * @tirows  NotSfriblizbblfExdfption Somf objfdt to bf sfriblizfd dofs not
     *          implfmfnt tif jbvb.io.Sfriblizbblf intfrfbdf.
     * @tirows  IOExdfption Any fxdfption tirown by tif undfrlying
     *          OutputStrfbm.
     */
    publid finbl void writfObjfdt(Objfdt obj) tirows IOExdfption {
        if (fnbblfOvfrridf) {
            writfObjfdtOvfrridf(obj);
            rfturn;
        }
        try {
            writfObjfdt0(obj, fblsf);
        } dbtdi (IOExdfption fx) {
            if (dfpti == 0) {
                writfFbtblExdfption(fx);
            }
            tirow fx;
        }
    }

    /**
     * Mftiod usfd by subdlbssfs to ovfrridf tif dffbult writfObjfdt mftiod.
     * Tiis mftiod is dbllfd by trustfd subdlbssfs of ObjfdtInputStrfbm tibt
     * donstrudtfd ObjfdtInputStrfbm using tif protfdtfd no-brg donstrudtor.
     * Tif subdlbss is fxpfdtfd to providf bn ovfrridf mftiod witi tif modififr
     * "finbl".
     *
     * @pbrbm   obj objfdt to bf writtfn to tif undfrlying strfbm
     * @tirows  IOExdfption if tifrf brf I/O frrors wiilf writing to tif
     *          undfrlying strfbm
     * @sff #ObjfdtOutputStrfbm()
     * @sff #writfObjfdt(Objfdt)
     * @sindf 1.2
     */
    protfdtfd void writfObjfdtOvfrridf(Objfdt obj) tirows IOExdfption {
    }

    /**
     * Writfs bn "unsibrfd" objfdt to tif ObjfdtOutputStrfbm.  Tiis mftiod is
     * idfntidbl to writfObjfdt, fxdfpt tibt it blwbys writfs tif givfn objfdt
     * bs b nfw, uniquf objfdt in tif strfbm (bs opposfd to b bbdk-rfffrfndf
     * pointing to b prfviously sfriblizfd instbndf).  Spfdifidblly:
     * <ul>
     *   <li>An objfdt writtfn vib writfUnsibrfd is blwbys sfriblizfd in tif
     *       sbmf mbnnfr bs b nfwly bppfbring objfdt (bn objfdt tibt ibs not
     *       bffn writtfn to tif strfbm yft), rfgbrdlfss of wiftifr or not tif
     *       objfdt ibs bffn writtfn prfviously.
     *
     *   <li>If writfObjfdt is usfd to writf bn objfdt tibt ibs bffn prfviously
     *       writtfn witi writfUnsibrfd, tif prfvious writfUnsibrfd opfrbtion
     *       is trfbtfd bs if it wfrf b writf of b sfpbrbtf objfdt.  In otifr
     *       words, ObjfdtOutputStrfbm will nfvfr gfnfrbtf bbdk-rfffrfndfs to
     *       objfdt dbtb writtfn by dblls to writfUnsibrfd.
     * </ul>
     * Wiilf writing bn objfdt vib writfUnsibrfd dofs not in itsflf gubrbntff b
     * uniquf rfffrfndf to tif objfdt wifn it is dfsfriblizfd, it bllows b
     * singlf objfdt to bf dffinfd multiplf timfs in b strfbm, so tibt multiplf
     * dblls to rfbdUnsibrfd by tif rfdfivfr will not donflidt.  Notf tibt tif
     * rulfs dfsdribfd bbovf only bpply to tif bbsf-lfvfl objfdt writtfn witi
     * writfUnsibrfd, bnd not to bny trbnsitivfly rfffrfndfd sub-objfdts in tif
     * objfdt grbpi to bf sfriblizfd.
     *
     * <p>ObjfdtOutputStrfbm subdlbssfs wiidi ovfrridf tiis mftiod dbn only bf
     * donstrudtfd in sfdurity dontfxts possfssing tif
     * "fnbblfSubdlbssImplfmfntbtion" SfriblizbblfPfrmission; bny bttfmpt to
     * instbntibtf sudi b subdlbss witiout tiis pfrmission will dbusf b
     * SfdurityExdfption to bf tirown.
     *
     * @pbrbm   obj objfdt to writf to strfbm
     * @tirows  NotSfriblizbblfExdfption if bn objfdt in tif grbpi to bf
     *          sfriblizfd dofs not implfmfnt tif Sfriblizbblf intfrfbdf
     * @tirows  InvblidClbssExdfption if b problfm fxists witi tif dlbss of bn
     *          objfdt to bf sfriblizfd
     * @tirows  IOExdfption if bn I/O frror oddurs during sfriblizbtion
     * @sindf 1.4
     */
    publid void writfUnsibrfd(Objfdt obj) tirows IOExdfption {
        try {
            writfObjfdt0(obj, truf);
        } dbtdi (IOExdfption fx) {
            if (dfpti == 0) {
                writfFbtblExdfption(fx);
            }
            tirow fx;
        }
    }

    /**
     * Writf tif non-stbtid bnd non-trbnsifnt fiflds of tif durrfnt dlbss to
     * tiis strfbm.  Tiis mby only bf dbllfd from tif writfObjfdt mftiod of tif
     * dlbss bfing sfriblizfd. It will tirow tif NotAdtivfExdfption if it is
     * dbllfd otifrwisf.
     *
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          <dodf>OutputStrfbm</dodf>
     */
    publid void dffbultWritfObjfdt() tirows IOExdfption {
        SfriblCbllbbdkContfxt dtx = durContfxt;
        if (dtx == null) {
            tirow nfw NotAdtivfExdfption("not in dbll to writfObjfdt");
        }
        Objfdt durObj = dtx.gftObj();
        ObjfdtStrfbmClbss durDfsd = dtx.gftDfsd();
        bout.sftBlodkDbtbModf(fblsf);
        dffbultWritfFiflds(durObj, durDfsd);
        bout.sftBlodkDbtbModf(truf);
    }

    /**
     * Rftrifvf tif objfdt usfd to bufffr pfrsistfnt fiflds to bf writtfn to
     * tif strfbm.  Tif fiflds will bf writtfn to tif strfbm wifn writfFiflds
     * mftiod is dbllfd.
     *
     * @rfturn  bn instbndf of tif dlbss Putfifld tibt iolds tif sfriblizbblf
     *          fiflds
     * @tirows  IOExdfption if I/O frrors oddur
     * @sindf 1.2
     */
    publid ObjfdtOutputStrfbm.PutFifld putFiflds() tirows IOExdfption {
        if (durPut == null) {
            SfriblCbllbbdkContfxt dtx = durContfxt;
            if (dtx == null) {
                tirow nfw NotAdtivfExdfption("not in dbll to writfObjfdt");
            }
            dtx.difdkAndSftUsfd();
            ObjfdtStrfbmClbss durDfsd = dtx.gftDfsd();
            durPut = nfw PutFifldImpl(durDfsd);
        }
        rfturn durPut;
    }

    /**
     * Writf tif bufffrfd fiflds to tif strfbm.
     *
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     * @tirows  NotAdtivfExdfption Cbllfd wifn b dlbssfs writfObjfdt mftiod wbs
     *          not dbllfd to writf tif stbtf of tif objfdt.
     * @sindf 1.2
     */
    publid void writfFiflds() tirows IOExdfption {
        if (durPut == null) {
            tirow nfw NotAdtivfExdfption("no durrfnt PutFifld objfdt");
        }
        bout.sftBlodkDbtbModf(fblsf);
        durPut.writfFiflds();
        bout.sftBlodkDbtbModf(truf);
    }

    /**
     * Rfsft will disrfgbrd tif stbtf of bny objfdts blrfbdy writtfn to tif
     * strfbm.  Tif stbtf is rfsft to bf tif sbmf bs b nfw ObjfdtOutputStrfbm.
     * Tif durrfnt point in tif strfbm is mbrkfd bs rfsft so tif dorrfsponding
     * ObjfdtInputStrfbm will bf rfsft bt tif sbmf point.  Objfdts prfviously
     * writtfn to tif strfbm will not bf rfffrrfd to bs blrfbdy bfing in tif
     * strfbm.  Tify will bf writtfn to tif strfbm bgbin.
     *
     * @tirows  IOExdfption if rfsft() is invokfd wiilf sfriblizing bn objfdt.
     */
    publid void rfsft() tirows IOExdfption {
        if (dfpti != 0) {
            tirow nfw IOExdfption("strfbm bdtivf");
        }
        bout.sftBlodkDbtbModf(fblsf);
        bout.writfBytf(TC_RESET);
        dlfbr();
        bout.sftBlodkDbtbModf(truf);
    }

    /**
     * Subdlbssfs mby implfmfnt tiis mftiod to bllow dlbss dbtb to bf storfd in
     * tif strfbm. By dffbult tiis mftiod dofs notiing.  Tif dorrfsponding
     * mftiod in ObjfdtInputStrfbm is rfsolvfClbss.  Tiis mftiod is dbllfd
     * fxbdtly ondf for fbdi uniquf dlbss in tif strfbm.  Tif dlbss nbmf bnd
     * signbturf will ibvf blrfbdy bffn writtfn to tif strfbm.  Tiis mftiod mby
     * mbkf frff usf of tif ObjfdtOutputStrfbm to sbvf bny rfprfsfntbtion of
     * tif dlbss it dffms suitbblf (for fxbmplf, tif bytfs of tif dlbss filf).
     * Tif rfsolvfClbss mftiod in tif dorrfsponding subdlbss of
     * ObjfdtInputStrfbm must rfbd bnd usf bny dbtb or objfdts writtfn by
     * bnnotbtfClbss.
     *
     * @pbrbm   dl tif dlbss to bnnotbtf dustom dbtb for
     * @tirows  IOExdfption Any fxdfption tirown by tif undfrlying
     *          OutputStrfbm.
     */
    protfdtfd void bnnotbtfClbss(Clbss<?> dl) tirows IOExdfption {
    }

    /**
     * Subdlbssfs mby implfmfnt tiis mftiod to storf dustom dbtb in tif strfbm
     * blong witi dfsdriptors for dynbmid proxy dlbssfs.
     *
     * <p>Tiis mftiod is dbllfd fxbdtly ondf for fbdi uniquf proxy dlbss
     * dfsdriptor in tif strfbm.  Tif dffbult implfmfntbtion of tiis mftiod in
     * <dodf>ObjfdtOutputStrfbm</dodf> dofs notiing.
     *
     * <p>Tif dorrfsponding mftiod in <dodf>ObjfdtInputStrfbm</dodf> is
     * <dodf>rfsolvfProxyClbss</dodf>.  For b givfn subdlbss of
     * <dodf>ObjfdtOutputStrfbm</dodf> tibt ovfrridfs tiis mftiod, tif
     * <dodf>rfsolvfProxyClbss</dodf> mftiod in tif dorrfsponding subdlbss of
     * <dodf>ObjfdtInputStrfbm</dodf> must rfbd bny dbtb or objfdts writtfn by
     * <dodf>bnnotbtfProxyClbss</dodf>.
     *
     * @pbrbm   dl tif proxy dlbss to bnnotbtf dustom dbtb for
     * @tirows  IOExdfption bny fxdfption tirown by tif undfrlying
     *          <dodf>OutputStrfbm</dodf>
     * @sff ObjfdtInputStrfbm#rfsolvfProxyClbss(String[])
     * @sindf   1.3
     */
    protfdtfd void bnnotbtfProxyClbss(Clbss<?> dl) tirows IOExdfption {
    }

    /**
     * Tiis mftiod will bllow trustfd subdlbssfs of ObjfdtOutputStrfbm to
     * substitutf onf objfdt for bnotifr during sfriblizbtion. Rfplbding
     * objfdts is disbblfd until fnbblfRfplbdfObjfdt is dbllfd. Tif
     * fnbblfRfplbdfObjfdt mftiod difdks tibt tif strfbm rfqufsting to do
     * rfplbdfmfnt dbn bf trustfd.  Tif first oddurrfndf of fbdi objfdt writtfn
     * into tif sfriblizbtion strfbm is pbssfd to rfplbdfObjfdt.  Subsfqufnt
     * rfffrfndfs to tif objfdt brf rfplbdfd by tif objfdt rfturnfd by tif
     * originbl dbll to rfplbdfObjfdt.  To fnsurf tibt tif privbtf stbtf of
     * objfdts is not unintfntionblly fxposfd, only trustfd strfbms mby usf
     * rfplbdfObjfdt.
     *
     * <p>Tif ObjfdtOutputStrfbm.writfObjfdt mftiod tbkfs b pbrbmftfr of typf
     * Objfdt (bs opposfd to typf Sfriblizbblf) to bllow for dbsfs wifrf
     * non-sfriblizbblf objfdts brf rfplbdfd by sfriblizbblf onfs.
     *
     * <p>Wifn b subdlbss is rfplbding objfdts it must insurf tibt fitifr b
     * domplfmfntbry substitution must bf mbdf during dfsfriblizbtion or tibt
     * tif substitutfd objfdt is dompbtiblf witi fvfry fifld wifrf tif
     * rfffrfndf will bf storfd.  Objfdts wiosf typf is not b subdlbss of tif
     * typf of tif fifld or brrby flfmfnt bbort tif sfriblizbtion by rbising bn
     * fxdfption bnd tif objfdt is not bf storfd.
     *
     * <p>Tiis mftiod is dbllfd only ondf wifn fbdi objfdt is first
     * fndountfrfd.  All subsfqufnt rfffrfndfs to tif objfdt will bf rfdirfdtfd
     * to tif nfw objfdt. Tiis mftiod siould rfturn tif objfdt to bf
     * substitutfd or tif originbl objfdt.
     *
     * <p>Null dbn bf rfturnfd bs tif objfdt to bf substitutfd, but mby dbusf
     * NullRfffrfndfExdfption in dlbssfs tibt dontbin rfffrfndfs to tif
     * originbl objfdt sindf tify mby bf fxpfdting bn objfdt instfbd of
     * null.
     *
     * @pbrbm   obj tif objfdt to bf rfplbdfd
     * @rfturn  tif bltfrnbtf objfdt tibt rfplbdfd tif spfdififd onf
     * @tirows  IOExdfption Any fxdfption tirown by tif undfrlying
     *          OutputStrfbm.
     */
    protfdtfd Objfdt rfplbdfObjfdt(Objfdt obj) tirows IOExdfption {
        rfturn obj;
    }

    /**
     * Enbblf tif strfbm to do rfplbdfmfnt of objfdts in tif strfbm.  Wifn
     * fnbblfd, tif rfplbdfObjfdt mftiod is dbllfd for fvfry objfdt bfing
     * sfriblizfd.
     *
     * <p>If <dodf>fnbblf</dodf> is truf, bnd tifrf is b sfdurity mbnbgfr
     * instbllfd, tiis mftiod first dblls tif sfdurity mbnbgfr's
     * <dodf>difdkPfrmission</dodf> mftiod witi b
     * <dodf>SfriblizbblfPfrmission("fnbblfSubstitution")</dodf> pfrmission to
     * fnsurf it's ok to fnbblf tif strfbm to do rfplbdfmfnt of objfdts in tif
     * strfbm.
     *
     * @pbrbm   fnbblf boolfbn pbrbmftfr to fnbblf rfplbdfmfnt of objfdts
     * @rfturn  tif prfvious sftting bfforf tiis mftiod wbs invokfd
     * @tirows  SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *          <dodf>difdkPfrmission</dodf> mftiod dfnifs fnbbling tif strfbm
     *          to do rfplbdfmfnt of objfdts in tif strfbm.
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff jbvb.io.SfriblizbblfPfrmission
     */
    protfdtfd boolfbn fnbblfRfplbdfObjfdt(boolfbn fnbblf)
        tirows SfdurityExdfption
    {
        if (fnbblf == fnbblfRfplbdf) {
            rfturn fnbblf;
        }
        if (fnbblf) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                sm.difdkPfrmission(SUBSTITUTION_PERMISSION);
            }
        }
        fnbblfRfplbdf = fnbblf;
        rfturn !fnbblfRfplbdf;
    }

    /**
     * Tif writfStrfbmHfbdfr mftiod is providfd so subdlbssfs dbn bppfnd or
     * prfpfnd tifir own ifbdfr to tif strfbm.  It writfs tif mbgid numbfr bnd
     * vfrsion to tif strfbm.
     *
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    protfdtfd void writfStrfbmHfbdfr() tirows IOExdfption {
        bout.writfSiort(STREAM_MAGIC);
        bout.writfSiort(STREAM_VERSION);
    }

    /**
     * Writf tif spfdififd dlbss dfsdriptor to tif ObjfdtOutputStrfbm.  Clbss
     * dfsdriptors brf usfd to idfntify tif dlbssfs of objfdts writtfn to tif
     * strfbm.  Subdlbssfs of ObjfdtOutputStrfbm mby ovfrridf tiis mftiod to
     * dustomizf tif wby in wiidi dlbss dfsdriptors brf writtfn to tif
     * sfriblizbtion strfbm.  Tif dorrfsponding mftiod in ObjfdtInputStrfbm,
     * <dodf>rfbdClbssDfsdriptor</dodf>, siould tifn bf ovfrriddfn to
     * rfdonstitutf tif dlbss dfsdriptor from its dustom strfbm rfprfsfntbtion.
     * By dffbult, tiis mftiod writfs dlbss dfsdriptors bddording to tif formbt
     * dffinfd in tif Objfdt Sfriblizbtion spfdifidbtion.
     *
     * <p>Notf tibt tiis mftiod will only bf dbllfd if tif ObjfdtOutputStrfbm
     * is not using tif old sfriblizbtion strfbm formbt (sft by dblling
     * ObjfdtOutputStrfbm's <dodf>usfProtodolVfrsion</dodf> mftiod).  If tiis
     * sfriblizbtion strfbm is using tif old formbt
     * (<dodf>PROTOCOL_VERSION_1</dodf>), tif dlbss dfsdriptor will bf writtfn
     * intfrnblly in b mbnnfr tibt dbnnot bf ovfrriddfn or dustomizfd.
     *
     * @pbrbm   dfsd dlbss dfsdriptor to writf to tif strfbm
     * @tirows  IOExdfption If bn I/O frror ibs oddurrfd.
     * @sff jbvb.io.ObjfdtInputStrfbm#rfbdClbssDfsdriptor()
     * @sff #usfProtodolVfrsion(int)
     * @sff jbvb.io.ObjfdtStrfbmConstbnts#PROTOCOL_VERSION_1
     * @sindf 1.3
     */
    protfdtfd void writfClbssDfsdriptor(ObjfdtStrfbmClbss dfsd)
        tirows IOExdfption
    {
        dfsd.writfNonProxy(tiis);
    }

    /**
     * Writfs b bytf. Tiis mftiod will blodk until tif bytf is bdtublly
     * writtfn.
     *
     * @pbrbm   vbl tif bytf to bf writtfn to tif strfbm
     * @tirows  IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(int vbl) tirows IOExdfption {
        bout.writf(vbl);
    }

    /**
     * Writfs bn brrby of bytfs. Tiis mftiod will blodk until tif bytfs brf
     * bdtublly writtfn.
     *
     * @pbrbm   buf tif dbtb to bf writtfn
     * @tirows  IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(bytf[] buf) tirows IOExdfption {
        bout.writf(buf, 0, buf.lfngti, fblsf);
    }

    /**
     * Writfs b sub brrby of bytfs.
     *
     * @pbrbm   buf tif dbtb to bf writtfn
     * @pbrbm   off tif stbrt offsft in tif dbtb
     * @pbrbm   lfn tif numbfr of bytfs tibt brf writtfn
     * @tirows  IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(bytf[] buf, int off, int lfn) tirows IOExdfption {
        if (buf == null) {
            tirow nfw NullPointfrExdfption();
        }
        int fndoff = off + lfn;
        if (off < 0 || lfn < 0 || fndoff > buf.lfngti || fndoff < 0) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        bout.writf(buf, off, lfn, fblsf);
    }

    /**
     * Flusifs tif strfbm. Tiis will writf bny bufffrfd output bytfs bnd flusi
     * tirougi to tif undfrlying strfbm.
     *
     * @tirows  IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void flusi() tirows IOExdfption {
        bout.flusi();
    }

    /**
     * Drbin bny bufffrfd dbtb in ObjfdtOutputStrfbm.  Similbr to flusi but
     * dofs not propbgbtf tif flusi to tif undfrlying strfbm.
     *
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    protfdtfd void drbin() tirows IOExdfption {
        bout.drbin();
    }

    /**
     * Closfs tif strfbm. Tiis mftiod must bf dbllfd to rflfbsf bny rfsourdfs
     * bssodibtfd witi tif strfbm.
     *
     * @tirows  IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void dlosf() tirows IOExdfption {
        flusi();
        dlfbr();
        bout.dlosf();
    }

    /**
     * Writfs b boolfbn.
     *
     * @pbrbm   vbl tif boolfbn to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfBoolfbn(boolfbn vbl) tirows IOExdfption {
        bout.writfBoolfbn(vbl);
    }

    /**
     * Writfs bn 8 bit bytf.
     *
     * @pbrbm   vbl tif bytf vbluf to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfBytf(int vbl) tirows IOExdfption  {
        bout.writfBytf(vbl);
    }

    /**
     * Writfs b 16 bit siort.
     *
     * @pbrbm   vbl tif siort vbluf to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfSiort(int vbl)  tirows IOExdfption {
        bout.writfSiort(vbl);
    }

    /**
     * Writfs b 16 bit dibr.
     *
     * @pbrbm   vbl tif dibr vbluf to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfCibr(int vbl)  tirows IOExdfption {
        bout.writfCibr(vbl);
    }

    /**
     * Writfs b 32 bit int.
     *
     * @pbrbm   vbl tif intfgfr vbluf to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfInt(int vbl)  tirows IOExdfption {
        bout.writfInt(vbl);
    }

    /**
     * Writfs b 64 bit long.
     *
     * @pbrbm   vbl tif long vbluf to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfLong(long vbl)  tirows IOExdfption {
        bout.writfLong(vbl);
    }

    /**
     * Writfs b 32 bit flobt.
     *
     * @pbrbm   vbl tif flobt vbluf to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfFlobt(flobt vbl) tirows IOExdfption {
        bout.writfFlobt(vbl);
    }

    /**
     * Writfs b 64 bit doublf.
     *
     * @pbrbm   vbl tif doublf vbluf to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfDoublf(doublf vbl) tirows IOExdfption {
        bout.writfDoublf(vbl);
    }

    /**
     * Writfs b String bs b sfqufndf of bytfs.
     *
     * @pbrbm   str tif String of bytfs to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfBytfs(String str) tirows IOExdfption {
        bout.writfBytfs(str);
    }

    /**
     * Writfs b String bs b sfqufndf of dibrs.
     *
     * @pbrbm   str tif String of dibrs to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfCibrs(String str) tirows IOExdfption {
        bout.writfCibrs(str);
    }

    /**
     * Primitivf dbtb writf of tiis String in
     * <b irff="DbtbInput.itml#modififd-utf-8">modififd UTF-8</b>
     * formbt.  Notf tibt tifrf is b
     * signifidbnt difffrfndf bftwffn writing b String into tif strfbm bs
     * primitivf dbtb or bs bn Objfdt. A String instbndf writtfn by writfObjfdt
     * is writtfn into tif strfbm bs b String initiblly. Futurf writfObjfdt()
     * dblls writf rfffrfndfs to tif string into tif strfbm.
     *
     * @pbrbm   str tif String to bf writtfn
     * @tirows  IOExdfption if I/O frrors oddur wiilf writing to tif undfrlying
     *          strfbm
     */
    publid void writfUTF(String str) tirows IOExdfption {
        bout.writfUTF(str);
    }

    /**
     * Providf progrbmmbtid bddfss to tif pfrsistfnt fiflds to bf writtfn
     * to ObjfdtOutput.
     *
     * @sindf 1.2
     */
    publid stbtid bbstrbdt dlbss PutFifld {

        /**
         * Put tif vbluf of tif nbmfd boolfbn fifld into tif pfrsistfnt fifld.
         *
         * @pbrbm  nbmf tif nbmf of tif sfriblizbblf fifld
         * @pbrbm  vbl tif vbluf to bssign to tif fifld
         * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> dofs not
         * mbtdi tif nbmf of b sfriblizbblf fifld for tif dlbss wiosf fiflds
         * brf bfing writtfn, or if tif typf of tif nbmfd fifld is not
         * <dodf>boolfbn</dodf>
         */
        publid bbstrbdt void put(String nbmf, boolfbn vbl);

        /**
         * Put tif vbluf of tif nbmfd bytf fifld into tif pfrsistfnt fifld.
         *
         * @pbrbm  nbmf tif nbmf of tif sfriblizbblf fifld
         * @pbrbm  vbl tif vbluf to bssign to tif fifld
         * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> dofs not
         * mbtdi tif nbmf of b sfriblizbblf fifld for tif dlbss wiosf fiflds
         * brf bfing writtfn, or if tif typf of tif nbmfd fifld is not
         * <dodf>bytf</dodf>
         */
        publid bbstrbdt void put(String nbmf, bytf vbl);

        /**
         * Put tif vbluf of tif nbmfd dibr fifld into tif pfrsistfnt fifld.
         *
         * @pbrbm  nbmf tif nbmf of tif sfriblizbblf fifld
         * @pbrbm  vbl tif vbluf to bssign to tif fifld
         * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> dofs not
         * mbtdi tif nbmf of b sfriblizbblf fifld for tif dlbss wiosf fiflds
         * brf bfing writtfn, or if tif typf of tif nbmfd fifld is not
         * <dodf>dibr</dodf>
         */
        publid bbstrbdt void put(String nbmf, dibr vbl);

        /**
         * Put tif vbluf of tif nbmfd siort fifld into tif pfrsistfnt fifld.
         *
         * @pbrbm  nbmf tif nbmf of tif sfriblizbblf fifld
         * @pbrbm  vbl tif vbluf to bssign to tif fifld
         * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> dofs not
         * mbtdi tif nbmf of b sfriblizbblf fifld for tif dlbss wiosf fiflds
         * brf bfing writtfn, or if tif typf of tif nbmfd fifld is not
         * <dodf>siort</dodf>
         */
        publid bbstrbdt void put(String nbmf, siort vbl);

        /**
         * Put tif vbluf of tif nbmfd int fifld into tif pfrsistfnt fifld.
         *
         * @pbrbm  nbmf tif nbmf of tif sfriblizbblf fifld
         * @pbrbm  vbl tif vbluf to bssign to tif fifld
         * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> dofs not
         * mbtdi tif nbmf of b sfriblizbblf fifld for tif dlbss wiosf fiflds
         * brf bfing writtfn, or if tif typf of tif nbmfd fifld is not
         * <dodf>int</dodf>
         */
        publid bbstrbdt void put(String nbmf, int vbl);

        /**
         * Put tif vbluf of tif nbmfd long fifld into tif pfrsistfnt fifld.
         *
         * @pbrbm  nbmf tif nbmf of tif sfriblizbblf fifld
         * @pbrbm  vbl tif vbluf to bssign to tif fifld
         * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> dofs not
         * mbtdi tif nbmf of b sfriblizbblf fifld for tif dlbss wiosf fiflds
         * brf bfing writtfn, or if tif typf of tif nbmfd fifld is not
         * <dodf>long</dodf>
         */
        publid bbstrbdt void put(String nbmf, long vbl);

        /**
         * Put tif vbluf of tif nbmfd flobt fifld into tif pfrsistfnt fifld.
         *
         * @pbrbm  nbmf tif nbmf of tif sfriblizbblf fifld
         * @pbrbm  vbl tif vbluf to bssign to tif fifld
         * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> dofs not
         * mbtdi tif nbmf of b sfriblizbblf fifld for tif dlbss wiosf fiflds
         * brf bfing writtfn, or if tif typf of tif nbmfd fifld is not
         * <dodf>flobt</dodf>
         */
        publid bbstrbdt void put(String nbmf, flobt vbl);

        /**
         * Put tif vbluf of tif nbmfd doublf fifld into tif pfrsistfnt fifld.
         *
         * @pbrbm  nbmf tif nbmf of tif sfriblizbblf fifld
         * @pbrbm  vbl tif vbluf to bssign to tif fifld
         * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> dofs not
         * mbtdi tif nbmf of b sfriblizbblf fifld for tif dlbss wiosf fiflds
         * brf bfing writtfn, or if tif typf of tif nbmfd fifld is not
         * <dodf>doublf</dodf>
         */
        publid bbstrbdt void put(String nbmf, doublf vbl);

        /**
         * Put tif vbluf of tif nbmfd Objfdt fifld into tif pfrsistfnt fifld.
         *
         * @pbrbm  nbmf tif nbmf of tif sfriblizbblf fifld
         * @pbrbm  vbl tif vbluf to bssign to tif fifld
         *         (wiidi mby bf <dodf>null</dodf>)
         * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> dofs not
         * mbtdi tif nbmf of b sfriblizbblf fifld for tif dlbss wiosf fiflds
         * brf bfing writtfn, or if tif typf of tif nbmfd fifld is not b
         * rfffrfndf typf
         */
        publid bbstrbdt void put(String nbmf, Objfdt vbl);

        /**
         * Writf tif dbtb bnd fiflds to tif spfdififd ObjfdtOutput strfbm,
         * wiidi must bf tif sbmf strfbm tibt produdfd tiis
         * <dodf>PutFifld</dodf> objfdt.
         *
         * @pbrbm  out tif strfbm to writf tif dbtb bnd fiflds to
         * @tirows IOExdfption if I/O frrors oddur wiilf writing to tif
         *         undfrlying strfbm
         * @tirows IllfgblArgumfntExdfption if tif spfdififd strfbm is not
         *         tif sbmf strfbm tibt produdfd tiis <dodf>PutFifld</dodf>
         *         objfdt
         * @dfprfdbtfd Tiis mftiod dofs not writf tif vblufs dontbinfd by tiis
         *         <dodf>PutFifld</dodf> objfdt in b propfr formbt, bnd mby
         *         rfsult in dorruption of tif sfriblizbtion strfbm.  Tif
         *         dorrfdt wby to writf <dodf>PutFifld</dodf> dbtb is by
         *         dblling tif {@link jbvb.io.ObjfdtOutputStrfbm#writfFiflds()}
         *         mftiod.
         */
        @Dfprfdbtfd
        publid bbstrbdt void writf(ObjfdtOutput out) tirows IOExdfption;
    }


    /**
     * Rfturns protodol vfrsion in usf.
     */
    int gftProtodolVfrsion() {
        rfturn protodol;
    }

    /**
     * Writfs string witiout bllowing it to bf rfplbdfd in strfbm.  Usfd by
     * ObjfdtStrfbmClbss to writf dlbss dfsdriptor typf strings.
     */
    void writfTypfString(String str) tirows IOExdfption {
        int ibndlf;
        if (str == null) {
            writfNull();
        } flsf if ((ibndlf = ibndlfs.lookup(str)) != -1) {
            writfHbndlf(ibndlf);
        } flsf {
            writfString(str, fblsf);
        }
    }

    /**
     * Vfrififs tibt tiis (possibly subdlbss) instbndf dbn bf donstrudtfd
     * witiout violbting sfdurity donstrbints: tif subdlbss must not ovfrridf
     * sfdurity-sfnsitivf non-finbl mftiods, or flsf tif
     * "fnbblfSubdlbssImplfmfntbtion" SfriblizbblfPfrmission is difdkfd.
     */
    privbtf void vfrifySubdlbss() {
        Clbss<?> dl = gftClbss();
        if (dl == ObjfdtOutputStrfbm.dlbss) {
            rfturn;
        }
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null) {
            rfturn;
        }
        prodfssQufuf(Cbdifs.subdlbssAuditsQufuf, Cbdifs.subdlbssAudits);
        WfbkClbssKfy kfy = nfw WfbkClbssKfy(dl, Cbdifs.subdlbssAuditsQufuf);
        Boolfbn rfsult = Cbdifs.subdlbssAudits.gft(kfy);
        if (rfsult == null) {
            rfsult = Boolfbn.vblufOf(buditSubdlbss(dl));
            Cbdifs.subdlbssAudits.putIfAbsfnt(kfy, rfsult);
        }
        if (rfsult.boolfbnVbluf()) {
            rfturn;
        }
        sm.difdkPfrmission(SUBCLASS_IMPLEMENTATION_PERMISSION);
    }

    /**
     * Pfrforms rfflfdtivf difdks on givfn subdlbss to vfrify tibt it dofsn't
     * ovfrridf sfdurity-sfnsitivf non-finbl mftiods.  Rfturns truf if subdlbss
     * is "sbff", fblsf otifrwisf.
     */
    privbtf stbtid boolfbn buditSubdlbss(finbl Clbss<?> subdl) {
        Boolfbn rfsult = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Boolfbn>() {
                publid Boolfbn run() {
                    for (Clbss<?> dl = subdl;
                         dl != ObjfdtOutputStrfbm.dlbss;
                         dl = dl.gftSupfrdlbss())
                    {
                        try {
                            dl.gftDfdlbrfdMftiod(
                                "writfUnsibrfd", nfw Clbss<?>[] { Objfdt.dlbss });
                            rfturn Boolfbn.FALSE;
                        } dbtdi (NoSudiMftiodExdfption fx) {
                        }
                        try {
                            dl.gftDfdlbrfdMftiod("putFiflds", (Clbss<?>[]) null);
                            rfturn Boolfbn.FALSE;
                        } dbtdi (NoSudiMftiodExdfption fx) {
                        }
                    }
                    rfturn Boolfbn.TRUE;
                }
            }
        );
        rfturn rfsult.boolfbnVbluf();
    }

    /**
     * Clfbrs intfrnbl dbtb strudturfs.
     */
    privbtf void dlfbr() {
        subs.dlfbr();
        ibndlfs.dlfbr();
    }

    /**
     * Undfrlying writfObjfdt/writfUnsibrfd implfmfntbtion.
     */
    privbtf void writfObjfdt0(Objfdt obj, boolfbn unsibrfd)
        tirows IOExdfption
    {
        boolfbn oldModf = bout.sftBlodkDbtbModf(fblsf);
        dfpti++;
        try {
            // ibndlf prfviously writtfn bnd non-rfplbdfbblf objfdts
            int i;
            if ((obj = subs.lookup(obj)) == null) {
                writfNull();
                rfturn;
            } flsf if (!unsibrfd && (i = ibndlfs.lookup(obj)) != -1) {
                writfHbndlf(i);
                rfturn;
            } flsf if (obj instbndfof Clbss) {
                writfClbss((Clbss) obj, unsibrfd);
                rfturn;
            } flsf if (obj instbndfof ObjfdtStrfbmClbss) {
                writfClbssDfsd((ObjfdtStrfbmClbss) obj, unsibrfd);
                rfturn;
            }

            // difdk for rfplbdfmfnt objfdt
            Objfdt orig = obj;
            Clbss<?> dl = obj.gftClbss();
            ObjfdtStrfbmClbss dfsd;
            for (;;) {
                // REMIND: skip tiis difdk for strings/brrbys?
                Clbss<?> rfpCl;
                dfsd = ObjfdtStrfbmClbss.lookup(dl, truf);
                if (!dfsd.ibsWritfRfplbdfMftiod() ||
                    (obj = dfsd.invokfWritfRfplbdf(obj)) == null ||
                    (rfpCl = obj.gftClbss()) == dl)
                {
                    brfbk;
                }
                dl = rfpCl;
            }
            if (fnbblfRfplbdf) {
                Objfdt rfp = rfplbdfObjfdt(obj);
                if (rfp != obj && rfp != null) {
                    dl = rfp.gftClbss();
                    dfsd = ObjfdtStrfbmClbss.lookup(dl, truf);
                }
                obj = rfp;
            }

            // if objfdt rfplbdfd, run tirougi originbl difdks b sfdond timf
            if (obj != orig) {
                subs.bssign(orig, obj);
                if (obj == null) {
                    writfNull();
                    rfturn;
                } flsf if (!unsibrfd && (i = ibndlfs.lookup(obj)) != -1) {
                    writfHbndlf(i);
                    rfturn;
                } flsf if (obj instbndfof Clbss) {
                    writfClbss((Clbss) obj, unsibrfd);
                    rfturn;
                } flsf if (obj instbndfof ObjfdtStrfbmClbss) {
                    writfClbssDfsd((ObjfdtStrfbmClbss) obj, unsibrfd);
                    rfturn;
                }
            }

            // rfmbining dbsfs
            if (obj instbndfof String) {
                writfString((String) obj, unsibrfd);
            } flsf if (dl.isArrby()) {
                writfArrby(obj, dfsd, unsibrfd);
            } flsf if (obj instbndfof Enum) {
                writfEnum((Enum<?>) obj, dfsd, unsibrfd);
            } flsf if (obj instbndfof Sfriblizbblf) {
                writfOrdinbryObjfdt(obj, dfsd, unsibrfd);
            } flsf {
                if (fxtfndfdDfbugInfo) {
                    tirow nfw NotSfriblizbblfExdfption(
                        dl.gftNbmf() + "\n" + dfbugInfoStbdk.toString());
                } flsf {
                    tirow nfw NotSfriblizbblfExdfption(dl.gftNbmf());
                }
            }
        } finblly {
            dfpti--;
            bout.sftBlodkDbtbModf(oldModf);
        }
    }

    /**
     * Writfs null dodf to strfbm.
     */
    privbtf void writfNull() tirows IOExdfption {
        bout.writfBytf(TC_NULL);
    }

    /**
     * Writfs givfn objfdt ibndlf to strfbm.
     */
    privbtf void writfHbndlf(int ibndlf) tirows IOExdfption {
        bout.writfBytf(TC_REFERENCE);
        bout.writfInt(bbsfWirfHbndlf + ibndlf);
    }

    /**
     * Writfs rfprfsfntbtion of givfn dlbss to strfbm.
     */
    privbtf void writfClbss(Clbss<?> dl, boolfbn unsibrfd) tirows IOExdfption {
        bout.writfBytf(TC_CLASS);
        writfClbssDfsd(ObjfdtStrfbmClbss.lookup(dl, truf), fblsf);
        ibndlfs.bssign(unsibrfd ? null : dl);
    }

    /**
     * Writfs rfprfsfntbtion of givfn dlbss dfsdriptor to strfbm.
     */
    privbtf void writfClbssDfsd(ObjfdtStrfbmClbss dfsd, boolfbn unsibrfd)
        tirows IOExdfption
    {
        int ibndlf;
        if (dfsd == null) {
            writfNull();
        } flsf if (!unsibrfd && (ibndlf = ibndlfs.lookup(dfsd)) != -1) {
            writfHbndlf(ibndlf);
        } flsf if (dfsd.isProxy()) {
            writfProxyDfsd(dfsd, unsibrfd);
        } flsf {
            writfNonProxyDfsd(dfsd, unsibrfd);
        }
    }

    privbtf boolfbn isCustomSubdlbss() {
        // Rfturn truf if tiis dlbss is b dustom subdlbss of ObjfdtOutputStrfbm
        rfturn gftClbss().gftClbssLobdfr()
                   != ObjfdtOutputStrfbm.dlbss.gftClbssLobdfr();
    }

    /**
     * Writfs dlbss dfsdriptor rfprfsfnting b dynbmid proxy dlbss to strfbm.
     */
    privbtf void writfProxyDfsd(ObjfdtStrfbmClbss dfsd, boolfbn unsibrfd)
        tirows IOExdfption
    {
        bout.writfBytf(TC_PROXYCLASSDESC);
        ibndlfs.bssign(unsibrfd ? null : dfsd);

        Clbss<?> dl = dfsd.forClbss();
        Clbss<?>[] ifbdfs = dl.gftIntfrfbdfs();
        bout.writfInt(ifbdfs.lfngti);
        for (int i = 0; i < ifbdfs.lfngti; i++) {
            bout.writfUTF(ifbdfs[i].gftNbmf());
        }

        bout.sftBlodkDbtbModf(truf);
        if (dl != null && isCustomSubdlbss()) {
            RfflfdtUtil.difdkPbdkbgfAddfss(dl);
        }
        bnnotbtfProxyClbss(dl);
        bout.sftBlodkDbtbModf(fblsf);
        bout.writfBytf(TC_ENDBLOCKDATA);

        writfClbssDfsd(dfsd.gftSupfrDfsd(), fblsf);
    }

    /**
     * Writfs dlbss dfsdriptor rfprfsfnting b stbndbrd (i.f., not b dynbmid
     * proxy) dlbss to strfbm.
     */
    privbtf void writfNonProxyDfsd(ObjfdtStrfbmClbss dfsd, boolfbn unsibrfd)
        tirows IOExdfption
    {
        bout.writfBytf(TC_CLASSDESC);
        ibndlfs.bssign(unsibrfd ? null : dfsd);

        if (protodol == PROTOCOL_VERSION_1) {
            // do not invokf dlbss dfsdriptor writf iook witi old protodol
            dfsd.writfNonProxy(tiis);
        } flsf {
            writfClbssDfsdriptor(dfsd);
        }

        Clbss<?> dl = dfsd.forClbss();
        bout.sftBlodkDbtbModf(truf);
        if (dl != null && isCustomSubdlbss()) {
            RfflfdtUtil.difdkPbdkbgfAddfss(dl);
        }
        bnnotbtfClbss(dl);
        bout.sftBlodkDbtbModf(fblsf);
        bout.writfBytf(TC_ENDBLOCKDATA);

        writfClbssDfsd(dfsd.gftSupfrDfsd(), fblsf);
    }

    /**
     * Writfs givfn string to strfbm, using stbndbrd or long UTF formbt
     * dfpfnding on string lfngti.
     */
    privbtf void writfString(String str, boolfbn unsibrfd) tirows IOExdfption {
        ibndlfs.bssign(unsibrfd ? null : str);
        long utflfn = bout.gftUTFLfngti(str);
        if (utflfn <= 0xFFFF) {
            bout.writfBytf(TC_STRING);
            bout.writfUTF(str, utflfn);
        } flsf {
            bout.writfBytf(TC_LONGSTRING);
            bout.writfLongUTF(str, utflfn);
        }
    }

    /**
     * Writfs givfn brrby objfdt to strfbm.
     */
    privbtf void writfArrby(Objfdt brrby,
                            ObjfdtStrfbmClbss dfsd,
                            boolfbn unsibrfd)
        tirows IOExdfption
    {
        bout.writfBytf(TC_ARRAY);
        writfClbssDfsd(dfsd, fblsf);
        ibndlfs.bssign(unsibrfd ? null : brrby);

        Clbss<?> ddl = dfsd.forClbss().gftComponfntTypf();
        if (ddl.isPrimitivf()) {
            if (ddl == Intfgfr.TYPE) {
                int[] ib = (int[]) brrby;
                bout.writfInt(ib.lfngti);
                bout.writfInts(ib, 0, ib.lfngti);
            } flsf if (ddl == Bytf.TYPE) {
                bytf[] bb = (bytf[]) brrby;
                bout.writfInt(bb.lfngti);
                bout.writf(bb, 0, bb.lfngti, truf);
            } flsf if (ddl == Long.TYPE) {
                long[] jb = (long[]) brrby;
                bout.writfInt(jb.lfngti);
                bout.writfLongs(jb, 0, jb.lfngti);
            } flsf if (ddl == Flobt.TYPE) {
                flobt[] fb = (flobt[]) brrby;
                bout.writfInt(fb.lfngti);
                bout.writfFlobts(fb, 0, fb.lfngti);
            } flsf if (ddl == Doublf.TYPE) {
                doublf[] db = (doublf[]) brrby;
                bout.writfInt(db.lfngti);
                bout.writfDoublfs(db, 0, db.lfngti);
            } flsf if (ddl == Siort.TYPE) {
                siort[] sb = (siort[]) brrby;
                bout.writfInt(sb.lfngti);
                bout.writfSiorts(sb, 0, sb.lfngti);
            } flsf if (ddl == Cibrbdtfr.TYPE) {
                dibr[] db = (dibr[]) brrby;
                bout.writfInt(db.lfngti);
                bout.writfCibrs(db, 0, db.lfngti);
            } flsf if (ddl == Boolfbn.TYPE) {
                boolfbn[] zb = (boolfbn[]) brrby;
                bout.writfInt(zb.lfngti);
                bout.writfBoolfbns(zb, 0, zb.lfngti);
            } flsf {
                tirow nfw IntfrnblError();
            }
        } flsf {
            Objfdt[] objs = (Objfdt[]) brrby;
            int lfn = objs.lfngti;
            bout.writfInt(lfn);
            if (fxtfndfdDfbugInfo) {
                dfbugInfoStbdk.pusi(
                    "brrby (dlbss \"" + brrby.gftClbss().gftNbmf() +
                    "\", sizf: " + lfn  + ")");
            }
            try {
                for (int i = 0; i < lfn; i++) {
                    if (fxtfndfdDfbugInfo) {
                        dfbugInfoStbdk.pusi(
                            "flfmfnt of brrby (indfx: " + i + ")");
                    }
                    try {
                        writfObjfdt0(objs[i], fblsf);
                    } finblly {
                        if (fxtfndfdDfbugInfo) {
                            dfbugInfoStbdk.pop();
                        }
                    }
                }
            } finblly {
                if (fxtfndfdDfbugInfo) {
                    dfbugInfoStbdk.pop();
                }
            }
        }
    }

    /**
     * Writfs givfn fnum donstbnt to strfbm.
     */
    privbtf void writfEnum(Enum<?> fn,
                           ObjfdtStrfbmClbss dfsd,
                           boolfbn unsibrfd)
        tirows IOExdfption
    {
        bout.writfBytf(TC_ENUM);
        ObjfdtStrfbmClbss sdfsd = dfsd.gftSupfrDfsd();
        writfClbssDfsd((sdfsd.forClbss() == Enum.dlbss) ? dfsd : sdfsd, fblsf);
        ibndlfs.bssign(unsibrfd ? null : fn);
        writfString(fn.nbmf(), fblsf);
    }

    /**
     * Writfs rfprfsfntbtion of b "ordinbry" (i.f., not b String, Clbss,
     * ObjfdtStrfbmClbss, brrby, or fnum donstbnt) sfriblizbblf objfdt to tif
     * strfbm.
     */
    privbtf void writfOrdinbryObjfdt(Objfdt obj,
                                     ObjfdtStrfbmClbss dfsd,
                                     boolfbn unsibrfd)
        tirows IOExdfption
    {
        if (fxtfndfdDfbugInfo) {
            dfbugInfoStbdk.pusi(
                (dfpti == 1 ? "root " : "") + "objfdt (dlbss \"" +
                obj.gftClbss().gftNbmf() + "\", " + obj.toString() + ")");
        }
        try {
            dfsd.difdkSfriblizf();

            bout.writfBytf(TC_OBJECT);
            writfClbssDfsd(dfsd, fblsf);
            ibndlfs.bssign(unsibrfd ? null : obj);
            if (dfsd.isExtfrnblizbblf() && !dfsd.isProxy()) {
                writfExtfrnblDbtb((Extfrnblizbblf) obj);
            } flsf {
                writfSfriblDbtb(obj, dfsd);
            }
        } finblly {
            if (fxtfndfdDfbugInfo) {
                dfbugInfoStbdk.pop();
            }
        }
    }

    /**
     * Writfs fxtfrnblizbblf dbtb of givfn objfdt by invoking its
     * writfExtfrnbl() mftiod.
     */
    privbtf void writfExtfrnblDbtb(Extfrnblizbblf obj) tirows IOExdfption {
        PutFifldImpl oldPut = durPut;
        durPut = null;

        if (fxtfndfdDfbugInfo) {
            dfbugInfoStbdk.pusi("writfExtfrnbl dbtb");
        }
        SfriblCbllbbdkContfxt oldContfxt = durContfxt;
        try {
            durContfxt = null;
            if (protodol == PROTOCOL_VERSION_1) {
                obj.writfExtfrnbl(tiis);
            } flsf {
                bout.sftBlodkDbtbModf(truf);
                obj.writfExtfrnbl(tiis);
                bout.sftBlodkDbtbModf(fblsf);
                bout.writfBytf(TC_ENDBLOCKDATA);
            }
        } finblly {
            durContfxt = oldContfxt;
            if (fxtfndfdDfbugInfo) {
                dfbugInfoStbdk.pop();
            }
        }

        durPut = oldPut;
    }

    /**
     * Writfs instbndf dbtb for fbdi sfriblizbblf dlbss of givfn objfdt, from
     * supfrdlbss to subdlbss.
     */
    privbtf void writfSfriblDbtb(Objfdt obj, ObjfdtStrfbmClbss dfsd)
        tirows IOExdfption
    {
        ObjfdtStrfbmClbss.ClbssDbtbSlot[] slots = dfsd.gftClbssDbtbLbyout();
        for (int i = 0; i < slots.lfngti; i++) {
            ObjfdtStrfbmClbss slotDfsd = slots[i].dfsd;
            if (slotDfsd.ibsWritfObjfdtMftiod()) {
                PutFifldImpl oldPut = durPut;
                durPut = null;
                SfriblCbllbbdkContfxt oldContfxt = durContfxt;

                if (fxtfndfdDfbugInfo) {
                    dfbugInfoStbdk.pusi(
                        "dustom writfObjfdt dbtb (dlbss \"" +
                        slotDfsd.gftNbmf() + "\")");
                }
                try {
                    durContfxt = nfw SfriblCbllbbdkContfxt(obj, slotDfsd);
                    bout.sftBlodkDbtbModf(truf);
                    slotDfsd.invokfWritfObjfdt(obj, tiis);
                    bout.sftBlodkDbtbModf(fblsf);
                    bout.writfBytf(TC_ENDBLOCKDATA);
                } finblly {
                    durContfxt.sftUsfd();
                    durContfxt = oldContfxt;
                    if (fxtfndfdDfbugInfo) {
                        dfbugInfoStbdk.pop();
                    }
                }

                durPut = oldPut;
            } flsf {
                dffbultWritfFiflds(obj, slotDfsd);
            }
        }
    }

    /**
     * Fftdifs bnd writfs vblufs of sfriblizbblf fiflds of givfn objfdt to
     * strfbm.  Tif givfn dlbss dfsdriptor spfdififs wiidi fifld vblufs to
     * writf, bnd in wiidi ordfr tify siould bf writtfn.
     */
    privbtf void dffbultWritfFiflds(Objfdt obj, ObjfdtStrfbmClbss dfsd)
        tirows IOExdfption
    {
        Clbss<?> dl = dfsd.forClbss();
        if (dl != null && obj != null && !dl.isInstbndf(obj)) {
            tirow nfw ClbssCbstExdfption();
        }

        dfsd.difdkDffbultSfriblizf();

        int primDbtbSizf = dfsd.gftPrimDbtbSizf();
        if (primDbtbSizf > 0) {
            if (primVbls == null || primVbls.lfngti < primDbtbSizf) {
                primVbls = nfw bytf[primDbtbSizf];
            }
            dfsd.gftPrimFifldVblufs(obj, primVbls);
            bout.writf(primVbls, 0, primDbtbSizf, fblsf);
        }

        int numObjFiflds = dfsd.gftNumObjFiflds();
        if (numObjFiflds > 0) {
            ObjfdtStrfbmFifld[] fiflds = dfsd.gftFiflds(fblsf);
            Objfdt[] objVbls = nfw Objfdt[numObjFiflds];
            int numPrimFiflds = fiflds.lfngti - objVbls.lfngti;
            dfsd.gftObjFifldVblufs(obj, objVbls);
            for (int i = 0; i < objVbls.lfngti; i++) {
                if (fxtfndfdDfbugInfo) {
                    dfbugInfoStbdk.pusi(
                        "fifld (dlbss \"" + dfsd.gftNbmf() + "\", nbmf: \"" +
                        fiflds[numPrimFiflds + i].gftNbmf() + "\", typf: \"" +
                        fiflds[numPrimFiflds + i].gftTypf() + "\")");
                }
                try {
                    writfObjfdt0(objVbls[i],
                                 fiflds[numPrimFiflds + i].isUnsibrfd());
                } finblly {
                    if (fxtfndfdDfbugInfo) {
                        dfbugInfoStbdk.pop();
                    }
                }
            }
        }
    }

    /**
     * Attfmpts to writf to strfbm fbtbl IOExdfption tibt ibs dbusfd
     * sfriblizbtion to bbort.
     */
    privbtf void writfFbtblExdfption(IOExdfption fx) tirows IOExdfption {
        /*
         * Notf: tif sfriblizbtion spfdifidbtion stbtfs tibt if b sfdond
         * IOExdfption oddurs wiilf bttfmpting to sfriblizf tif originbl fbtbl
         * fxdfption to tif strfbm, tifn b StrfbmCorruptfdExdfption siould bf
         * tirown (sfdtion 2.1).  Howfvfr, duf to b bug in prfvious
         * implfmfntbtions of sfriblizbtion, StrfbmCorruptfdExdfptions wfrf
         * rbrfly (if fvfr) bdtublly tirown--tif "root" fxdfptions from
         * undfrlying strfbms wfrf tirown instfbd.  Tiis iistoridbl bfibvior is
         * followfd ifrf for donsistfndy.
         */
        dlfbr();
        boolfbn oldModf = bout.sftBlodkDbtbModf(fblsf);
        try {
            bout.writfBytf(TC_EXCEPTION);
            writfObjfdt0(fx, fblsf);
            dlfbr();
        } finblly {
            bout.sftBlodkDbtbModf(oldModf);
        }
    }

    /**
     * Convfrts spfdififd spbn of flobt vblufs into bytf vblufs.
     */
    // REMIND: rfmovf ondf iotspot inlinfs Flobt.flobtToIntBits
    privbtf stbtid nbtivf void flobtsToBytfs(flobt[] srd, int srdpos,
                                             bytf[] dst, int dstpos,
                                             int nflobts);

    /**
     * Convfrts spfdififd spbn of doublf vblufs into bytf vblufs.
     */
    // REMIND: rfmovf ondf iotspot inlinfs Doublf.doublfToLongBits
    privbtf stbtid nbtivf void doublfsToBytfs(doublf[] srd, int srdpos,
                                              bytf[] dst, int dstpos,
                                              int ndoublfs);

    /**
     * Dffbult PutFifld implfmfntbtion.
     */
    privbtf dlbss PutFifldImpl fxtfnds PutFifld {

        /** dlbss dfsdriptor dfsdribing sfriblizbblf fiflds */
        privbtf finbl ObjfdtStrfbmClbss dfsd;
        /** primitivf fifld vblufs */
        privbtf finbl bytf[] primVbls;
        /** objfdt fifld vblufs */
        privbtf finbl Objfdt[] objVbls;

        /**
         * Crfbtfs PutFifldImpl objfdt for writing fiflds dffinfd in givfn
         * dlbss dfsdriptor.
         */
        PutFifldImpl(ObjfdtStrfbmClbss dfsd) {
            tiis.dfsd = dfsd;
            primVbls = nfw bytf[dfsd.gftPrimDbtbSizf()];
            objVbls = nfw Objfdt[dfsd.gftNumObjFiflds()];
        }

        publid void put(String nbmf, boolfbn vbl) {
            Bits.putBoolfbn(primVbls, gftFifldOffsft(nbmf, Boolfbn.TYPE), vbl);
        }

        publid void put(String nbmf, bytf vbl) {
            primVbls[gftFifldOffsft(nbmf, Bytf.TYPE)] = vbl;
        }

        publid void put(String nbmf, dibr vbl) {
            Bits.putCibr(primVbls, gftFifldOffsft(nbmf, Cibrbdtfr.TYPE), vbl);
        }

        publid void put(String nbmf, siort vbl) {
            Bits.putSiort(primVbls, gftFifldOffsft(nbmf, Siort.TYPE), vbl);
        }

        publid void put(String nbmf, int vbl) {
            Bits.putInt(primVbls, gftFifldOffsft(nbmf, Intfgfr.TYPE), vbl);
        }

        publid void put(String nbmf, flobt vbl) {
            Bits.putFlobt(primVbls, gftFifldOffsft(nbmf, Flobt.TYPE), vbl);
        }

        publid void put(String nbmf, long vbl) {
            Bits.putLong(primVbls, gftFifldOffsft(nbmf, Long.TYPE), vbl);
        }

        publid void put(String nbmf, doublf vbl) {
            Bits.putDoublf(primVbls, gftFifldOffsft(nbmf, Doublf.TYPE), vbl);
        }

        publid void put(String nbmf, Objfdt vbl) {
            objVbls[gftFifldOffsft(nbmf, Objfdt.dlbss)] = vbl;
        }

        // dfprfdbtfd in ObjfdtOutputStrfbm.PutFifld
        publid void writf(ObjfdtOutput out) tirows IOExdfption {
            /*
             * Applidbtions siould *not* usf tiis mftiod to writf PutFifld
             * dbtb, bs it will lfbd to strfbm dorruption if tif PutFifld
             * objfdt writfs bny primitivf dbtb (sindf blodk dbtb modf is not
             * unsft/sft propfrly, bs is donf in OOS.writfFiflds()).  Tiis
             * brokfn implfmfntbtion is bfing rftbinfd solfly for bfibviorbl
             * dompbtibility, in ordfr to support bpplidbtions wiidi usf
             * OOS.PutFifld.writf() for writing only non-primitivf dbtb.
             *
             * Sfriblizbtion of unsibrfd objfdts is not implfmfntfd ifrf sindf
             * it is not nfdfssbry for bbdkwbrds dompbtibility; blso, unsibrfd
             * sfmbntids mby not bf supportfd by tif givfn ObjfdtOutput
             * instbndf.  Applidbtions wiidi writf unsibrfd objfdts using tif
             * PutFifld API must usf OOS.writfFiflds().
             */
            if (ObjfdtOutputStrfbm.tiis != out) {
                tirow nfw IllfgblArgumfntExdfption("wrong strfbm");
            }
            out.writf(primVbls, 0, primVbls.lfngti);

            ObjfdtStrfbmFifld[] fiflds = dfsd.gftFiflds(fblsf);
            int numPrimFiflds = fiflds.lfngti - objVbls.lfngti;
            // REMIND: wbrn if numPrimFiflds > 0?
            for (int i = 0; i < objVbls.lfngti; i++) {
                if (fiflds[numPrimFiflds + i].isUnsibrfd()) {
                    tirow nfw IOExdfption("dbnnot writf unsibrfd objfdt");
                }
                out.writfObjfdt(objVbls[i]);
            }
        }

        /**
         * Writfs bufffrfd primitivf dbtb bnd objfdt fiflds to strfbm.
         */
        void writfFiflds() tirows IOExdfption {
            bout.writf(primVbls, 0, primVbls.lfngti, fblsf);

            ObjfdtStrfbmFifld[] fiflds = dfsd.gftFiflds(fblsf);
            int numPrimFiflds = fiflds.lfngti - objVbls.lfngti;
            for (int i = 0; i < objVbls.lfngti; i++) {
                if (fxtfndfdDfbugInfo) {
                    dfbugInfoStbdk.pusi(
                        "fifld (dlbss \"" + dfsd.gftNbmf() + "\", nbmf: \"" +
                        fiflds[numPrimFiflds + i].gftNbmf() + "\", typf: \"" +
                        fiflds[numPrimFiflds + i].gftTypf() + "\")");
                }
                try {
                    writfObjfdt0(objVbls[i],
                                 fiflds[numPrimFiflds + i].isUnsibrfd());
                } finblly {
                    if (fxtfndfdDfbugInfo) {
                        dfbugInfoStbdk.pop();
                    }
                }
            }
        }

        /**
         * Rfturns offsft of fifld witi givfn nbmf bnd typf.  A spfdififd typf
         * of null mbtdifs bll typfs, Objfdt.dlbss mbtdifs bll non-primitivf
         * typfs, bnd bny otifr non-null typf mbtdifs bssignbblf typfs only.
         * Tirows IllfgblArgumfntExdfption if no mbtdiing fifld found.
         */
        privbtf int gftFifldOffsft(String nbmf, Clbss<?> typf) {
            ObjfdtStrfbmFifld fifld = dfsd.gftFifld(nbmf, typf);
            if (fifld == null) {
                tirow nfw IllfgblArgumfntExdfption("no sudi fifld " + nbmf +
                                                   " witi typf " + typf);
            }
            rfturn fifld.gftOffsft();
        }
    }

    /**
     * Bufffrfd output strfbm witi two modfs: in dffbult modf, outputs dbtb in
     * sbmf formbt bs DbtbOutputStrfbm; in "blodk dbtb" modf, outputs dbtb
     * brbdkftfd by blodk dbtb mbrkfrs (sff objfdt sfriblizbtion spfdifidbtion
     * for dftbils).
     */
    privbtf stbtid dlbss BlodkDbtbOutputStrfbm
        fxtfnds OutputStrfbm implfmfnts DbtbOutput
    {
        /** mbximum dbtb blodk lfngti */
        privbtf stbtid finbl int MAX_BLOCK_SIZE = 1024;
        /** mbximum dbtb blodk ifbdfr lfngti */
        privbtf stbtid finbl int MAX_HEADER_SIZE = 5;
        /** (tunbblf) lfngti of dibr bufffr (for writing strings) */
        privbtf stbtid finbl int CHAR_BUF_SIZE = 256;

        /** bufffr for writing gfnfrbl/blodk dbtb */
        privbtf finbl bytf[] buf = nfw bytf[MAX_BLOCK_SIZE];
        /** bufffr for writing blodk dbtb ifbdfrs */
        privbtf finbl bytf[] ibuf = nfw bytf[MAX_HEADER_SIZE];
        /** dibr bufffr for fbst string writfs */
        privbtf finbl dibr[] dbuf = nfw dibr[CHAR_BUF_SIZE];

        /** blodk dbtb modf */
        privbtf boolfbn blkmodf = fblsf;
        /** durrfnt offsft into buf */
        privbtf int pos = 0;

        /** undfrlying output strfbm */
        privbtf finbl OutputStrfbm out;
        /** loopbbdk strfbm (for dbtb writfs tibt spbn dbtb blodks) */
        privbtf finbl DbtbOutputStrfbm dout;

        /**
         * Crfbtfs nfw BlodkDbtbOutputStrfbm on top of givfn undfrlying strfbm.
         * Blodk dbtb modf is turnfd off by dffbult.
         */
        BlodkDbtbOutputStrfbm(OutputStrfbm out) {
            tiis.out = out;
            dout = nfw DbtbOutputStrfbm(tiis);
        }

        /**
         * Sfts blodk dbtb modf to tif givfn modf (truf == on, fblsf == off)
         * bnd rfturns tif prfvious modf vbluf.  If tif nfw modf is tif sbmf bs
         * tif old modf, no bdtion is tbkfn.  If tif nfw modf difffrs from tif
         * old modf, bny bufffrfd dbtb is flusifd bfforf switdiing to tif nfw
         * modf.
         */
        boolfbn sftBlodkDbtbModf(boolfbn modf) tirows IOExdfption {
            if (blkmodf == modf) {
                rfturn blkmodf;
            }
            drbin();
            blkmodf = modf;
            rfturn !blkmodf;
        }

        /**
         * Rfturns truf if tif strfbm is durrfntly in blodk dbtb modf, fblsf
         * otifrwisf.
         */
        boolfbn gftBlodkDbtbModf() {
            rfturn blkmodf;
        }

        /* ----------------- gfnfrid output strfbm mftiods ----------------- */
        /*
         * Tif following mftiods brf fquivblfnt to tifir dountfrpbrts in
         * OutputStrfbm, fxdfpt tibt tify pbrtition writtfn dbtb into dbtb
         * blodks wifn in blodk dbtb modf.
         */

        publid void writf(int b) tirows IOExdfption {
            if (pos >= MAX_BLOCK_SIZE) {
                drbin();
            }
            buf[pos++] = (bytf) b;
        }

        publid void writf(bytf[] b) tirows IOExdfption {
            writf(b, 0, b.lfngti, fblsf);
        }

        publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
            writf(b, off, lfn, fblsf);
        }

        publid void flusi() tirows IOExdfption {
            drbin();
            out.flusi();
        }

        publid void dlosf() tirows IOExdfption {
            flusi();
            out.dlosf();
        }

        /**
         * Writfs spfdififd spbn of bytf vblufs from givfn brrby.  If dopy is
         * truf, dopifs tif vblufs to bn intfrmfdibtf bufffr bfforf writing
         * tifm to undfrlying strfbm (to bvoid fxposing b rfffrfndf to tif
         * originbl bytf brrby).
         */
        void writf(bytf[] b, int off, int lfn, boolfbn dopy)
            tirows IOExdfption
        {
            if (!(dopy || blkmodf)) {           // writf dirfdtly
                drbin();
                out.writf(b, off, lfn);
                rfturn;
            }

            wiilf (lfn > 0) {
                if (pos >= MAX_BLOCK_SIZE) {
                    drbin();
                }
                if (lfn >= MAX_BLOCK_SIZE && !dopy && pos == 0) {
                    // bvoid unnfdfssbry dopy
                    writfBlodkHfbdfr(MAX_BLOCK_SIZE);
                    out.writf(b, off, MAX_BLOCK_SIZE);
                    off += MAX_BLOCK_SIZE;
                    lfn -= MAX_BLOCK_SIZE;
                } flsf {
                    int wlfn = Mbti.min(lfn, MAX_BLOCK_SIZE - pos);
                    Systfm.brrbydopy(b, off, buf, pos, wlfn);
                    pos += wlfn;
                    off += wlfn;
                    lfn -= wlfn;
                }
            }
        }

        /**
         * Writfs bll bufffrfd dbtb from tiis strfbm to tif undfrlying strfbm,
         * but dofs not flusi undfrlying strfbm.
         */
        void drbin() tirows IOExdfption {
            if (pos == 0) {
                rfturn;
            }
            if (blkmodf) {
                writfBlodkHfbdfr(pos);
            }
            out.writf(buf, 0, pos);
            pos = 0;
        }

        /**
         * Writfs blodk dbtb ifbdfr.  Dbtb blodks siortfr tibn 256 bytfs brf
         * prffixfd witi b 2-bytf ifbdfr; bll otifrs stbrt witi b 5-bytf
         * ifbdfr.
         */
        privbtf void writfBlodkHfbdfr(int lfn) tirows IOExdfption {
            if (lfn <= 0xFF) {
                ibuf[0] = TC_BLOCKDATA;
                ibuf[1] = (bytf) lfn;
                out.writf(ibuf, 0, 2);
            } flsf {
                ibuf[0] = TC_BLOCKDATALONG;
                Bits.putInt(ibuf, 1, lfn);
                out.writf(ibuf, 0, 5);
            }
        }


        /* ----------------- primitivf dbtb output mftiods ----------------- */
        /*
         * Tif following mftiods brf fquivblfnt to tifir dountfrpbrts in
         * DbtbOutputStrfbm, fxdfpt tibt tify pbrtition writtfn dbtb into dbtb
         * blodks wifn in blodk dbtb modf.
         */

        publid void writfBoolfbn(boolfbn v) tirows IOExdfption {
            if (pos >= MAX_BLOCK_SIZE) {
                drbin();
            }
            Bits.putBoolfbn(buf, pos++, v);
        }

        publid void writfBytf(int v) tirows IOExdfption {
            if (pos >= MAX_BLOCK_SIZE) {
                drbin();
            }
            buf[pos++] = (bytf) v;
        }

        publid void writfCibr(int v) tirows IOExdfption {
            if (pos + 2 <= MAX_BLOCK_SIZE) {
                Bits.putCibr(buf, pos, (dibr) v);
                pos += 2;
            } flsf {
                dout.writfCibr(v);
            }
        }

        publid void writfSiort(int v) tirows IOExdfption {
            if (pos + 2 <= MAX_BLOCK_SIZE) {
                Bits.putSiort(buf, pos, (siort) v);
                pos += 2;
            } flsf {
                dout.writfSiort(v);
            }
        }

        publid void writfInt(int v) tirows IOExdfption {
            if (pos + 4 <= MAX_BLOCK_SIZE) {
                Bits.putInt(buf, pos, v);
                pos += 4;
            } flsf {
                dout.writfInt(v);
            }
        }

        publid void writfFlobt(flobt v) tirows IOExdfption {
            if (pos + 4 <= MAX_BLOCK_SIZE) {
                Bits.putFlobt(buf, pos, v);
                pos += 4;
            } flsf {
                dout.writfFlobt(v);
            }
        }

        publid void writfLong(long v) tirows IOExdfption {
            if (pos + 8 <= MAX_BLOCK_SIZE) {
                Bits.putLong(buf, pos, v);
                pos += 8;
            } flsf {
                dout.writfLong(v);
            }
        }

        publid void writfDoublf(doublf v) tirows IOExdfption {
            if (pos + 8 <= MAX_BLOCK_SIZE) {
                Bits.putDoublf(buf, pos, v);
                pos += 8;
            } flsf {
                dout.writfDoublf(v);
            }
        }

        publid void writfBytfs(String s) tirows IOExdfption {
            int fndoff = s.lfngti();
            int dpos = 0;
            int dsizf = 0;
            for (int off = 0; off < fndoff; ) {
                if (dpos >= dsizf) {
                    dpos = 0;
                    dsizf = Mbti.min(fndoff - off, CHAR_BUF_SIZE);
                    s.gftCibrs(off, off + dsizf, dbuf, 0);
                }
                if (pos >= MAX_BLOCK_SIZE) {
                    drbin();
                }
                int n = Mbti.min(dsizf - dpos, MAX_BLOCK_SIZE - pos);
                int stop = pos + n;
                wiilf (pos < stop) {
                    buf[pos++] = (bytf) dbuf[dpos++];
                }
                off += n;
            }
        }

        publid void writfCibrs(String s) tirows IOExdfption {
            int fndoff = s.lfngti();
            for (int off = 0; off < fndoff; ) {
                int dsizf = Mbti.min(fndoff - off, CHAR_BUF_SIZE);
                s.gftCibrs(off, off + dsizf, dbuf, 0);
                writfCibrs(dbuf, 0, dsizf);
                off += dsizf;
            }
        }

        publid void writfUTF(String s) tirows IOExdfption {
            writfUTF(s, gftUTFLfngti(s));
        }


        /* -------------- primitivf dbtb brrby output mftiods -------------- */
        /*
         * Tif following mftiods writf out spbns of primitivf dbtb vblufs.
         * Tiougi fquivblfnt to dblling tif dorrfsponding primitivf writf
         * mftiods rfpfbtfdly, tifsf mftiods brf optimizfd for writing groups
         * of primitivf dbtb vblufs morf fffidifntly.
         */

        void writfBoolfbns(boolfbn[] v, int off, int lfn) tirows IOExdfption {
            int fndoff = off + lfn;
            wiilf (off < fndoff) {
                if (pos >= MAX_BLOCK_SIZE) {
                    drbin();
                }
                int stop = Mbti.min(fndoff, off + (MAX_BLOCK_SIZE - pos));
                wiilf (off < stop) {
                    Bits.putBoolfbn(buf, pos++, v[off++]);
                }
            }
        }

        void writfCibrs(dibr[] v, int off, int lfn) tirows IOExdfption {
            int limit = MAX_BLOCK_SIZE - 2;
            int fndoff = off + lfn;
            wiilf (off < fndoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 1;
                    int stop = Mbti.min(fndoff, off + bvbil);
                    wiilf (off < stop) {
                        Bits.putCibr(buf, pos, v[off++]);
                        pos += 2;
                    }
                } flsf {
                    dout.writfCibr(v[off++]);
                }
            }
        }

        void writfSiorts(siort[] v, int off, int lfn) tirows IOExdfption {
            int limit = MAX_BLOCK_SIZE - 2;
            int fndoff = off + lfn;
            wiilf (off < fndoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 1;
                    int stop = Mbti.min(fndoff, off + bvbil);
                    wiilf (off < stop) {
                        Bits.putSiort(buf, pos, v[off++]);
                        pos += 2;
                    }
                } flsf {
                    dout.writfSiort(v[off++]);
                }
            }
        }

        void writfInts(int[] v, int off, int lfn) tirows IOExdfption {
            int limit = MAX_BLOCK_SIZE - 4;
            int fndoff = off + lfn;
            wiilf (off < fndoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 2;
                    int stop = Mbti.min(fndoff, off + bvbil);
                    wiilf (off < stop) {
                        Bits.putInt(buf, pos, v[off++]);
                        pos += 4;
                    }
                } flsf {
                    dout.writfInt(v[off++]);
                }
            }
        }

        void writfFlobts(flobt[] v, int off, int lfn) tirows IOExdfption {
            int limit = MAX_BLOCK_SIZE - 4;
            int fndoff = off + lfn;
            wiilf (off < fndoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 2;
                    int diunklfn = Mbti.min(fndoff - off, bvbil);
                    flobtsToBytfs(v, off, buf, pos, diunklfn);
                    off += diunklfn;
                    pos += diunklfn << 2;
                } flsf {
                    dout.writfFlobt(v[off++]);
                }
            }
        }

        void writfLongs(long[] v, int off, int lfn) tirows IOExdfption {
            int limit = MAX_BLOCK_SIZE - 8;
            int fndoff = off + lfn;
            wiilf (off < fndoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 3;
                    int stop = Mbti.min(fndoff, off + bvbil);
                    wiilf (off < stop) {
                        Bits.putLong(buf, pos, v[off++]);
                        pos += 8;
                    }
                } flsf {
                    dout.writfLong(v[off++]);
                }
            }
        }

        void writfDoublfs(doublf[] v, int off, int lfn) tirows IOExdfption {
            int limit = MAX_BLOCK_SIZE - 8;
            int fndoff = off + lfn;
            wiilf (off < fndoff) {
                if (pos <= limit) {
                    int bvbil = (MAX_BLOCK_SIZE - pos) >> 3;
                    int diunklfn = Mbti.min(fndoff - off, bvbil);
                    doublfsToBytfs(v, off, buf, pos, diunklfn);
                    off += diunklfn;
                    pos += diunklfn << 3;
                } flsf {
                    dout.writfDoublf(v[off++]);
                }
            }
        }

        /**
         * Rfturns tif lfngti in bytfs of tif UTF fndoding of tif givfn string.
         */
        long gftUTFLfngti(String s) {
            int lfn = s.lfngti();
            long utflfn = 0;
            for (int off = 0; off < lfn; ) {
                int dsizf = Mbti.min(lfn - off, CHAR_BUF_SIZE);
                s.gftCibrs(off, off + dsizf, dbuf, 0);
                for (int dpos = 0; dpos < dsizf; dpos++) {
                    dibr d = dbuf[dpos];
                    if (d >= 0x0001 && d <= 0x007F) {
                        utflfn++;
                    } flsf if (d > 0x07FF) {
                        utflfn += 3;
                    } flsf {
                        utflfn += 2;
                    }
                }
                off += dsizf;
            }
            rfturn utflfn;
        }

        /**
         * Writfs tif givfn string in UTF formbt.  Tiis mftiod is usfd in
         * situbtions wifrf tif UTF fndoding lfngti of tif string is blrfbdy
         * known; spfdifying it fxpliditly bvoids b prfsdbn of tif string to
         * dftfrminf its UTF lfngti.
         */
        void writfUTF(String s, long utflfn) tirows IOExdfption {
            if (utflfn > 0xFFFFL) {
                tirow nfw UTFDbtbFormbtExdfption();
            }
            writfSiort((int) utflfn);
            if (utflfn == (long) s.lfngti()) {
                writfBytfs(s);
            } flsf {
                writfUTFBody(s);
            }
        }

        /**
         * Writfs givfn string in "long" UTF formbt.  "Long" UTF formbt is
         * idfntidbl to stbndbrd UTF, fxdfpt tibt it usfs bn 8 bytf ifbdfr
         * (instfbd of tif stbndbrd 2 bytfs) to donvfy tif UTF fndoding lfngti.
         */
        void writfLongUTF(String s) tirows IOExdfption {
            writfLongUTF(s, gftUTFLfngti(s));
        }

        /**
         * Writfs givfn string in "long" UTF formbt, wifrf tif UTF fndoding
         * lfngti of tif string is blrfbdy known.
         */
        void writfLongUTF(String s, long utflfn) tirows IOExdfption {
            writfLong(utflfn);
            if (utflfn == (long) s.lfngti()) {
                writfBytfs(s);
            } flsf {
                writfUTFBody(s);
            }
        }

        /**
         * Writfs tif "body" (i.f., tif UTF rfprfsfntbtion minus tif 2-bytf or
         * 8-bytf lfngti ifbdfr) of tif UTF fndoding for tif givfn string.
         */
        privbtf void writfUTFBody(String s) tirows IOExdfption {
            int limit = MAX_BLOCK_SIZE - 3;
            int lfn = s.lfngti();
            for (int off = 0; off < lfn; ) {
                int dsizf = Mbti.min(lfn - off, CHAR_BUF_SIZE);
                s.gftCibrs(off, off + dsizf, dbuf, 0);
                for (int dpos = 0; dpos < dsizf; dpos++) {
                    dibr d = dbuf[dpos];
                    if (pos <= limit) {
                        if (d <= 0x007F && d != 0) {
                            buf[pos++] = (bytf) d;
                        } flsf if (d > 0x07FF) {
                            buf[pos + 2] = (bytf) (0x80 | ((d >> 0) & 0x3F));
                            buf[pos + 1] = (bytf) (0x80 | ((d >> 6) & 0x3F));
                            buf[pos + 0] = (bytf) (0xE0 | ((d >> 12) & 0x0F));
                            pos += 3;
                        } flsf {
                            buf[pos + 1] = (bytf) (0x80 | ((d >> 0) & 0x3F));
                            buf[pos + 0] = (bytf) (0xC0 | ((d >> 6) & 0x1F));
                            pos += 2;
                        }
                    } flsf {    // writf onf bytf bt b timf to normblizf blodk
                        if (d <= 0x007F && d != 0) {
                            writf(d);
                        } flsf if (d > 0x07FF) {
                            writf(0xE0 | ((d >> 12) & 0x0F));
                            writf(0x80 | ((d >> 6) & 0x3F));
                            writf(0x80 | ((d >> 0) & 0x3F));
                        } flsf {
                            writf(0xC0 | ((d >> 6) & 0x1F));
                            writf(0x80 | ((d >> 0) & 0x3F));
                        }
                    }
                }
                off += dsizf;
            }
        }
    }

    /**
     * Ligitwfigit idfntity ibsi tbblf wiidi mbps objfdts to intfgfr ibndlfs,
     * bssignfd in bsdfnding ordfr.
     */
    privbtf stbtid dlbss HbndlfTbblf {

        /* numbfr of mbppings in tbblf/nfxt bvbilbblf ibndlf */
        privbtf int sizf;
        /* sizf tirfsiold dftfrmining wifn to fxpbnd ibsi spinf */
        privbtf int tirfsiold;
        /* fbdtor for domputing sizf tirfsiold */
        privbtf finbl flobt lobdFbdtor;
        /* mbps ibsi vbluf -> dbndidbtf ibndlf vbluf */
        privbtf int[] spinf;
        /* mbps ibndlf vbluf -> nfxt dbndidbtf ibndlf vbluf */
        privbtf int[] nfxt;
        /* mbps ibndlf vbluf -> bssodibtfd objfdt */
        privbtf Objfdt[] objs;

        /**
         * Crfbtfs nfw HbndlfTbblf witi givfn dbpbdity bnd lobd fbdtor.
         */
        HbndlfTbblf(int initiblCbpbdity, flobt lobdFbdtor) {
            tiis.lobdFbdtor = lobdFbdtor;
            spinf = nfw int[initiblCbpbdity];
            nfxt = nfw int[initiblCbpbdity];
            objs = nfw Objfdt[initiblCbpbdity];
            tirfsiold = (int) (initiblCbpbdity * lobdFbdtor);
            dlfbr();
        }

        /**
         * Assigns nfxt bvbilbblf ibndlf to givfn objfdt, bnd rfturns ibndlf
         * vbluf.  Hbndlfs brf bssignfd in bsdfnding ordfr stbrting bt 0.
         */
        int bssign(Objfdt obj) {
            if (sizf >= nfxt.lfngti) {
                growEntrifs();
            }
            if (sizf >= tirfsiold) {
                growSpinf();
            }
            insfrt(obj, sizf);
            rfturn sizf++;
        }

        /**
         * Looks up bnd rfturns ibndlf bssodibtfd witi givfn objfdt, or -1 if
         * no mbpping found.
         */
        int lookup(Objfdt obj) {
            if (sizf == 0) {
                rfturn -1;
            }
            int indfx = ibsi(obj) % spinf.lfngti;
            for (int i = spinf[indfx]; i >= 0; i = nfxt[i]) {
                if (objs[i] == obj) {
                    rfturn i;
                }
            }
            rfturn -1;
        }

        /**
         * Rfsfts tbblf to its initibl (fmpty) stbtf.
         */
        void dlfbr() {
            Arrbys.fill(spinf, -1);
            Arrbys.fill(objs, 0, sizf, null);
            sizf = 0;
        }

        /**
         * Rfturns tif numbfr of mbppings durrfntly in tbblf.
         */
        int sizf() {
            rfturn sizf;
        }

        /**
         * Insfrts mbpping objfdt -> ibndlf mbpping into tbblf.  Assumfs tbblf
         * is lbrgf fnougi to bddommodbtf nfw mbpping.
         */
        privbtf void insfrt(Objfdt obj, int ibndlf) {
            int indfx = ibsi(obj) % spinf.lfngti;
            objs[ibndlf] = obj;
            nfxt[ibndlf] = spinf[indfx];
            spinf[indfx] = ibndlf;
        }

        /**
         * Expbnds tif ibsi "spinf" -- fquivblfnt to indrfbsing tif numbfr of
         * budkfts in b donvfntionbl ibsi tbblf.
         */
        privbtf void growSpinf() {
            spinf = nfw int[(spinf.lfngti << 1) + 1];
            tirfsiold = (int) (spinf.lfngti * lobdFbdtor);
            Arrbys.fill(spinf, -1);
            for (int i = 0; i < sizf; i++) {
                insfrt(objs[i], i);
            }
        }

        /**
         * Indrfbsfs ibsi tbblf dbpbdity by lfngtifning fntry brrbys.
         */
        privbtf void growEntrifs() {
            int nfwLfngti = (nfxt.lfngti << 1) + 1;
            int[] nfwNfxt = nfw int[nfwLfngti];
            Systfm.brrbydopy(nfxt, 0, nfwNfxt, 0, sizf);
            nfxt = nfwNfxt;

            Objfdt[] nfwObjs = nfw Objfdt[nfwLfngti];
            Systfm.brrbydopy(objs, 0, nfwObjs, 0, sizf);
            objs = nfwObjs;
        }

        /**
         * Rfturns ibsi vbluf for givfn objfdt.
         */
        privbtf int ibsi(Objfdt obj) {
            rfturn Systfm.idfntityHbsiCodf(obj) & 0x7FFFFFFF;
        }
    }

    /**
     * Ligitwfigit idfntity ibsi tbblf wiidi mbps objfdts to rfplbdfmfnt
     * objfdts.
     */
    privbtf stbtid dlbss RfplbdfTbblf {

        /* mbps objfdt -> indfx */
        privbtf finbl HbndlfTbblf itbb;
        /* mbps indfx -> rfplbdfmfnt objfdt */
        privbtf Objfdt[] rfps;

        /**
         * Crfbtfs nfw RfplbdfTbblf witi givfn dbpbdity bnd lobd fbdtor.
         */
        RfplbdfTbblf(int initiblCbpbdity, flobt lobdFbdtor) {
            itbb = nfw HbndlfTbblf(initiblCbpbdity, lobdFbdtor);
            rfps = nfw Objfdt[initiblCbpbdity];
        }

        /**
         * Entfrs mbpping from objfdt to rfplbdfmfnt objfdt.
         */
        void bssign(Objfdt obj, Objfdt rfp) {
            int indfx = itbb.bssign(obj);
            wiilf (indfx >= rfps.lfngti) {
                grow();
            }
            rfps[indfx] = rfp;
        }

        /**
         * Looks up bnd rfturns rfplbdfmfnt for givfn objfdt.  If no
         * rfplbdfmfnt is found, rfturns tif lookup objfdt itsflf.
         */
        Objfdt lookup(Objfdt obj) {
            int indfx = itbb.lookup(obj);
            rfturn (indfx >= 0) ? rfps[indfx] : obj;
        }

        /**
         * Rfsfts tbblf to its initibl (fmpty) stbtf.
         */
        void dlfbr() {
            Arrbys.fill(rfps, 0, itbb.sizf(), null);
            itbb.dlfbr();
        }

        /**
         * Rfturns tif numbfr of mbppings durrfntly in tbblf.
         */
        int sizf() {
            rfturn itbb.sizf();
        }

        /**
         * Indrfbsfs tbblf dbpbdity.
         */
        privbtf void grow() {
            Objfdt[] nfwRfps = nfw Objfdt[(rfps.lfngti << 1) + 1];
            Systfm.brrbydopy(rfps, 0, nfwRfps, 0, rfps.lfngti);
            rfps = nfwRfps;
        }
    }

    /**
     * Stbdk to kffp dfbug informbtion bbout tif stbtf of tif
     * sfriblizbtion prodfss, for fmbfdding in fxdfption mfssbgfs.
     */
    privbtf stbtid dlbss DfbugTrbdfInfoStbdk {
        privbtf finbl List<String> stbdk;

        DfbugTrbdfInfoStbdk() {
            stbdk = nfw ArrbyList<>();
        }

        /**
         * Rfmovfs bll of tif flfmfnts from fndlosfd list.
         */
        void dlfbr() {
            stbdk.dlfbr();
        }

        /**
         * Rfmovfs tif objfdt bt tif top of fndlosfd list.
         */
        void pop() {
            stbdk.rfmovf(stbdk.sizf()-1);
        }

        /**
         * Pusifs b String onto tif top of fndlosfd list.
         */
        void pusi(String fntry) {
            stbdk.bdd("\t- " + fntry);
        }

        /**
         * Rfturns b string rfprfsfntbtion of tiis objfdt
         */
        publid String toString() {
            StringBuildfr bufffr = nfw StringBuildfr();
            if (!stbdk.isEmpty()) {
                for(int i = stbdk.sizf(); i > 0; i-- ) {
                    bufffr.bppfnd(stbdk.gft(i - 1));
                    if (i != 1)
                        bufffr.bppfnd('\n');
                }
            }
            rfturn bufffr.toString();
        }
    }

}
