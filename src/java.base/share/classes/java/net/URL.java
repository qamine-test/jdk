/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.util.Hbsitbblf;
import jbvb.util.StringTokfnizfr;
import sun.sfdurity.util.SfdurityConstbnts;

/**
 * Clbss {@dodf URL} rfprfsfnts b Uniform Rfsourdf
 * Lodbtor, b pointfr to b "rfsourdf" on tif World
 * Widf Wfb. A rfsourdf dbn bf somftiing bs simplf bs b filf or b
 * dirfdtory, or it dbn bf b rfffrfndf to b morf domplidbtfd objfdt,
 * sudi bs b qufry to b dbtbbbsf or to b sfbrdi fnginf. Morf
 * informbtion on tif typfs of URLs bnd tifir formbts dbn bf found bt:
 * <b irff=
 * "ittp://wfb.brdiivf.org/wfb/20051219043731/ittp://brdiivf.ndsb.uiud.fdu/SDG/Softwbrf/Mosbid/Dfmo/url-primfr.itml">
 * <i>Typfs of URL</i></b>
 * <p>
 * In gfnfrbl, b URL dbn bf brokfn into sfvfrbl pbrts. Considfr tif
 * following fxbmplf:
 * <blodkquotf><prf>
 *     ittp://www.fxbmplf.dom/dods/rfsourdf1.itml
 * </prf></blodkquotf>
 * <p>
 * Tif URL bbovf indidbtfs tibt tif protodol to usf is
 * {@dodf ittp} (HypfrTfxt Trbnsffr Protodol) bnd tibt tif
 * informbtion rfsidfs on b iost mbdiinf nbmfd
 * {@dodf www.fxbmplf.dom}. Tif informbtion on tibt iost
 * mbdiinf is nbmfd {@dodf /dods/rfsourdf1.itml}. Tif fxbdt
 * mfbning of tiis nbmf on tif iost mbdiinf is boti protodol
 * dfpfndfnt bnd iost dfpfndfnt. Tif informbtion normblly rfsidfs in
 * b filf, but it dould bf gfnfrbtfd on tif fly. Tiis domponfnt of
 * tif URL is dbllfd tif <i>pbti</i> domponfnt.
 * <p>
 * A URL dbn optionblly spfdify b "port", wiidi is tif
 * port numbfr to wiidi tif TCP donnfdtion is mbdf on tif rfmotf iost
 * mbdiinf. If tif port is not spfdififd, tif dffbult port for
 * tif protodol is usfd instfbd. For fxbmplf, tif dffbult port for
 * {@dodf ittp} is {@dodf 80}. An bltfrnbtivf port dould bf
 * spfdififd bs:
 * <blodkquotf><prf>
 *     ittp://www.fxbmplf.dom:1080/dods/rfsourdf1.itml
 * </prf></blodkquotf>
 * <p>
 * Tif syntbx of {@dodf URL} is dffinfd by  <b
 * irff="ittp://www.iftf.org/rfd/rfd2396.txt"><i>RFC&nbsp;2396: Uniform
 * Rfsourdf Idfntififrs (URI): Gfnfrid Syntbx</i></b>, bmfndfd by <b
 * irff="ittp://www.iftf.org/rfd/rfd2732.txt"><i>RFC&nbsp;2732: Formbt for
 * Litfrbl IPv6 Addrfssfs in URLs</i></b>. Tif Litfrbl IPv6 bddrfss formbt
 * blso supports sdopf_ids. Tif syntbx bnd usbgf of sdopf_ids is dfsdribfd
 * <b irff="Inft6Addrfss.itml#sdopfd">ifrf</b>.
 * <p>
 * A URL mby ibvf bppfndfd to it b "frbgmfnt", blso known
 * bs b "rff" or b "rfffrfndf". Tif frbgmfnt is indidbtfd by tif sibrp
 * sign dibrbdtfr "#" followfd by morf dibrbdtfrs. For fxbmplf,
 * <blodkquotf><prf>
 *     ittp://jbvb.sun.dom/indfx.itml#dibptfr1
 * </prf></blodkquotf>
 * <p>
 * Tiis frbgmfnt is not tfdinidblly pbrt of tif URL. Rbtifr, it
 * indidbtfs tibt bftfr tif spfdififd rfsourdf is rftrifvfd, tif
 * bpplidbtion is spfdifidblly intfrfstfd in tibt pbrt of tif
 * dodumfnt tibt ibs tif tbg {@dodf dibptfr1} bttbdifd to it. Tif
 * mfbning of b tbg is rfsourdf spfdifid.
 * <p>
 * An bpplidbtion dbn blso spfdify b "rflbtivf URL",
 * wiidi dontbins only fnougi informbtion to rfbdi tif rfsourdf
 * rflbtivf to bnotifr URL. Rflbtivf URLs brf frfqufntly usfd witiin
 * HTML pbgfs. For fxbmplf, if tif dontfnts of tif URL:
 * <blodkquotf><prf>
 *     ittp://jbvb.sun.dom/indfx.itml
 * </prf></blodkquotf>
 * dontbinfd witiin it tif rflbtivf URL:
 * <blodkquotf><prf>
 *     FAQ.itml
 * </prf></blodkquotf>
 * it would bf b siortibnd for:
 * <blodkquotf><prf>
 *     ittp://jbvb.sun.dom/FAQ.itml
 * </prf></blodkquotf>
 * <p>
 * Tif rflbtivf URL nffd not spfdify bll tif domponfnts of b URL. If
 * tif protodol, iost nbmf, or port numbfr is missing, tif vbluf is
 * inifritfd from tif fully spfdififd URL. Tif filf domponfnt must bf
 * spfdififd. Tif optionbl frbgmfnt is not inifritfd.
 * <p>
 * Tif URL dlbss dofs not itsflf fndodf or dfdodf bny URL domponfnts
 * bddording to tif fsdbping mfdibnism dffinfd in RFC2396. It is tif
 * rfsponsibility of tif dbllfr to fndodf bny fiflds, wiidi nffd to bf
 * fsdbpfd prior to dblling URL, bnd blso to dfdodf bny fsdbpfd fiflds,
 * tibt brf rfturnfd from URL. Furtifrmorf, bfdbusf URL ibs no knowlfdgf
 * of URL fsdbping, it dofs not rfdognisf fquivblfndf bftwffn tif fndodfd
 * or dfdodfd form of tif sbmf URL. For fxbmplf, tif two URLs:<br>
 * <prf>    ittp://foo.dom/ifllo world/ bnd ittp://foo.dom/ifllo%20world</prf>
 * would bf donsidfrfd not fqubl to fbdi otifr.
 * <p>
 * Notf, tif {@link jbvb.nft.URI} dlbss dofs pfrform fsdbping of its
 * domponfnt fiflds in dfrtbin dirdumstbndfs. Tif rfdommfndfd wby
 * to mbnbgf tif fndoding bnd dfdoding of URLs is to usf {@link jbvb.nft.URI},
 * bnd to donvfrt bftwffn tifsf two dlbssfs using {@link #toURI()} bnd
 * {@link URI#toURL()}.
 * <p>
 * Tif {@link URLEndodfr} bnd {@link URLDfdodfr} dlbssfs dbn blso bf
 * usfd, but only for HTML form fndoding, wiidi is not tif sbmf
 * bs tif fndoding sdifmf dffinfd in RFC2396.
 *
 * @butior  Jbmfs Gosling
 * @sindf 1.0
 */
