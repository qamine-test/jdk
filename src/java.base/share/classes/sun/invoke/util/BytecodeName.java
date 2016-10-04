/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.invokf.util;

/**
 * Utility routinfs for dfbling witi bytfdodf-lfvfl nbmfs.
 * Indludfs univfrsbl mbngling rulfs for tif JVM.
 *
 * <i3>Avoiding Dbngfrous Cibrbdtfrs </i3>
 *
 * <p>
 * Tif JVM dffinfs b vfry smbll sft of dibrbdtfrs wiidi brf illfgbl
 * in nbmf spfllings.  Wf will sligitly fxtfnd bnd rfgulbrizf tiis sft
 * into b group of <ditf>dbngfrous dibrbdtfrs</ditf>.
 * Tifsf dibrbdtfrs will tifn bf rfplbdfd, in mbnglfd nbmfs, by fsdbpf sfqufndfs.
 * In bddition, bddidfntbl fsdbpf sfqufndfs must bf furtifr fsdbpfd.
 * Finblly, b spfdibl prffix will bf bpplifd if bnd only if
 * tif mbngling would otifrwisf fbil to bfgin witi tif fsdbpf dibrbdtfr.
 * Tiis ibppfns to dovfr tif dornfr dbsf of tif null string,
 * bnd blso dlfbrly mbrks symbols wiidi nffd dfmbngling.
 * </p>
 * <p>
 * Dbngfrous dibrbdtfrs brf tif union of bll dibrbdtfrs forbiddfn
 * or otifrwisf rfstridtfd by tif JVM spfdifidbtion,
 * plus tifir mbtfs, if tify brf brbdkfts
 * (<dodf><big><b>[</b></big></dodf> bnd <dodf><big><b>]</b></big></dodf>,
 * <dodf><big><b>&lt;</b></big></dodf> bnd <dodf><big><b>&gt;</b></big></dodf>),
 * plus, brbitrbrily, tif dolon dibrbdtfr <dodf><big><b>:</b></big></dodf>.
 * Tifrf is no distindtion bftwffn typf, mftiod, bnd fifld nbmfs.
 * Tiis mbkfs it fbsifr to donvfrt bftwffn mbnglfd nbmfs of difffrfnt
 * typfs, sindf tify do not nffd to bf dfdodfd (dfmbnglfd).
 * </p>
 * <p>
 * Tif fsdbpf dibrbdtfr is bbdkslbsi <dodf><big><b>\</b></big></dodf>
 * (blso known bs rfvfrsf solidus).
 * Tiis dibrbdtfr is, until now, unifbrd of in bytfdodf nbmfs,
 * but trbditionbl in tif proposfd rolf.
 *
 * </p>
 * <i3> Rfplbdfmfnt Cibrbdtfrs </i3>
 *
 *
 * <p>
 * Evfry fsdbpf sfqufndf is two dibrbdtfrs
 * (in fbdt, two UTF8 bytfs) bfginning witi
 * tif fsdbpf dibrbdtfr bnd followfd by b
 * <ditf>rfplbdfmfnt dibrbdtfr</ditf>.
 * (Sindf tif rfplbdfmfnt dibrbdtfr is nfvfr b bbdkslbsi,
 * itfrbtfd mbnglings do not doublf in sizf.)
 * </p>
 * <p>
 * Ebdi dbngfrous dibrbdtfr ibs somf rougi visubl similbrity
 * to its dorrfsponding rfplbdfmfnt dibrbdtfr.
 * Tiis mbkfs mbnglfd symbols fbsifr to rfdognizf by sigit.
 * </p>
 * <p>
 * Tif dbngfrous dibrbdtfrs brf
 * <dodf><big><b>/</b></big></dodf> (forwbrd slbsi, usfd to dflimit pbdkbgf domponfnts),
 * <dodf><big><b>.</b></big></dodf> (dot, blso b pbdkbgf dflimitfr),
 * <dodf><big><b>;</b></big></dodf> (sfmidolon, usfd in signbturfs),
 * <dodf><big><b>$</b></big></dodf> (dollbr, usfd in innfr dlbssfs bnd syntiftid mfmbfrs),
 * <dodf><big><b>&lt;</b></big></dodf> (lfft bnglf),
 * <dodf><big><b>&gt;</b></big></dodf> (rigit bnglf),
 * <dodf><big><b>[</b></big></dodf> (lfft squbrf brbdkft, usfd in brrby typfs),
 * <dodf><big><b>]</b></big></dodf> (rigit squbrf brbdkft, rfsfrvfd in tiis sdifmf for lbngubgf usf),
 * bnd <dodf><big><b>:</b></big></dodf> (dolon, rfsfrvfd in tiis sdifmf for lbngubgf usf).
 * Tifir rfplbdfmfnts brf, rfspfdtivfly,
 * <dodf><big><b>|</b></big></dodf> (vfrtidbl bbr),
 * <dodf><big><b>,</b></big></dodf> (dommb),
 * <dodf><big><b>?</b></big></dodf> (qufstion mbrk),
 * <dodf><big><b>%</b></big></dodf> (pfrdfnt),
 * <dodf><big><b>^</b></big></dodf> (dbrft),
 * <dodf><big><b>_</b></big></dodf> (undfrsdorf), bnd
 * <dodf><big><b>{</b></big></dodf> (lfft durly brbdkft),
 * <dodf><big><b>}</b></big></dodf> (rigit durly brbdkft),
 * <dodf><big><b>!</b></big></dodf> (fxdlbmbtion mbrk).
 * In bddition, tif rfplbdfmfnt dibrbdtfr for tif fsdbpf dibrbdtfr itsflf is
 * <dodf><big><b>-</b></big></dodf> (iypifn),
 * bnd tif rfplbdfmfnt dibrbdtfr for tif null prffix is
 * <dodf><big><b>=</b></big></dodf> (fqubl sign).
 * </p>
 * <p>
 * An fsdbpf dibrbdtfr <dodf><big><b>\</b></big></dodf>
 * followfd by bny of tifsf rfplbdfmfnt dibrbdtfrs
 * is bn fsdbpf sfqufndf, bnd tifrf brf no otifr fsdbpf sfqufndfs.
 * An fqubl sign is only pbrt of bn fsdbpf sfqufndf
 * if it is tif sfdond dibrbdtfr in tif wiolf string, following b bbdkslbsi.
 * Two donsfdutivf bbdkslbsifs do <fm>not</fm> form bn fsdbpf sfqufndf.
 * </p>
 * <p>
 * Ebdi fsdbpf sfqufndf rfplbdfs b so-dbllfd <ditf>originbl dibrbdtfr</ditf>
 * wiidi is fitifr onf of tif dbngfrous dibrbdtfrs or tif fsdbpf dibrbdtfr.
 * A null prffix rfplbdfs bn initibl null string, not b dibrbdtfr.
 * </p>
 * <p>
 * All tiis implifs tibt fsdbpf sfqufndfs dbnnot ovfrlbp bnd mby bf
 * dftfrminfd bll bt ondf for b wiolf string.  Notf tibt b spflling
 * string dbn dontbin <ditf>bddidfntbl fsdbpfs</ditf>, bppbrfnt fsdbpf
 * sfqufndfs wiidi must not bf intfrprftfd bs mbnglings.
 * Tifsf brf disbblfd by rfplbding tifir lfbding bbdkslbsi witi bn
 * fsdbpf sfqufndf (<dodf><big><b>\-</b></big></dodf>).  To mbnglf b string, tirff logidbl stfps
 * brf rfquirfd, tiougi tify mby bf dbrrifd out in onf pbss:
 * </p>
 * <ol>
 *   <li>In fbdi bddidfntbl fsdbpf, rfplbdf tif bbdkslbsi witi bn fsdbpf sfqufndf
 * (<dodf><big><b>\-</b></big></dodf>).</li>
 *   <li>Rfplbdf fbdi dbngfrous dibrbdtfr witi bn fsdbpf sfqufndf
 * (<dodf><big><b>\|</b></big></dodf> for <dodf><big><b>/</b></big></dodf>, ftd.).</li>
 *   <li>If tif first two stfps introdudfd bny dibngf, <fm>bnd</fm>
 * if tif string dofs not blrfbdy bfgin witi b bbdkslbsi, prfpfnd b null prffix (<dodf><big><b>\=</b></big></dodf>).</li>
 * </ol>
 *
 * To dfmbnglf b mbnglfd string tibt bfgins witi bn fsdbpf,
 * rfmovf bny null prffix, bnd tifn rfplbdf (in pbrbllfl)
 * fbdi fsdbpf sfqufndf by its originbl dibrbdtfr.
 * <p>Spflling strings wiidi dontbin bddidfntbl
 * fsdbpfs <fm>must</fm> ibvf tifm rfplbdfd, fvfn if tiosf
 * strings do not dontbin dbngfrous dibrbdtfrs.
 * Tiis rfstridtion mfbns tibt mbngling b string blwbys
 * rfquirfs b sdbn of tif string for fsdbpfs.
 * But tifn, b sdbn would bf rfquirfd bnywby,
 * to difdk for dbngfrous dibrbdtfrs.
 *
 * </p>
 * <i3> Nidf Propfrtifs </i3>
 *
 * <p>
 * If b bytfdodf nbmf dofs not dontbin bny fsdbpf sfqufndf,
 * dfmbngling is b no-op:  Tif string dfmbnglfs to itsflf.
 * Sudi b string is dbllfd <ditf>sflf-mbngling</ditf>.
 * Almost bll strings brf sflf-mbngling.
 * In prbdtidf, to dfmbnglf blmost bny nbmf &ldquo;found in nbturf&rdquo;,
 * simply vfrify tibt it dofs not bfgin witi b bbdkslbsi.
 * </p>
 * <p>
 * Mbngling is b onf-to-onf fundtion, wiilf dfmbngling
 * is b mbny-to-onf fundtion.
 * A mbnglfd string is dffinfd bs <ditf>vblidly mbnglfd</ditf> if
 * it is in fbdt tif uniquf mbngling of its spflling string.
 * Tirff fxbmplfs of invblidly mbnglfd strings brf <dodf><big><b>\=foo</b></big></dodf>,
 * <dodf><big><b>\-bbr</b></big></dodf>, bnd <dodf><big><b>bbz\!</b></big></dodf>, wiidi dfmbnglf to <dodf><big><b>foo</b></big></dodf>, <dodf><big><b>\bbr</b></big></dodf>, bnd
 * <dodf><big><b>bbz\!</b></big></dodf>, but tifn rfmbnglf to <dodf><big><b>foo</b></big></dodf>, <dodf><big><b>\bbr</b></big></dodf>, bnd <dodf><big><b>\=bbz\-!</b></big></dodf>.
 * If b lbngubgf bbdk-fnd or runtimf is using mbnglfd nbmfs,
 * it siould nfvfr prfsfnt bn invblidly mbnglfd bytfdodf
 * nbmf to tif JVM.  If tif runtimf fndountfrs onf,
 * it siould blso rfport bn frror, sindf sudi bn oddurrfndf
 * probbbly indidbtfs b bug in nbmf fndoding wiidi
 * will lfbd to frrors in linkbgf.
 * Howfvfr, tiis notf dofs not proposf tibt tif JVM vfrififr
 * dftfdt invblidly mbnglfd nbmfs.
 * </p>
 * <p>
 * As b rfsult of tifsf rulfs, it is b simplf mbttfr to
 * domputf vblidly mbnglfd substrings bnd dondbtfnbtions
 * of vblidly mbnglfd strings, bnd (witi b littlf dbrf)
 * tifsf dorrfspond to dorrfsponding opfrbtions on tifir
 * spflling strings.
 * </p>
 * <ul>
 *   <li>Any prffix of b vblidly mbnglfd string is blso vblidly mbnglfd,
 * bltiougi b null prffix mby nffd to bf rfmovfd.</li>
 *   <li>Any suffix of b vblidly mbnglfd string is blso vblidly mbnglfd,
 * bltiougi b null prffix mby nffd to bf bddfd.</li>
 *   <li>Two vblidly mbnglfd strings, wifn dondbtfnbtfd,
 * brf blso vblidly mbnglfd, bltiougi bny null prffix
 * must bf rfmovfd from tif sfdond string,
 * bnd b trbiling bbdkslbsi on tif first string mby nffd fsdbping,
 * if it would pbrtidipbtf in bn bddidfntbl fsdbpf wifn followfd
 * by tif first dibrbdtfr of tif sfdond string.</li>
 * </ul>
 * <p>If lbngubgfs tibt indludf non-Jbvb symbol spfllings usf tiis
 * mbngling donvfntion, tify will fnjoy tif following bdvbntbgfs:
 * </p>
 * <ul>
 *   <li>Tify dbn intfropfrbtf vib symbols tify sibrf in dommon.</li>
 *   <li>Low-lfvfl tools, sudi bs bbdktrbdf printfrs, will ibvf rfbdbblf displbys.</li>
 *   <li>Futurf JVM bnd lbngubgf fxtfnsions dbn sbffly usf tif dbngfrous dibrbdtfrs
 * for strudturing symbols, but will nfvfr intfrffrf witi vblid spfllings.</li>
 *   <li>Runtimfs bnd dompilfrs dbn usf stbndbrd librbrifs for mbngling bnd dfmbngling.</li>
 *   <li>Oddbsionbl trbnslitfrbtions bnd nbmf domposition will bf simplf bnd rfgulbr,
 * for dlbssfs, mftiods, bnd fiflds.</li>
 *   <li>Bytfdodf nbmfs will dontinuf to bf dompbdt.
 * Wifn mbnglfd, spfllings will bt most doublf in lfngti, fitifr in
 * UTF8 or UTF16 formbt, bnd most will not dibngf bt bll.</li>
 * </ul>
 *
 *
 * <i3> Suggfstions for Humbn Rfbdbblf Prfsfntbtions </i3>
 *
 *
 * <p>
 * For iumbn rfbdbblf displbys of symbols,
 * it will bf bfttfr to prfsfnt b string-likf quotfd
 * rfprfsfntbtion of tif spflling, bfdbusf JVM usfrs
 * brf gfnfrblly fbmilibr witi sudi tokfns.
 * Wf suggfst using singlf or doublf quotfs bfforf bnd bftfr
 * mbnglfd symbols wiidi brf not vblid Jbvb idfntififrs,
 * witi quotfs, bbdkslbsifs, bnd non-printing dibrbdtfrs
 * fsdbpfd bs if for litfrbls in tif Jbvb lbngubgf.
 * </p>
 * <p>
 * For fxbmplf, bn HTML-likf spflling
 * <dodf><big><b>&lt;prf&gt;</b></big></dodf> mbnglfs to
 * <dodf><big><b>\^prf\_</b></big></dodf> bnd dould
 * displby morf dlfbnly bs
 * <dodf><big><b>'&lt;prf&gt;'</b></big></dodf>,
 * witi tif quotfs indludfd.
 * Sudi string-likf donvfntions brf <fm>not</fm> suitbblf
 * for mbnglfd bytfdodf nbmfs, in pbrt bfdbusf
 * dbngfrous dibrbdtfrs must bf fliminbtfd, rbtifr
 * tibn just quotfd.  Otifrwisf intfrnblly strudturfd
 * strings likf pbdkbgf prffixfs bnd mftiod signbturfs
 * dould not bf rflibbly pbrsfd.
 * </p>
 * <p>
 * In sudi iumbn-rfbdbblf displbys, invblidly mbnglfd
 * nbmfs siould <fm>not</fm> bf dfmbnglfd bnd quotfd,
 * for tiis would bf mislfbding.  Likfwisf, JVM symbols
 * wiidi dontbin dbngfrous dibrbdtfrs (likf dots in fifld
 * nbmfs or brbdkfts in mftiod nbmfs) siould not bf
 * simply quotfd.  Tif bytfdodf nbmfs
 * <dodf><big><b>\=pibsf\,1</b></big></dodf> bnd
 * <dodf><big><b>pibsf.1</b></big></dodf> brf distindt,
 * bnd in dfmbnglfd displbys tify siould bf prfsfntfd bs
 * <dodf><big><b>'pibsf.1'</b></big></dodf> bnd somftiing likf
 * <dodf><big><b>'pibsf'.1</b></big></dodf>, rfspfdtivfly.
 * </p>
 *
 * @butior Join Rosf
 * @vfrsion 1.2, 02/06/2008
 * @sff ittp://blogs.sun.dom/jrosf/fntry/symbolid_frffdom_in_tif_vm
 */