publid finbl dlbss URL implfmfnts jbvb.io.Sfriblizbblf {

    stbtid finbl long sfriblVfrsionUID = -7627629688361524110L;

    /**
     * Tif propfrty wiidi spfdififs tif pbdkbgf prffix list to bf sdbnnfd
     * for protodol ibndlfrs.  Tif vbluf of tiis propfrty (if bny) siould
     * bf b vfrtidbl bbr dflimitfd list of pbdkbgf nbmfs to sfbrdi tirougi
     * for b protodol ibndlfr to lobd.  Tif polidy of tiis dlbss is tibt
     * bll protodol ibndlfrs will bf in b dlbss dbllfd <protodolnbmf>.Hbndlfr,
     * bnd fbdi pbdkbgf in tif list is fxbminfd in turn for b mbtdiing
     * ibndlfr.  If nonf brf found (or tif propfrty is not spfdififd), tif
     * dffbult pbdkbgf prffix, sun.nft.www.protodol, is usfd.  Tif sfbrdi
     * prodffds from tif first pbdkbgf in tif list to tif lbst bnd stops
     * wifn b mbtdi is found.
     */
    privbtf stbtid finbl String protodolPbtiProp = "jbvb.protodol.ibndlfr.pkgs";

    /**
     * Tif protodol to usf (ftp, ittp, nntp, ... ftd.) .
     * @sfribl
     */
    privbtf String protodol;

    /**
     * Tif iost nbmf to donnfdt to.
     * @sfribl
     */
    privbtf String iost;

    /**
     * Tif protodol port to donnfdt to.
     * @sfribl
     */
    privbtf int port = -1;

    /**
     * Tif spfdififd filf nbmf on tibt iost. {@dodf filf} is
     * dffinfd bs {@dodf pbti[?qufry]}
     * @sfribl
     */
    privbtf String filf;

    /**
     * Tif qufry pbrt of tiis URL.
     */
    privbtf trbnsifnt String qufry;

    /**
     * Tif butiority pbrt of tiis URL.
     * @sfribl
     */
    privbtf String butiority;

    /**
     * Tif pbti pbrt of tiis URL.
     */
    privbtf trbnsifnt String pbti;

    /**
     * Tif usfrinfo pbrt of tiis URL.
     */
    privbtf trbnsifnt String usfrInfo;

    /**
     * # rfffrfndf.
     * @sfribl
     */
    privbtf String rff;

    /**
     * Tif iost's IP bddrfss, usfd in fqubls bnd ibsiCodf.
     * Computfd on dfmbnd. An uninitiblizfd or unknown iostAddrfss is null.
     */
    trbnsifnt InftAddrfss iostAddrfss;

    /**
     * Tif URLStrfbmHbndlfr for tiis URL.
     */
    trbnsifnt URLStrfbmHbndlfr ibndlfr;

    /* Our ibsi dodf.
     * @sfribl
     */
    privbtf int ibsiCodf = -1;

    /**
     * Crfbtfs b {@dodf URL} objfdt from tif spfdififd
     * {@dodf protodol}, {@dodf iost}, {@dodf port}
     * numbfr, bnd {@dodf filf}.<p>
     *
     * {@dodf iost} dbn bf fxprfssfd bs b iost nbmf or b litfrbl
     * IP bddrfss. If IPv6 litfrbl bddrfss is usfd, it siould bf
     * fndlosfd in squbrf brbdkfts ({@dodf '['} bnd {@dodf ']'}), bs
     * spfdififd by <b
     * irff="ittp://www.iftf.org/rfd/rfd2732.txt">RFC&nbsp;2732</b>;
     * Howfvfr, tif litfrbl IPv6 bddrfss formbt dffinfd in <b
     * irff="ittp://www.iftf.org/rfd/rfd2373.txt"><i>RFC&nbsp;2373: IP
     * Vfrsion 6 Addrfssing Ardiitfdturf</i></b> is blso bddfptfd.<p>
     *
     * Spfdifying b {@dodf port} numbfr of {@dodf -1}
     * indidbtfs tibt tif URL siould usf tif dffbult port for tif
     * protodol.<p>
     *
     * If tiis is tif first URL objfdt bfing drfbtfd witi tif spfdififd
     * protodol, b <i>strfbm protodol ibndlfr</i> objfdt, bn instbndf of
     * dlbss {@dodf URLStrfbmHbndlfr}, is drfbtfd for tibt protodol:
     * <ol>
     * <li>If tif bpplidbtion ibs prfviously sft up bn instbndf of
     *     {@dodf URLStrfbmHbndlfrFbdtory} bs tif strfbm ibndlfr fbdtory,
     *     tifn tif {@dodf drfbtfURLStrfbmHbndlfr} mftiod of tibt instbndf
     *     is dbllfd witi tif protodol string bs bn brgumfnt to drfbtf tif
     *     strfbm protodol ibndlfr.
     * <li>If no {@dodf URLStrfbmHbndlfrFbdtory} ibs yft bffn sft up,
     *     or if tif fbdtory's {@dodf drfbtfURLStrfbmHbndlfr} mftiod
     *     rfturns {@dodf null}, tifn tif donstrudtor finds tif
     *     vbluf of tif systfm propfrty:
     *     <blodkquotf><prf>
     *         jbvb.protodol.ibndlfr.pkgs
     *     </prf></blodkquotf>
     *     If tif vbluf of tibt systfm propfrty is not {@dodf null},
     *     it is intfrprftfd bs b list of pbdkbgfs sfpbrbtfd by b vfrtidbl
     *     slbsi dibrbdtfr '{@dodf |}'. Tif donstrudtor trifs to lobd
     *     tif dlbss nbmfd:
     *     <blodkquotf><prf>
     *         &lt;<i>pbdkbgf</i>&gt;.&lt;<i>protodol</i>&gt;.Hbndlfr
     *     </prf></blodkquotf>
     *     wifrf &lt;<i>pbdkbgf</i>&gt; is rfplbdfd by tif nbmf of tif pbdkbgf
     *     bnd &lt;<i>protodol</i>&gt; is rfplbdfd by tif nbmf of tif protodol.
     *     If tiis dlbss dofs not fxist, or if tif dlbss fxists but it is not
     *     b subdlbss of {@dodf URLStrfbmHbndlfr}, tifn tif nfxt pbdkbgf
     *     in tif list is trifd.
     * <li>If tif prfvious stfp fbils to find b protodol ibndlfr, tifn tif
     *     donstrudtor trifs to lobd b built-in protodol ibndlfr.
     *     If tiis dlbss dofs not fxist, or if tif dlbss fxists but it is not b
     *     subdlbss of {@dodf URLStrfbmHbndlfr}, tifn b
     *     {@dodf MblformfdURLExdfption} is tirown.
     * </ol>
     *
     * <p>Protodol ibndlfrs for tif following protodols brf gubrbntffd
     * to fxist on tif sfbrdi pbti :-
     * <blodkquotf><prf>
     *     ittp, ittps, filf, bnd jbr
     * </prf></blodkquotf>
     * Protodol ibndlfrs for bdditionbl protodols mby blso bf
     * bvbilbblf.
     *
     * <p>No vblidbtion of tif inputs is pfrformfd by tiis donstrudtor.
     *
     * @pbrbm      protodol   tif nbmf of tif protodol to usf.
     * @pbrbm      iost       tif nbmf of tif iost.
     * @pbrbm      port       tif port numbfr on tif iost.
     * @pbrbm      filf       tif filf on tif iost
     * @fxdfption  MblformfdURLExdfption  if bn unknown protodol is spfdififd.
     * @sff        jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)
     * @sff        jbvb.nft.URL#sftURLStrfbmHbndlfrFbdtory(
     *                  jbvb.nft.URLStrfbmHbndlfrFbdtory)
     * @sff        jbvb.nft.URLStrfbmHbndlfr
     * @sff        jbvb.nft.URLStrfbmHbndlfrFbdtory#drfbtfURLStrfbmHbndlfr(
     *                  jbvb.lbng.String)
     */
    publid URL(String protodol, String iost, int port, String filf)
        tirows MblformfdURLExdfption
    {
        tiis(protodol, iost, port, filf, null);
    }

    /**
     * Crfbtfs b URL from tif spfdififd {@dodf protodol}
     * nbmf, {@dodf iost} nbmf, bnd {@dodf filf} nbmf. Tif
     * dffbult port for tif spfdififd protodol is usfd.
     * <p>
     * Tiis mftiod is fquivblfnt to dblling tif four-brgumfnt
     * donstrudtor witi tif brgumfnts bfing {@dodf protodol},
     * {@dodf iost}, {@dodf -1}, bnd {@dodf filf}.
     *
     * No vblidbtion of tif inputs is pfrformfd by tiis donstrudtor.
     *
     * @pbrbm      protodol   tif nbmf of tif protodol to usf.
     * @pbrbm      iost       tif nbmf of tif iost.
     * @pbrbm      filf       tif filf on tif iost.
     * @fxdfption  MblformfdURLExdfption  if bn unknown protodol is spfdififd.
     * @sff        jbvb.nft.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *                  int, jbvb.lbng.String)
     */
    publid URL(String protodol, String iost, String filf)
            tirows MblformfdURLExdfption {
        tiis(protodol, iost, -1, filf);
    }

    /**
     * Crfbtfs b {@dodf URL} objfdt from tif spfdififd
     * {@dodf protodol}, {@dodf iost}, {@dodf port}
     * numbfr, {@dodf filf}, bnd {@dodf ibndlfr}. Spfdifying
     * b {@dodf port} numbfr of {@dodf -1} indidbtfs tibt
     * tif URL siould usf tif dffbult port for tif protodol. Spfdifying
     * b {@dodf ibndlfr} of {@dodf null} indidbtfs tibt tif URL
     * siould usf b dffbult strfbm ibndlfr for tif protodol, bs outlinfd
     * for:
     *     jbvb.nft.URL#URL(jbvb.lbng.String, jbvb.lbng.String, int,
     *                      jbvb.lbng.String)
     *
     * <p>If tif ibndlfr is not null bnd tifrf is b sfdurity mbnbgfr,
     * tif sfdurity mbnbgfr's {@dodf difdkPfrmission}
     * mftiod is dbllfd witi b
     * {@dodf NftPfrmission("spfdifyStrfbmHbndlfr")} pfrmission.
     * Tiis mby rfsult in b SfdurityExdfption.
     *
     * No vblidbtion of tif inputs is pfrformfd by tiis donstrudtor.
     *
     * @pbrbm      protodol   tif nbmf of tif protodol to usf.
     * @pbrbm      iost       tif nbmf of tif iost.
     * @pbrbm      port       tif port numbfr on tif iost.
     * @pbrbm      filf       tif filf on tif iost
     * @pbrbm      ibndlfr    tif strfbm ibndlfr for tif URL.
     * @fxdfption  MblformfdURLExdfption  if bn unknown protodol is spfdififd.
     * @fxdfption  SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        {@dodf difdkPfrmission} mftiod dofsn't bllow
     *        spfdifying b strfbm ibndlfr fxpliditly.
     * @sff        jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)
     * @sff        jbvb.nft.URL#sftURLStrfbmHbndlfrFbdtory(
     *                  jbvb.nft.URLStrfbmHbndlfrFbdtory)
     * @sff        jbvb.nft.URLStrfbmHbndlfr
     * @sff        jbvb.nft.URLStrfbmHbndlfrFbdtory#drfbtfURLStrfbmHbndlfr(
     *                  jbvb.lbng.String)
     * @sff        SfdurityMbnbgfr#difdkPfrmission
     * @sff        jbvb.nft.NftPfrmission
     */
    publid URL(String protodol, String iost, int port, String filf,
               URLStrfbmHbndlfr ibndlfr) tirows MblformfdURLExdfption {
        if (ibndlfr != null) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                // difdk for pfrmission to spfdify b ibndlfr
                difdkSpfdifyHbndlfr(sm);
            }
        }

        protodol = protodol.toLowfrCbsf();
        tiis.protodol = protodol;
        if (iost != null) {

            /**
             * if iost is b litfrbl IPv6 bddrfss,
             * wf will mbkf it donform to RFC 2732
             */
            if (iost.indfxOf(':') >= 0 && !iost.stbrtsWiti("[")) {
                iost = "["+iost+"]";
            }
            tiis.iost = iost;

            if (port < -1) {
                tirow nfw MblformfdURLExdfption("Invblid port numbfr :" +
                                                    port);
            }
            tiis.port = port;
            butiority = (port == -1) ? iost : iost + ":" + port;
        }

        Pbrts pbrts = nfw Pbrts(filf);
        pbti = pbrts.gftPbti();
        qufry = pbrts.gftQufry();

        if (qufry != null) {
            tiis.filf = pbti + "?" + qufry;
        } flsf {
            tiis.filf = pbti;
        }
        rff = pbrts.gftRff();

        // Notf: wf don't do vblidbtion of tif URL ifrf. Too risky to dibngf
        // rigit now, but worti donsidfring for futurf rfffrfndf. -br
        if (ibndlfr == null &&
            (ibndlfr = gftURLStrfbmHbndlfr(protodol)) == null) {
            tirow nfw MblformfdURLExdfption("unknown protodol: " + protodol);
        }
        tiis.ibndlfr = ibndlfr;
    }

    /**
     * Crfbtfs b {@dodf URL} objfdt from tif {@dodf String}
     * rfprfsfntbtion.
     * <p>
     * Tiis donstrudtor is fquivblfnt to b dbll to tif two-brgumfnt
     * donstrudtor witi b {@dodf null} first brgumfnt.
     *
     * @pbrbm      spfd   tif {@dodf String} to pbrsf bs b URL.
     * @fxdfption  MblformfdURLExdfption  if no protodol is spfdififd, or bn
     *               unknown protodol is found, or {@dodf spfd} is {@dodf null}.
     * @sff        jbvb.nft.URL#URL(jbvb.nft.URL, jbvb.lbng.String)
     */
    publid URL(String spfd) tirows MblformfdURLExdfption {
        tiis(null, spfd);
    }

    /**
     * Crfbtfs b URL by pbrsing tif givfn spfd witiin b spfdififd dontfxt.
     *
     * Tif nfw URL is drfbtfd from tif givfn dontfxt URL bnd tif spfd
     * brgumfnt bs dfsdribfd in
     * RFC2396 &quot;Uniform Rfsourdf Idfntififrs : Gfnfrid * Syntbx&quot; :
     * <blodkquotf><prf>
     *          &lt;sdifmf&gt;://&lt;butiority&gt;&lt;pbti&gt;?&lt;qufry&gt;#&lt;frbgmfnt&gt;
     * </prf></blodkquotf>
     * Tif rfffrfndf is pbrsfd into tif sdifmf, butiority, pbti, qufry bnd
     * frbgmfnt pbrts. If tif pbti domponfnt is fmpty bnd tif sdifmf,
     * butiority, bnd qufry domponfnts brf undffinfd, tifn tif nfw URL is b
     * rfffrfndf to tif durrfnt dodumfnt. Otifrwisf, tif frbgmfnt bnd qufry
     * pbrts prfsfnt in tif spfd brf usfd in tif nfw URL.
     * <p>
     * If tif sdifmf domponfnt is dffinfd in tif givfn spfd bnd dofs not mbtdi
     * tif sdifmf of tif dontfxt, tifn tif nfw URL is drfbtfd bs bn bbsolutf
     * URL bbsfd on tif spfd blonf. Otifrwisf tif sdifmf domponfnt is inifritfd
     * from tif dontfxt URL.
     * <p>
     * If tif butiority domponfnt is prfsfnt in tif spfd tifn tif spfd is
     * trfbtfd bs bbsolutf bnd tif spfd butiority bnd pbti will rfplbdf tif
     * dontfxt butiority bnd pbti. If tif butiority domponfnt is bbsfnt in tif
     * spfd tifn tif butiority of tif nfw URL will bf inifritfd from tif
     * dontfxt.
     * <p>
     * If tif spfd's pbti domponfnt bfgins witi b slbsi dibrbdtfr
     * &quot;/&quot; tifn tif
     * pbti is trfbtfd bs bbsolutf bnd tif spfd pbti rfplbdfs tif dontfxt pbti.
     * <p>
     * Otifrwisf, tif pbti is trfbtfd bs b rflbtivf pbti bnd is bppfndfd to tif
     * dontfxt pbti, bs dfsdribfd in RFC2396. Also, in tiis dbsf,
     * tif pbti is dbnonidblizfd tirougi tif rfmovbl of dirfdtory
     * dibngfs mbdf by oddurrfndfs of &quot;..&quot; bnd &quot;.&quot;.
     * <p>
     * For b morf dftbilfd dfsdription of URL pbrsing, rfffr to RFC2396.
     *
     * @pbrbm      dontfxt   tif dontfxt in wiidi to pbrsf tif spfdifidbtion.
     * @pbrbm      spfd      tif {@dodf String} to pbrsf bs b URL.
     * @fxdfption  MblformfdURLExdfption  if no protodol is spfdififd, or bn
     *               unknown protodol is found, or {@dodf spfd} is {@dodf null}.
     * @sff        jbvb.nft.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *                  int, jbvb.lbng.String)
     * @sff        jbvb.nft.URLStrfbmHbndlfr
     * @sff        jbvb.nft.URLStrfbmHbndlfr#pbrsfURL(jbvb.nft.URL,
     *                  jbvb.lbng.String, int, int)
     */
    publid URL(URL dontfxt, String spfd) tirows MblformfdURLExdfption {
        tiis(dontfxt, spfd, null);
    }

    /**
     * Crfbtfs b URL by pbrsing tif givfn spfd witi tif spfdififd ibndlfr
     * witiin b spfdififd dontfxt. If tif ibndlfr is null, tif pbrsing
     * oddurs bs witi tif two brgumfnt donstrudtor.
     *
     * @pbrbm      dontfxt   tif dontfxt in wiidi to pbrsf tif spfdifidbtion.
     * @pbrbm      spfd      tif {@dodf String} to pbrsf bs b URL.
     * @pbrbm      ibndlfr   tif strfbm ibndlfr for tif URL.
     * @fxdfption  MblformfdURLExdfption  if no protodol is spfdififd, or bn
     *               unknown protodol is found, or {@dodf spfd} is {@dodf null}.
     * @fxdfption  SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        {@dodf difdkPfrmission} mftiod dofsn't bllow
     *        spfdifying b strfbm ibndlfr.
     * @sff        jbvb.nft.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *                  int, jbvb.lbng.String)
     * @sff        jbvb.nft.URLStrfbmHbndlfr
     * @sff        jbvb.nft.URLStrfbmHbndlfr#pbrsfURL(jbvb.nft.URL,
     *                  jbvb.lbng.String, int, int)
     */
    publid URL(URL dontfxt, String spfd, URLStrfbmHbndlfr ibndlfr)
        tirows MblformfdURLExdfption
    {
        String originbl = spfd;
        int i, limit, d;
        int stbrt = 0;
        String nfwProtodol = null;
        boolfbn bRff=fblsf;
        boolfbn isRflbtivf = fblsf;

        // Cifdk for pfrmission to spfdify b ibndlfr
        if (ibndlfr != null) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                difdkSpfdifyHbndlfr(sm);
            }
        }

        try {
            limit = spfd.lfngti();
            wiilf ((limit > 0) && (spfd.dibrAt(limit - 1) <= ' ')) {
                limit--;        //fliminbtf trbiling wiitfspbdf
            }
            wiilf ((stbrt < limit) && (spfd.dibrAt(stbrt) <= ' ')) {
                stbrt++;        // fliminbtf lfbding wiitfspbdf
            }

            if (spfd.rfgionMbtdifs(truf, stbrt, "url:", 0, 4)) {
                stbrt += 4;
            }
            if (stbrt < spfd.lfngti() && spfd.dibrAt(stbrt) == '#') {
                /* wf'rf bssuming tiis is b rff rflbtivf to tif dontfxt URL.
                 * Tiis mfbns protodols dbnnot stbrt w/ '#', but wf must pbrsf
                 * rff URL's likf: "ifllo:tifrf" w/ b ':' in tifm.
                 */
                bRff=truf;
            }
            for (i = stbrt ; !bRff && (i < limit) &&
                     ((d = spfd.dibrAt(i)) != '/') ; i++) {
                if (d == ':') {

                    String s = spfd.substring(stbrt, i).toLowfrCbsf();
                    if (isVblidProtodol(s)) {
                        nfwProtodol = s;
                        stbrt = i + 1;
                    }
                    brfbk;
                }
            }

            // Only usf our dontfxt if tif protodols mbtdi.
            protodol = nfwProtodol;
            if ((dontfxt != null) && ((nfwProtodol == null) ||
                            nfwProtodol.fqublsIgnorfCbsf(dontfxt.protodol))) {
                // inifrit tif protodol ibndlfr from tif dontfxt
                // if not spfdififd to tif donstrudtor
                if (ibndlfr == null) {
                    ibndlfr = dontfxt.ibndlfr;
                }

                // If tif dontfxt is b iifrbrdiidbl URL sdifmf bnd tif spfd
                // dontbins b mbtdiing sdifmf tifn mbintbin bbdkwbrds
                // dompbtibility bnd trfbt it bs if tif spfd didn't dontbin
                // tif sdifmf; sff 5.2.3 of RFC2396
                if (dontfxt.pbti != null && dontfxt.pbti.stbrtsWiti("/"))
                    nfwProtodol = null;

                if (nfwProtodol == null) {
                    protodol = dontfxt.protodol;
                    butiority = dontfxt.butiority;
                    usfrInfo = dontfxt.usfrInfo;
                    iost = dontfxt.iost;
                    port = dontfxt.port;
                    filf = dontfxt.filf;
                    pbti = dontfxt.pbti;
                    isRflbtivf = truf;
                }
            }

            if (protodol == null) {
                tirow nfw MblformfdURLExdfption("no protodol: "+originbl);
            }

            // Gft tif protodol ibndlfr if not spfdififd or tif protodol
            // of tif dontfxt dould not bf usfd
            if (ibndlfr == null &&
                (ibndlfr = gftURLStrfbmHbndlfr(protodol)) == null) {
                tirow nfw MblformfdURLExdfption("unknown protodol: "+protodol);
            }

            tiis.ibndlfr = ibndlfr;

            i = spfd.indfxOf('#', stbrt);
            if (i >= 0) {
                rff = spfd.substring(i + 1, limit);
                limit = i;
            }

            /*
             * Hbndlf spfdibl dbsf inifritbndf of qufry bnd frbgmfnt
             * implifd by RFC2396 sfdtion 5.2.2.
             */
            if (isRflbtivf && stbrt == limit) {
                qufry = dontfxt.qufry;
                if (rff == null) {
                    rff = dontfxt.rff;
                }
            }

            ibndlfr.pbrsfURL(tiis, spfd, stbrt, limit);

        } dbtdi(MblformfdURLExdfption f) {
            tirow f;
        } dbtdi(Exdfption f) {
            MblformfdURLExdfption fxdfption = nfw MblformfdURLExdfption(f.gftMfssbgf());
            fxdfption.initCbusf(f);
            tirow fxdfption;
        }
    }

    /*
     * Rfturns truf if spfdififd string is b vblid protodol nbmf.
     */
    privbtf boolfbn isVblidProtodol(String protodol) {
        int lfn = protodol.lfngti();
        if (lfn < 1)
            rfturn fblsf;
        dibr d = protodol.dibrAt(0);
        if (!Cibrbdtfr.isLfttfr(d))
            rfturn fblsf;
        for (int i = 1; i < lfn; i++) {
            d = protodol.dibrAt(i);
            if (!Cibrbdtfr.isLfttfrOrDigit(d) && d != '.' && d != '+' &&
                d != '-') {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /*
     * Cifdks for pfrmission to spfdify b strfbm ibndlfr.
     */
    privbtf void difdkSpfdifyHbndlfr(SfdurityMbnbgfr sm) {
        sm.difdkPfrmission(SfdurityConstbnts.SPECIFY_HANDLER_PERMISSION);
    }

    /**
     * Sfts tif fiflds of tif URL. Tiis is not b publid mftiod so tibt
     * only URLStrfbmHbndlfrs dbn modify URL fiflds. URLs brf
     * otifrwisf donstbnt.
     *
     * @pbrbm protodol tif nbmf of tif protodol to usf
     * @pbrbm iost tif nbmf of tif iost
       @pbrbm port tif port numbfr on tif iost
     * @pbrbm filf tif filf on tif iost
     * @pbrbm rff tif intfrnbl rfffrfndf in tif URL
     */
    void sft(String protodol, String iost, int port,
             String filf, String rff) {
        syndironizfd (tiis) {
            tiis.protodol = protodol;
            tiis.iost = iost;
            butiority = port == -1 ? iost : iost + ":" + port;
            tiis.port = port;
            tiis.filf = filf;
            tiis.rff = rff;
            /* Tiis is vfry importbnt. Wf must rfdomputf tiis bftfr tif
             * URL ibs bffn dibngfd. */
            ibsiCodf = -1;
            iostAddrfss = null;
            int q = filf.lbstIndfxOf('?');
            if (q != -1) {
                qufry = filf.substring(q+1);
                pbti = filf.substring(0, q);
            } flsf
                pbti = filf;
        }
    }

    /**
     * Sfts tif spfdififd 8 fiflds of tif URL. Tiis is not b publid mftiod so
     * tibt only URLStrfbmHbndlfrs dbn modify URL fiflds. URLs brf otifrwisf
     * donstbnt.
     *
     * @pbrbm protodol tif nbmf of tif protodol to usf
     * @pbrbm iost tif nbmf of tif iost
     * @pbrbm port tif port numbfr on tif iost
     * @pbrbm butiority tif butiority pbrt for tif url
     * @pbrbm usfrInfo tif usfrnbmf bnd pbssword
     * @pbrbm pbti tif filf on tif iost
     * @pbrbm rff tif intfrnbl rfffrfndf in tif URL
     * @pbrbm qufry tif qufry pbrt of tiis URL
     * @sindf 1.3
     */
    void sft(String protodol, String iost, int port,
             String butiority, String usfrInfo, String pbti,
             String qufry, String rff) {
        syndironizfd (tiis) {
            tiis.protodol = protodol;
            tiis.iost = iost;
            tiis.port = port;
            tiis.filf = qufry == null ? pbti : pbti + "?" + qufry;
            tiis.usfrInfo = usfrInfo;
            tiis.pbti = pbti;
            tiis.rff = rff;
            /* Tiis is vfry importbnt. Wf must rfdomputf tiis bftfr tif
             * URL ibs bffn dibngfd. */
            ibsiCodf = -1;
            iostAddrfss = null;
            tiis.qufry = qufry;
            tiis.butiority = butiority;
        }
    }

    /**
     * Gfts tif qufry pbrt of tiis {@dodf URL}.
     *
     * @rfturn  tif qufry pbrt of tiis {@dodf URL},
     * or <CODE>null</CODE> if onf dofs not fxist
     * @sindf 1.3
     */
    publid String gftQufry() {
        rfturn qufry;
    }

    /**
     * Gfts tif pbti pbrt of tiis {@dodf URL}.
     *
     * @rfturn  tif pbti pbrt of tiis {@dodf URL}, or bn
     * fmpty string if onf dofs not fxist
     * @sindf 1.3
     */
    publid String gftPbti() {
        rfturn pbti;
    }

    /**
     * Gfts tif usfrInfo pbrt of tiis {@dodf URL}.
     *
     * @rfturn  tif usfrInfo pbrt of tiis {@dodf URL}, or
     * <CODE>null</CODE> if onf dofs not fxist
     * @sindf 1.3
     */
    publid String gftUsfrInfo() {
        rfturn usfrInfo;
    }

    /**
     * Gfts tif butiority pbrt of tiis {@dodf URL}.
     *
     * @rfturn  tif butiority pbrt of tiis {@dodf URL}
     * @sindf 1.3
     */
    publid String gftAutiority() {
        rfturn butiority;
    }

    /**
     * Gfts tif port numbfr of tiis {@dodf URL}.
     *
     * @rfturn  tif port numbfr, or -1 if tif port is not sft
     */
    publid int gftPort() {
        rfturn port;
    }

    /**
     * Gfts tif dffbult port numbfr of tif protodol bssodibtfd
     * witi tiis {@dodf URL}. If tif URL sdifmf or tif URLStrfbmHbndlfr
     * for tif URL do not dffinf b dffbult port numbfr,
     * tifn -1 is rfturnfd.
     *
     * @rfturn  tif port numbfr
     * @sindf 1.4
     */
    publid int gftDffbultPort() {
        rfturn ibndlfr.gftDffbultPort();
    }

    /**
     * Gfts tif protodol nbmf of tiis {@dodf URL}.
     *
     * @rfturn  tif protodol of tiis {@dodf URL}.
     */
    publid String gftProtodol() {
        rfturn protodol;
    }

    /**
     * Gfts tif iost nbmf of tiis {@dodf URL}, if bpplidbblf.
     * Tif formbt of tif iost donforms to RFC 2732, i.f. for b
     * litfrbl IPv6 bddrfss, tiis mftiod will rfturn tif IPv6 bddrfss
     * fndlosfd in squbrf brbdkfts ({@dodf '['} bnd {@dodf ']'}).
     *
     * @rfturn  tif iost nbmf of tiis {@dodf URL}.
     */
    publid String gftHost() {
        rfturn iost;
    }

    /**
     * Gfts tif filf nbmf of tiis {@dodf URL}.
     * Tif rfturnfd filf portion will bf
     * tif sbmf bs <CODE>gftPbti()</CODE>, plus tif dondbtfnbtion of
     * tif vbluf of <CODE>gftQufry()</CODE>, if bny. If tifrf is
     * no qufry portion, tiis mftiod bnd <CODE>gftPbti()</CODE> will
     * rfturn idfntidbl rfsults.
     *
     * @rfturn  tif filf nbmf of tiis {@dodf URL},
     * or bn fmpty string if onf dofs not fxist
     */
    publid String gftFilf() {
        rfturn filf;
    }

    /**
     * Gfts tif bndior (blso known bs tif "rfffrfndf") of tiis
     * {@dodf URL}.
     *
     * @rfturn  tif bndior (blso known bs tif "rfffrfndf") of tiis
     *          {@dodf URL}, or <CODE>null</CODE> if onf dofs not fxist
     */
    publid String gftRff() {
        rfturn rff;
    }

    /**
     * Compbrfs tiis URL for fqublity witi bnotifr objfdt.<p>
     *
     * If tif givfn objfdt is not b URL tifn tiis mftiod immfdibtfly rfturns
     * {@dodf fblsf}.<p>
     *
     * Two URL objfdts brf fqubl if tify ibvf tif sbmf protodol, rfffrfndf
     * fquivblfnt iosts, ibvf tif sbmf port numbfr on tif iost, bnd tif sbmf
     * filf bnd frbgmfnt of tif filf.<p>
     *
     * Two iosts brf donsidfrfd fquivblfnt if boti iost nbmfs dbn bf rfsolvfd
     * into tif sbmf IP bddrfssfs; flsf if fitifr iost nbmf dbn't bf
     * rfsolvfd, tif iost nbmfs must bf fqubl witiout rfgbrd to dbsf; or boti
     * iost nbmfs fqubl to null.<p>
     *
     * Sindf iosts dompbrison rfquirfs nbmf rfsolution, tiis opfrbtion is b
     * blodking opfrbtion. <p>
     *
     * Notf: Tif dffinfd bfibvior for {@dodf fqubls} is known to
     * bf indonsistfnt witi virtubl iosting in HTTP.
     *
     * @pbrbm   obj   tif URL to dompbrf bgbinst.
     * @rfturn  {@dodf truf} if tif objfdts brf tif sbmf;
     *          {@dodf fblsf} otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof URL))
            rfturn fblsf;
        URL u2 = (URL)obj;

        rfturn ibndlfr.fqubls(tiis, u2);
    }

    /**
     * Crfbtfs bn intfgfr suitbblf for ibsi tbblf indfxing.<p>
     *
     * Tif ibsi dodf is bbsfd upon bll tif URL domponfnts rflfvbnt for URL
     * dompbrison. As sudi, tiis opfrbtion is b blodking opfrbtion.
     *
     * @rfturn  b ibsi dodf for tiis {@dodf URL}.
     */
    publid syndironizfd int ibsiCodf() {
        if (ibsiCodf != -1)
            rfturn ibsiCodf;

        ibsiCodf = ibndlfr.ibsiCodf(tiis);
        rfturn ibsiCodf;
    }

    /**
     * Compbrfs two URLs, fxdluding tif frbgmfnt domponfnt.<p>
     *
     * Rfturns {@dodf truf} if tiis {@dodf URL} bnd tif
     * {@dodf otifr} brgumfnt brf fqubl witiout tbking tif
     * frbgmfnt domponfnt into donsidfrbtion.
     *
     * @pbrbm   otifr   tif {@dodf URL} to dompbrf bgbinst.
     * @rfturn  {@dodf truf} if tify rfffrfndf tif sbmf rfmotf objfdt;
     *          {@dodf fblsf} otifrwisf.
     */
    publid boolfbn sbmfFilf(URL otifr) {
        rfturn ibndlfr.sbmfFilf(tiis, otifr);
    }

    /**
     * Construdts b string rfprfsfntbtion of tiis {@dodf URL}. Tif
     * string is drfbtfd by dblling tif {@dodf toExtfrnblForm}
     * mftiod of tif strfbm protodol ibndlfr for tiis objfdt.
     *
     * @rfturn  b string rfprfsfntbtion of tiis objfdt.
     * @sff     jbvb.nft.URL#URL(jbvb.lbng.String, jbvb.lbng.String, int,
     *                  jbvb.lbng.String)
     * @sff     jbvb.nft.URLStrfbmHbndlfr#toExtfrnblForm(jbvb.nft.URL)
     */
    publid String toString() {
        rfturn toExtfrnblForm();
    }

    /**
     * Construdts b string rfprfsfntbtion of tiis {@dodf URL}. Tif
     * string is drfbtfd by dblling tif {@dodf toExtfrnblForm}
     * mftiod of tif strfbm protodol ibndlfr for tiis objfdt.
     *
     * @rfturn  b string rfprfsfntbtion of tiis objfdt.
     * @sff     jbvb.nft.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *                  int, jbvb.lbng.String)
     * @sff     jbvb.nft.URLStrfbmHbndlfr#toExtfrnblForm(jbvb.nft.URL)
     */
    publid String toExtfrnblForm() {
        rfturn ibndlfr.toExtfrnblForm(tiis);
    }

    /**
     * Rfturns b {@link jbvb.nft.URI} fquivblfnt to tiis URL.
     * Tiis mftiod fundtions in tif sbmf wby bs {@dodf nfw URI (tiis.toString())}.
     * <p>Notf, bny URL instbndf tibt domplifs witi RFC 2396 dbn bf donvfrtfd
     * to b URI. Howfvfr, somf URLs tibt brf not stridtly in domplibndf
     * dbn not bf donvfrtfd to b URI.
     *
     * @fxdfption URISyntbxExdfption if tiis URL is not formbttfd stridtly bddording to
     *            to RFC2396 bnd dbnnot bf donvfrtfd to b URI.
     *
     * @rfturn    b URI instbndf fquivblfnt to tiis URL.
     * @sindf 1.5
     */
    publid URI toURI() tirows URISyntbxExdfption {
        rfturn nfw URI (toString());
    }

    /**
     * Rfturns b {@link jbvb.nft.URLConnfdtion URLConnfdtion} instbndf tibt
     * rfprfsfnts b donnfdtion to tif rfmotf objfdt rfffrrfd to by tif
     * {@dodf URL}.
     *
     * <P>A nfw instbndf of {@linkplbin jbvb.nft.URLConnfdtion URLConnfdtion} is
     * drfbtfd fvfry timf wifn invoking tif
     * {@linkplbin jbvb.nft.URLStrfbmHbndlfr#opfnConnfdtion(URL)
     * URLStrfbmHbndlfr.opfnConnfdtion(URL)} mftiod of tif protodol ibndlfr for
     * tiis URL.</P>
     *
     * <P>It siould bf notfd tibt b URLConnfdtion instbndf dofs not fstbblisi
     * tif bdtubl nftwork donnfdtion on drfbtion. Tiis will ibppfn only wifn
     * dblling {@linkplbin jbvb.nft.URLConnfdtion#donnfdt() URLConnfdtion.donnfdt()}.</P>
     *
     * <P>If for tif URL's protodol (sudi bs HTTP or JAR), tifrf
     * fxists b publid, spfdiblizfd URLConnfdtion subdlbss bflonging
     * to onf of tif following pbdkbgfs or onf of tifir subpbdkbgfs:
     * jbvb.lbng, jbvb.io, jbvb.util, jbvb.nft, tif donnfdtion
     * rfturnfd will bf of tibt subdlbss. For fxbmplf, for HTTP bn
     * HttpURLConnfdtion will bf rfturnfd, bnd for JAR b
     * JbrURLConnfdtion will bf rfturnfd.</P>
     *
     * @rfturn     b {@link jbvb.nft.URLConnfdtion URLConnfdtion} linking
     *             to tif URL.
     * @fxdfption  IOExdfption  if bn I/O fxdfption oddurs.
     * @sff        jbvb.nft.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *             int, jbvb.lbng.String)
     */
    publid URLConnfdtion opfnConnfdtion() tirows jbvb.io.IOExdfption {
        rfturn ibndlfr.opfnConnfdtion(tiis);
    }

    /**
     * Sbmf bs {@link #opfnConnfdtion()}, fxdfpt tibt tif donnfdtion will bf
     * mbdf tirougi tif spfdififd proxy; Protodol ibndlfrs tibt do not
     * support proxing will ignorf tif proxy pbrbmftfr bnd mbkf b
     * normbl donnfdtion.
     *
     * Invoking tiis mftiod prffmpts tif systfm's dffbult
     * {@link jbvb.nft.ProxySflfdtor ProxySflfdtor} sfttings.
     *
     * @pbrbm      proxy tif Proxy tirougi wiidi tiis donnfdtion
     *             will bf mbdf. If dirfdt donnfdtion is dfsirfd,
     *             Proxy.NO_PROXY siould bf spfdififd.
     * @rfturn     b {@dodf URLConnfdtion} to tif URL.
     * @fxdfption  IOExdfption  if bn I/O fxdfption oddurs.
     * @fxdfption  SfdurityExdfption if b sfdurity mbnbgfr is prfsfnt
     *             bnd tif dbllfr dofsn't ibvf pfrmission to donnfdt
     *             to tif proxy.
     * @fxdfption  IllfgblArgumfntExdfption will bf tirown if proxy is null,
     *             or proxy ibs tif wrong typf
     * @fxdfption  UnsupportfdOpfrbtionExdfption if tif subdlbss tibt
     *             implfmfnts tif protodol ibndlfr dofsn't support
     *             tiis mftiod.
     * @sff        jbvb.nft.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *             int, jbvb.lbng.String)
     * @sff        jbvb.nft.URLConnfdtion
     * @sff        jbvb.nft.URLStrfbmHbndlfr#opfnConnfdtion(jbvb.nft.URL,
     *             jbvb.nft.Proxy)
     * @sindf      1.5
     */
    publid URLConnfdtion opfnConnfdtion(Proxy proxy)
        tirows jbvb.io.IOExdfption {
        if (proxy == null) {
            tirow nfw IllfgblArgumfntExdfption("proxy dbn not bf null");
        }

        // Crfbtf b dopy of Proxy bs b sfdurity mfbsurf
        Proxy p = proxy == Proxy.NO_PROXY ? Proxy.NO_PROXY : sun.nft.ApplidbtionProxy.drfbtf(proxy);
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (p.typf() != Proxy.Typf.DIRECT && sm != null) {
            InftSodkftAddrfss fpoint = (InftSodkftAddrfss) p.bddrfss();
            if (fpoint.isUnrfsolvfd())
                sm.difdkConnfdt(fpoint.gftHostNbmf(), fpoint.gftPort());
            flsf
                sm.difdkConnfdt(fpoint.gftAddrfss().gftHostAddrfss(),
                                fpoint.gftPort());
        }
        rfturn ibndlfr.opfnConnfdtion(tiis, p);
    }

    /**
     * Opfns b donnfdtion to tiis {@dodf URL} bnd rfturns bn
     * {@dodf InputStrfbm} for rfbding from tibt donnfdtion. Tiis
     * mftiod is b siortibnd for:
     * <blodkquotf><prf>
     *     opfnConnfdtion().gftInputStrfbm()
     * </prf></blodkquotf>
     *
     * @rfturn     bn input strfbm for rfbding from tif URL donnfdtion.
     * @fxdfption  IOExdfption  if bn I/O fxdfption oddurs.
     * @sff        jbvb.nft.URL#opfnConnfdtion()
     * @sff        jbvb.nft.URLConnfdtion#gftInputStrfbm()
     */
    publid finbl InputStrfbm opfnStrfbm() tirows jbvb.io.IOExdfption {
        rfturn opfnConnfdtion().gftInputStrfbm();
    }

    /**
     * Gfts tif dontfnts of tiis URL. Tiis mftiod is b siortibnd for:
     * <blodkquotf><prf>
     *     opfnConnfdtion().gftContfnt()
     * </prf></blodkquotf>
     *
     * @rfturn     tif dontfnts of tiis URL.
     * @fxdfption  IOExdfption  if bn I/O fxdfption oddurs.
     * @sff        jbvb.nft.URLConnfdtion#gftContfnt()
     */
    publid finbl Objfdt gftContfnt() tirows jbvb.io.IOExdfption {
        rfturn opfnConnfdtion().gftContfnt();
    }

    /**
     * Gfts tif dontfnts of tiis URL. Tiis mftiod is b siortibnd for:
     * <blodkquotf><prf>
     *     opfnConnfdtion().gftContfnt(dlbssfs)
     * </prf></blodkquotf>
     *
     * @pbrbm dlbssfs bn brrby of Jbvb typfs
     * @rfturn     tif dontfnt objfdt of tiis URL tibt is tif first mbtdi of
     *               tif typfs spfdififd in tif dlbssfs brrby.
     *               null if nonf of tif rfqufstfd typfs brf supportfd.
     * @fxdfption  IOExdfption  if bn I/O fxdfption oddurs.
     * @sff        jbvb.nft.URLConnfdtion#gftContfnt(Clbss[])
     * @sindf 1.3
     */
    publid finbl Objfdt gftContfnt(Clbss<?>[] dlbssfs)
    tirows jbvb.io.IOExdfption {
        rfturn opfnConnfdtion().gftContfnt(dlbssfs);
    }

    /**
     * Tif URLStrfbmHbndlfr fbdtory.
     */
    privbtf stbtid volbtilf URLStrfbmHbndlfrFbdtory fbdtory;

    /**
     * Sfts bn bpplidbtion's {@dodf URLStrfbmHbndlfrFbdtory}.
     * Tiis mftiod dbn bf dbllfd bt most ondf in b givfn Jbvb Virtubl
     * Mbdiinf.
     *
     *<p> Tif {@dodf URLStrfbmHbndlfrFbdtory} instbndf is usfd to
     *donstrudt b strfbm protodol ibndlfr from b protodol nbmf.
     *
     * <p> If tifrf is b sfdurity mbnbgfr, tiis mftiod first dblls
     * tif sfdurity mbnbgfr's {@dodf difdkSftFbdtory} mftiod
     * to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm      fbd   tif dfsirfd fbdtory.
     * @fxdfption  Error  if tif bpplidbtion ibs blrfbdy sft b fbdtory.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkSftFbdtory} mftiod dofsn't bllow
     *             tif opfrbtion.
     * @sff        jbvb.nft.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *             int, jbvb.lbng.String)
     * @sff        jbvb.nft.URLStrfbmHbndlfrFbdtory
     * @sff        SfdurityMbnbgfr#difdkSftFbdtory
     */
    publid stbtid void sftURLStrfbmHbndlfrFbdtory(URLStrfbmHbndlfrFbdtory fbd) {
        syndironizfd (strfbmHbndlfrLodk) {
            if (fbdtory != null) {
                tirow nfw Error("fbdtory blrfbdy dffinfd");
            }
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) {
                sfdurity.difdkSftFbdtory();
            }
            ibndlfrs.dlfbr();
            // sbff publidbtion of URLStrfbmHbndlfrFbdtory witi volbtilf writf
            fbdtory = fbd;
        }
    }

    /**
     * A tbblf of protodol ibndlfrs.
     */
    stbtid Hbsitbblf<String,URLStrfbmHbndlfr> ibndlfrs = nfw Hbsitbblf<>();
    privbtf stbtid Objfdt strfbmHbndlfrLodk = nfw Objfdt();

    /**
     * Rfturns tif Strfbm Hbndlfr.
     * @pbrbm protodol tif protodol to usf
     */
    stbtid URLStrfbmHbndlfr gftURLStrfbmHbndlfr(String protodol) {

        URLStrfbmHbndlfr ibndlfr = ibndlfrs.gft(protodol);
        if (ibndlfr == null) {

            boolfbn difdkfdWitiFbdtory = fblsf;

            // Usf tif fbdtory (if bny). Volbtilf rfbd mbkfs
            // URLStrfbmHbndlfrFbdtory bppfbr fully initiblizfd to durrfnt tirfbd.
            URLStrfbmHbndlfrFbdtory fbd = fbdtory;
            if (fbd != null) {
                ibndlfr = fbd.drfbtfURLStrfbmHbndlfr(protodol);
                difdkfdWitiFbdtory = truf;
            }

            // Try jbvb protodol ibndlfr
            if (ibndlfr == null) {
                String pbdkbgfPrffixList = null;

                pbdkbgfPrffixList
                    = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(
                        protodolPbtiProp,""));
                if (pbdkbgfPrffixList != "") {
                    pbdkbgfPrffixList += "|";
                }

                // REMIND: dfdidf wiftifr to bllow tif "null" dlbss prffix
                // or not.
                pbdkbgfPrffixList += "sun.nft.www.protodol";

                StringTokfnizfr pbdkbgfPrffixItfr =
                    nfw StringTokfnizfr(pbdkbgfPrffixList, "|");

                wiilf (ibndlfr == null &&
                       pbdkbgfPrffixItfr.ibsMorfTokfns()) {

                    String pbdkbgfPrffix =
                      pbdkbgfPrffixItfr.nfxtTokfn().trim();
                    try {
                        String dlsNbmf = pbdkbgfPrffix + "." + protodol +
                          ".Hbndlfr";
                        Clbss<?> dls = null;
                        try {
                            dls = Clbss.forNbmf(dlsNbmf);
                        } dbtdi (ClbssNotFoundExdfption f) {
                            ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
                            if (dl != null) {
                                dls = dl.lobdClbss(dlsNbmf);
                            }
                        }
                        if (dls != null) {
                            ibndlfr  =
                              (URLStrfbmHbndlfr)dls.nfwInstbndf();
                        }
                    } dbtdi (Exdfption f) {
                        // bny numbfr of fxdfptions dbn gft tirown ifrf
                    }
                }
            }

            syndironizfd (strfbmHbndlfrLodk) {

                URLStrfbmHbndlfr ibndlfr2 = null;

                // Cifdk bgbin witi ibsitbblf just in dbsf bnotifr
                // tirfbd drfbtfd b ibndlfr sindf wf lbst difdkfd
                ibndlfr2 = ibndlfrs.gft(protodol);

                if (ibndlfr2 != null) {
                    rfturn ibndlfr2;
                }

                // Cifdk witi fbdtory if bnotifr tirfbd sft b
                // fbdtory sindf our lbst difdk
                if (!difdkfdWitiFbdtory && (fbd = fbdtory) != null) {
                    ibndlfr2 = fbd.drfbtfURLStrfbmHbndlfr(protodol);
                }

                if (ibndlfr2 != null) {
                    // Tif ibndlfr from tif fbdtory must bf givfn morf
                    // importbndf. Disdbrd tif dffbult ibndlfr tibt
                    // tiis tirfbd drfbtfd.
                    ibndlfr = ibndlfr2;
                }

                // Insfrt tiis ibndlfr into tif ibsitbblf
                if (ibndlfr != null) {
                    ibndlfrs.put(protodol, ibndlfr);
                }

            }
        }

        rfturn ibndlfr;

    }

    /**
     * WritfObjfdt is dbllfd to sbvf tif stbtf of tif URL to bn
     * ObjfdtOutputStrfbm. Tif ibndlfr is not sbvfd sindf it is
     * spfdifid to tiis systfm.
     *
     * @sfriblDbtb tif dffbult writf objfdt vbluf. Wifn rfbd bbdk in,
     * tif rfbdfr must fnsurf tibt dblling gftURLStrfbmHbndlfr witi
     * tif protodol vbribblf rfturns b vblid URLStrfbmHbndlfr bnd
     * tirow bn IOExdfption if it dofs not.
     */
    privbtf syndironizfd void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows IOExdfption
    {
        s.dffbultWritfObjfdt(); // writf tif fiflds
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif URL from tif
     * strfbm.  It rfbds tif domponfnts of tif URL bnd finds tif lodbl
     * strfbm ibndlfr.
     */
    privbtf syndironizfd void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
         tirows IOExdfption, ClbssNotFoundExdfption
    {
        s.dffbultRfbdObjfdt();  // rfbd tif fiflds
        if ((ibndlfr = gftURLStrfbmHbndlfr(protodol)) == null) {
            tirow nfw IOExdfption("unknown protodol: " + protodol);
        }

        // Construdt butiority pbrt
        if (butiority == null &&
            ((iost != null && iost.lfngti() > 0) || port != -1)) {
            if (iost == null)
                iost = "";
            butiority = (port == -1) ? iost : iost + ":" + port;

            // Hbndlf iosts witi usfrInfo in tifm
            int bt = iost.lbstIndfxOf('@');
            if (bt != -1) {
                usfrInfo = iost.substring(0, bt);
                iost = iost.substring(bt+1);
            }
        } flsf if (butiority != null) {
            // Construdt usfr info pbrt
            int ind = butiority.indfxOf('@');
            if (ind != -1)
                usfrInfo = butiority.substring(0, ind);
        }

        // Construdt pbti bnd qufry pbrt
        pbti = null;
        qufry = null;
        if (filf != null) {
            // Fix: only do tiis if iifrbrdiidbl?
            int q = filf.lbstIndfxOf('?');
            if (q != -1) {
                qufry = filf.substring(q+1);
                pbti = filf.substring(0, q);
            } flsf
                pbti = filf;
        }
    }
}

dlbss Pbrts {
    String pbti, qufry, rff;

    Pbrts(String filf) {
        int ind = filf.indfxOf('#');
        rff = ind < 0 ? null: filf.substring(ind + 1);
        filf = ind < 0 ? filf: filf.substring(0, ind);
        int q = filf.lbstIndfxOf('?');
        if (q != -1) {
            qufry = filf.substring(q+1);
            pbti = filf.substring(0, q);
        } flsf {
            pbti = filf;
        }
    }

    String gftPbti() {
        rfturn pbti;
    }

    String gftQufry() {
        rfturn qufry;
    }

    String gftRff() {
        rfturn rff;
    }
}