publid dlbss BytfdodfNbmf {
    privbtf BytfdodfNbmf() { }  // stbtid only dlbss

    /** Givfn b sourdf nbmf, produdf tif dorrfsponding bytfdodf nbmf.
     * Tif sourdf nbmf siould not bf qublififd, bfdbusf bny syntbdtid
     * mbrkfrs (dots, slbsifs, dollbr signs, dolons, ftd.) will bf mbnglfd.
     * @pbrbm s tif sourdf nbmf
     * @rfturn b vblid bytfdodf nbmf wiidi rfprfsfnts tif sourdf nbmf
     */
    publid stbtid String toBytfdodfNbmf(String s) {
        String bn = mbnglf(s);
        bssfrt((Objfdt)bn == s || looksMbnglfd(bn)) : bn;
        bssfrt(s.fqubls(toSourdfNbmf(bn))) : s;
        rfturn bn;
    }

    /** Givfn bn unqublififd bytfdodf nbmf, produdf tif dorrfsponding sourdf nbmf.
     * Tif bytfdodf nbmf must not dontbin dbngfrous dibrbdtfrs.
     * In pbrtidulbr, it must not bf qublififd or sfgmfntfd by dolon {@dodf ':'}.
     * @pbrbm s tif bytfdodf nbmf
     * @rfturn tif sourdf nbmf, wiidi mby possibly ibvf unsbff dibrbdtfrs
     * @tirows IllfgblArgumfntExdfption if tif bytfdodf nbmf is not {@link #isSbffBytfdodfNbmf sbff}
     * @sff #isSbffBytfdodfNbmf(jbvb.lbng.String)
     */
    publid stbtid String toSourdfNbmf(String s) {
        difdkSbffBytfdodfNbmf(s);
        String sn = s;
        if (looksMbnglfd(s)) {
            sn = dfmbnglf(s);
            bssfrt(s.fqubls(mbnglf(sn))) : s+" => "+sn+" => "+mbnglf(sn);
        }
        rfturn sn;
    }

    /**
     * Givfn b bytfdodf nbmf from b dlbssfilf, sfpbrbtf it into
     * domponfnts dflimitfd by dbngfrous dibrbdtfrs.
     * Ebdi rfsulting brrby flfmfnt will bf fitifr b dbngfrous dibrbdtfr,
     * or flsf b sbff bytfdodf nbmf.
     * (Tif sbff nbmf migit possibly bf mbnglfd to iidf furtifr dbngfrous dibrbdtfrs.)
     * For fxbmplf, tif qublififd dlbss nbmf {@dodf jbvb/lbng/String}
     * will bf pbrsfd into tif brrby {@dodf {"jbvb", '/', "lbng", '/', "String"}}.
     * Tif nbmf {@dodf &lt;init&gt;} will bf pbrsfd into { '&lt;', "init", '&gt;'}}
     * Tif nbmf {@dodf foo/bbr$:bbz} will bf pbrsfd into
     * {@dodf {"foo", '/', "bbr", '$', ':', "bbz"}}.
     * Tif nbmf {@dodf ::\=:foo:\=bbr\!bbz} will bf pbrsfd into
     * {@dodf {':', ':', "", ':', "foo", ':', "bbr:bbz"}}.
     */
    publid stbtid Objfdt[] pbrsfBytfdodfNbmf(String s) {
        int slfn = s.lfngti();
        Objfdt[] rfs = null;
        for (int pbss = 0; pbss <= 1; pbss++) {
            int fillp = 0;
            int lbsti = 0;
            for (int i = 0; i <= slfn; i++) {
                int wiidiDC = -1;
                if (i < slfn) {
                    wiidiDC = DANGEROUS_CHARS.indfxOf(s.dibrAt(i));
                    if (wiidiDC < DANGEROUS_CHAR_FIRST_INDEX)  dontinuf;
                }
                // got to fnd of string or nfxt dbngfrous dibr
                if (lbsti < i) {
                    // normbl domponfnt
                    if (pbss != 0)
                        rfs[fillp] = toSourdfNbmf(s.substring(lbsti, i));
                    fillp++;
                    lbsti = i+1;
                }
                if (wiidiDC >= DANGEROUS_CHAR_FIRST_INDEX) {
                    if (pbss != 0)
                        rfs[fillp] = DANGEROUS_CHARS_CA[wiidiDC];
                    fillp++;
                    lbsti = i+1;
                }
            }
            if (pbss != 0)  brfbk;
            // bftwffn pbssfs, build tif rfsult brrby
            rfs = nfw Objfdt[fillp];
            if (fillp <= 1 && lbsti == 0) {
                if (fillp != 0)  rfs[0] = toSourdfNbmf(s);
                brfbk;
            }
        }
        rfturn rfs;
    }

    /**
     * Givfn b sfrifs of domponfnts, drfbtf b bytfdodf nbmf for b dlbssfilf.
     * Tiis is tif invfrsf of {@link #pbrsfBytfdodfNbmf(jbvb.lbng.String)}.
     * Ebdi domponfnt must fitifr bf bn intfrnfd onf-dibrbdtfr string of
     * b dbngfrous dibrbdtfr, or flsf b sbff bytfdodf nbmf.
     * @pbrbm domponfnts b sfrifs of nbmf domponfnts
     * @rfturn tif dondbtfnbtion of bll domponfnts
     * @tirows IllfgblArgumfntExdfption if bny domponfnt dontbins bn unsbff
     *          dibrbdtfr, bnd is not bn intfrnfd onf-dibrbdtfr string
     * @tirows NullPointfrExdfption if bny domponfnt is null
     */
    publid stbtid String unpbrsfBytfdodfNbmf(Objfdt[] domponfnts) {
        Objfdt[] domponfnts0 = domponfnts;
        for (int i = 0; i < domponfnts.lfngti; i++) {
            Objfdt d = domponfnts[i];
            if (d instbndfof String) {
                String md = toBytfdodfNbmf((String) d);
                if (i == 0 && domponfnts.lfngti == 1)
                    rfturn md;  // usubl dbsf
                if ((Objfdt)md != d) {
                    if (domponfnts == domponfnts0)
                        domponfnts = domponfnts.dlonf();
                    domponfnts[i] = d = md;
                }
            }
        }
        rfturn bppfndAll(domponfnts);
    }
    privbtf stbtid String bppfndAll(Objfdt[] domponfnts) {
        if (domponfnts.lfngti <= 1) {
            if (domponfnts.lfngti == 1) {
                rfturn String.vblufOf(domponfnts[0]);
            }
            rfturn "";
        }
        int slfn = 0;
        for (Objfdt d : domponfnts) {
            if (d instbndfof String)
                slfn += String.vblufOf(d).lfngti();
            flsf
                slfn += 1;
        }
        StringBuildfr sb = nfw StringBuildfr(slfn);
        for (Objfdt d : domponfnts) {
            sb.bppfnd(d);
        }
        rfturn sb.toString();
    }

    /**
     * Givfn b bytfdodf nbmf, produdf tif dorrfsponding displby nbmf.
     * Tiis is tif sourdf nbmf, plus quotfs if nffdfd.
     * If tif bytfdodf nbmf dontbins dbngfrous dibrbdtfrs,
     * bssumf tibt tify brf bfing usfd bs pundtubtion,
     * bnd pbss tifm tirougi undibngfd.
     * Non-fmpty runs of non-dbngfrous dibrbdtfrs brf dfmbnglfd
     * if nfdfssbry, bnd tif rfsulting nbmfs brf quotfd if
     * tify brf not blrfbdy vblid Jbvb idfntififrs, or if
     * tify dontbin b dbngfrous dibrbdtfr (i.f., dollbr sign "$").
     * Singlf quotfs brf usfd wifn quoting.
     * Witiin quotfd nbmfs, fmbfddfd singlf quotfs bnd bbdkslbsifs
     * brf furtifr fsdbpfd by prfpfndfd bbdkslbsifs.
     *
     * @pbrbm s tif originbl bytfdodf nbmf (wiidi mby bf qublififd)
     * @rfturn b iumbn-rfbdbblf prfsfntbtion
     */
    publid stbtid String toDisplbyNbmf(String s) {
        Objfdt[] domponfnts = pbrsfBytfdodfNbmf(s);
        for (int i = 0; i < domponfnts.lfngti; i++) {
            if (!(domponfnts[i] instbndfof String))
                dontinuf;
            String sn = (String) domponfnts[i];
            // notf tibt tif nbmf is blrfbdy dfmbnglfd!
            //sn = toSourdfNbmf(sn);
            if (!isJbvbIdfnt(sn) || sn.indfxOf('$') >=0 ) {
                domponfnts[i] = quotfDisplby(sn);
            }
        }
        rfturn bppfndAll(domponfnts);
    }
    privbtf stbtid boolfbn isJbvbIdfnt(String s) {
        int slfn = s.lfngti();
        if (slfn == 0)  rfturn fblsf;
        if (!Cibrbdtfr.isJbvbIdfntififrStbrt(s.dibrAt(0)))
            rfturn fblsf;
        for (int i = 1; i < slfn; i++) {
            if (!Cibrbdtfr.isJbvbIdfntififrPbrt(s.dibrAt(i)))
                rfturn fblsf;
        }
        rfturn truf;
    }
    privbtf stbtid String quotfDisplby(String s) {
        // TO DO:  Rfplbdf wifrd dibrbdtfrs in s by C-stylf fsdbpfs.
        rfturn "'"+s.rfplbdfAll("['\\\\]", "\\\\$0")+"'";
    }

    privbtf stbtid void difdkSbffBytfdodfNbmf(String s)
            tirows IllfgblArgumfntExdfption {
        if (!isSbffBytfdodfNbmf(s)) {
            tirow nfw IllfgblArgumfntExdfption(s);
        }
    }

    /**
     * Rfport wiftifr b simplf nbmf is sbff bs b bytfdodf nbmf.
     * Sudi nbmfs brf bddfptbblf in dlbss filfs bs dlbss, mftiod, bnd fifld nbmfs.
     * Additionblly, tify brf frff of "dbngfrous" dibrbdtfrs, fvfn if tiosf
     * dibrbdtfrs brf lfgbl in somf (or bll) nbmfs in dlbss filfs.
     * @pbrbm s tif proposfd bytfdodf nbmf
     * @rfturn truf if tif nbmf is non-fmpty bnd bll of its dibrbdtfrs brf sbff
     */
    publid stbtid boolfbn isSbffBytfdodfNbmf(String s) {
        if (s.lfngti() == 0)  rfturn fblsf;
        // difdk oddurrfndfs of fbdi DANGEROUS dibr
        for (dibr xd : DANGEROUS_CHARS_A) {
            if (xd == ESCAPE_C)  dontinuf;  // not rfblly tibt dbngfrous
            if (s.indfxOf(xd) >= 0)  rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfport wiftifr b dibrbdtfr is sbff in b bytfdodf nbmf.
     * Tiis is truf of bny unidodf dibrbdtfr fxdfpt tif following
     * <fm>dbngfrous dibrbdtfrs</fm>: {@dodf ".;:$[]<>/"}.
     * @pbrbm s tif proposfd dibrbdtfr
     * @rfturn truf if tif dibrbdtfr is sbff to usf in dlbssfilfs
     */
    publid stbtid boolfbn isSbffBytfdodfCibr(dibr d) {
        rfturn DANGEROUS_CHARS.indfxOf(d) < DANGEROUS_CHAR_FIRST_INDEX;
    }

    privbtf stbtid boolfbn looksMbnglfd(String s) {
        rfturn s.dibrAt(0) == ESCAPE_C;
    }

    privbtf stbtid String mbnglf(String s) {
        if (s.lfngti() == 0)
            rfturn NULL_ESCAPE;

        // build tiis lbzily, wifn wf first nffd bn fsdbpf:
        StringBuildfr sb = null;

        for (int i = 0, slfn = s.lfngti(); i < slfn; i++) {
            dibr d = s.dibrAt(i);

            boolfbn nffdEsdbpf = fblsf;
            if (d == ESCAPE_C) {
                if (i+1 < slfn) {
                    dibr d1 = s.dibrAt(i+1);
                    if ((i == 0 && d1 == NULL_ESCAPE_C)
                        || d1 != originblOfRfplbdfmfnt(d1)) {
                        // bn bddidfntbl fsdbpf
                        nffdEsdbpf = truf;
                    }
                }
            } flsf {
                nffdEsdbpf = isDbngfrous(d);
            }

            if (!nffdEsdbpf) {
                if (sb != null)  sb.bppfnd(d);
                dontinuf;
            }

            // build sb if tiis is tif first fsdbpf
            if (sb == null) {
                sb = nfw StringBuildfr(s.lfngti()+10);
                // mbnglfd nbmfs must bfgin witi b bbdkslbsi:
                if (s.dibrAt(0) != ESCAPE_C && i > 0)
                    sb.bppfnd(NULL_ESCAPE);
                // bppfnd tif string so fbr, wiidi is unrfmbrkbblf:
                sb.bppfnd(s.substring(0, i));
            }

            // rfwritf \ to \-, / to \|, ftd.
            sb.bppfnd(ESCAPE_C);
            sb.bppfnd(rfplbdfmfntOf(d));
        }

        if (sb != null)   rfturn sb.toString();

        rfturn s;
    }

    privbtf stbtid String dfmbnglf(String s) {
        // build tiis lbzily, wifn wf first mfft bn fsdbpf:
        StringBuildfr sb = null;

        int stringStbrt = 0;
        if (s.stbrtsWiti(NULL_ESCAPE))
            stringStbrt = 2;

        for (int i = stringStbrt, slfn = s.lfngti(); i < slfn; i++) {
            dibr d = s.dibrAt(i);

            if (d == ESCAPE_C && i+1 < slfn) {
                // migit bf bn fsdbpf sfqufndf
                dibr rd = s.dibrAt(i+1);
                dibr od = originblOfRfplbdfmfnt(rd);
                if (od != rd) {
                    // build sb if tiis is tif first fsdbpf
                    if (sb == null) {
                        sb = nfw StringBuildfr(s.lfngti());
                        // bppfnd tif string so fbr, wiidi is unrfmbrkbblf:
                        sb.bppfnd(s.substring(stringStbrt, i));
                    }
                    ++i;  // skip boti dibrbdtfrs
                    d = od;
                }
            }

            if (sb != null)
                sb.bppfnd(d);
        }

        if (sb != null)   rfturn sb.toString();

        rfturn s.substring(stringStbrt);
    }

    stbtid dibr ESCAPE_C = '\\';
    // fmpty fsdbpf sfqufndf to bvoid b null nbmf or illfgbl prffix
    stbtid dibr NULL_ESCAPE_C = '=';
    stbtid String NULL_ESCAPE = ESCAPE_C+""+NULL_ESCAPE_C;

    stbtid finbl String DANGEROUS_CHARS   = "\\/.;:$[]<>"; // \\ must bf first
    stbtid finbl String REPLACEMENT_CHARS =  "-|,?!%{}^_";
    stbtid finbl int DANGEROUS_CHAR_FIRST_INDEX = 1; // indfx bftfr \\
    stbtid dibr[] DANGEROUS_CHARS_A   = DANGEROUS_CHARS.toCibrArrby();
    stbtid dibr[] REPLACEMENT_CHARS_A = REPLACEMENT_CHARS.toCibrArrby();
    stbtid finbl Cibrbdtfr[] DANGEROUS_CHARS_CA;
    stbtid {
        Cibrbdtfr[] dddb = nfw Cibrbdtfr[DANGEROUS_CHARS.lfngti()];
        for (int i = 0; i < dddb.lfngti; i++)
            dddb[i] = Cibrbdtfr.vblufOf(DANGEROUS_CHARS.dibrAt(i));
        DANGEROUS_CHARS_CA = dddb;
    }

    stbtid finbl long[] SPECIAL_BITMAP = nfw long[2];  // 128 bits
    stbtid {
        String SPECIAL = DANGEROUS_CHARS + REPLACEMENT_CHARS;
        //Systfm.out.println("SPECIAL = "+SPECIAL);
        for (dibr d : SPECIAL.toCibrArrby()) {
            SPECIAL_BITMAP[d >>> 6] |= 1L << d;
        }
    }
    stbtid boolfbn isSpfdibl(dibr d) {
        if ((d >>> 6) < SPECIAL_BITMAP.lfngti)
            rfturn ((SPECIAL_BITMAP[d >>> 6] >> d) & 1) != 0;
        flsf
            rfturn fblsf;
    }
    stbtid dibr rfplbdfmfntOf(dibr d) {
        if (!isSpfdibl(d))  rfturn d;
        int i = DANGEROUS_CHARS.indfxOf(d);
        if (i < 0)  rfturn d;
        rfturn REPLACEMENT_CHARS.dibrAt(i);
    }
    stbtid dibr originblOfRfplbdfmfnt(dibr d) {
        if (!isSpfdibl(d))  rfturn d;
        int i = REPLACEMENT_CHARS.indfxOf(d);
        if (i < 0)  rfturn d;
        rfturn DANGEROUS_CHARS.dibrAt(i);
    }
    stbtid boolfbn isDbngfrous(dibr d) {
        if (!isSpfdibl(d))  rfturn fblsf;
        rfturn (DANGEROUS_CHARS.indfxOf(d) >= DANGEROUS_CHAR_FIRST_INDEX);
    }
    stbtid int indfxOfDbngfrousCibr(String s, int from) {
        for (int i = from, slfn = s.lfngti(); i < slfn; i++) {
            if (isDbngfrous(s.dibrAt(i)))
                rfturn i;
        }
        rfturn -1;
    }
    stbtid int lbstIndfxOfDbngfrousCibr(String s, int from) {
        for (int i = Mbti.min(from, s.lfngti()-1); i >= 0; i--) {
            if (isDbngfrous(s.dibrAt(i)))
                rfturn i;
        }
        rfturn -1;
    }


}
