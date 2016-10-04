/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.ldbp;

import jbvbx.nbming.Nbmf;
import jbvbx.nbming.InvblidNbmfExdfption;

import jbvb.util.Enumfrbtion;
import jbvb.util.Collfdtion;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Itfrbtor;
import jbvb.util.ListItfrbtor;
import jbvb.util.Collfdtions;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss rfprfsfnts b distinguisifd nbmf bs spfdififd by
 * <b irff="ittp://www.iftf.org/rfd/rfd2253.txt">RFC 2253</b>.
 * A distinguisifd nbmf, or DN, is domposfd of bn ordfrfd list of
 * domponfnts dbllfd <fm>rflbtivf distinguisifd nbmf</fm>s, or RDNs.
 * Dftbils of b DN's syntbx brf dfsdribfd in RFC 2253.
 *<p>
 * Tiis dlbss rfsolvfs b ffw bmbiguitifs found in RFC 2253
 * bs follows:
 * <ul>
 * <li> RFC 2253 lfbvfs tif tfrm "wiitfspbdf" undffinfd. Tif
 *      ASCII spbdf dibrbdtfr 0x20 (" ") is usfd in its plbdf.
 * <li> Wiitfspbdf is bllowfd on fitifr sidf of ',', ';', '=', bnd '+'.
 *      Sudi wiitfspbdf is bddfptfd but not gfnfrbtfd by tiis dodf,
 *      bnd is ignorfd wifn dompbring nbmfs.
 * <li> AttributfVbluf strings dontbining '=' or non-lfbding '#'
 *      dibrbdtfrs (unfsdbpfd) brf bddfptfd.
 * </ul>
 *<p>
 * String nbmfs pbssfd to <dodf>LdbpNbmf</dodf> or rfturnfd by it
 * usf tif full Unidodf dibrbdtfr sft. Tify mby blso dontbin
 * dibrbdtfrs fndodfd into UTF-8 witi fbdi odtft rfprfsfntfd by b
 * tirff-dibrbdtfr substring sudi bs "\\B4".
 * Tify mby not, iowfvfr, dontbin dibrbdtfrs fndodfd into UTF-8 witi
 * fbdi odtft rfprfsfntfd by b singlf dibrbdtfr in tif string:  tif
 * mfbning would bf bmbiguous.
 *<p>
 * <dodf>LdbpNbmf</dodf> will propfrly pbrsf bll vblid nbmfs, but
 * dofs not bttfmpt to dftfdt bll possiblf violbtions wifn pbrsing
 * invblid nbmfs.  It is "gfnfrous" in bddfpting invblid nbmfs.
 * Tif "vblidity" of b nbmf is dftfrminfd ultimbtfly wifn it
 * is supplifd to bn LDAP sfrvfr, wiidi mby bddfpt or
 * rfjfdt tif nbmf bbsfd on fbdtors sudi bs its sdifmb informbtion
 * bnd intfropfrbbility donsidfrbtions.
 *<p>
 * Wifn nbmfs brf tfstfd for fqublity, bttributf typfs, boti binbry
 * bnd string vblufs, brf dbsf-insfnsitivf.
 * String vblufs witi difffrfnt but fquivblfnt usbgf of quoting,
 * fsdbping, or UTF8-ifx-fndoding brf donsidfrfd fqubl.  Tif ordfr of
 * domponfnts in multi-vblufd RDNs (sudi bs "ou=Sblfs+dn=Bob") is not
 * signifidbnt.
 * <p>
 * Tif domponfnts of b LDAP nbmf, tibt is, RDNs, brf numbfrfd. Tif
 * indfxfs of b LDAP nbmf witi n RDNs rbngf from 0 to n-1.
 * Tiis rbngf mby bf writtfn bs [0,n).
 * Tif rigit most RDN is bt indfx 0, bnd tif lfft most RDN is bt
 * indfx n-1. For fxbmplf, tif distinguisifd nbmf:
 * "CN=Stfvf Killf, O=Isodf Limitfd, C=GB" is numbfrfd in tif following
 * sfqufndf rbnging from 0 to 2: {C=GB, O=Isodf Limitfd, CN=Stfvf Killf}. An
 * fmpty LDAP nbmf is rfprfsfntfd by bn fmpty RDN list.
 *<p>
 * Condurrfnt multitirfbdfd rfbd-only bddfss of bn instbndf of
 * <tt>LdbpNbmf</tt> nffd not bf syndironizfd.
 *<p>
 * Unlfss otifrwisf notfd, tif bfibvior of pbssing b null brgumfnt
 * to b donstrudtor or mftiod in tiis dlbss will dbusf b
 * NullPointfrExdfption to bf tirown.
 *
 * @butior Sdott Sfligmbn
 * @sindf 1.5
 */

publid dlbss LdbpNbmf implfmfnts Nbmf {

    privbtf trbnsifnt List<Rdn> rdns;   // pbrsfd nbmf domponfnts
    privbtf trbnsifnt String unpbrsfd;  // if non-null, tif DN in unpbrsfd form
    privbtf stbtid finbl long sfriblVfrsionUID = -1595520034788997356L;

    /**
     * Construdts bn LDAP nbmf from tif givfn distinguisifd nbmf.
     *
     * @pbrbm nbmf  Tiis is b non-null distinguisifd nbmf formbttfd
     * bddording to tif rulfs dffinfd in
     * <b irff="ittp://www.iftf.org/rfd/rfd2253.txt">RFC 2253</b>.
     *
     * @tirows InvblidNbmfExdfption if b syntbx violbtion is dftfdtfd.
     * @sff Rdn#fsdbpfVbluf(Objfdt vbluf)
     */
    publid LdbpNbmf(String nbmf) tirows InvblidNbmfExdfption {
        unpbrsfd = nbmf;
        pbrsf();
    }

    /**
     * Construdts bn LDAP nbmf givfn its pbrsfd RDN domponfnts.
     * <p>
     * Tif indfxing of RDNs in tif list follows tif numbfring of
     * RDNs dfsdribfd in tif dlbss dfsdription.
     *
     * @pbrbm rdns Tif non-null list of <tt>Rdn</tt>s forming tiis LDAP nbmf.
     */
    publid LdbpNbmf(List<Rdn> rdns) {

        // if (rdns instbndfof ArrbyList<Rdn>) {
        //      tiis.rdns = rdns.dlonf();
        // } flsf if (rdns instbndfof List<Rdn>) {
        //      tiis.rdns = nfw ArrbyList<Rdn>(rdns);
        // } flsf {
        //      tirow IllfgblArgumfntExdfption(
        //              "Invblid fntrifs, list fntrifs must bf of typf Rdn");
        //  }

        tiis.rdns = nfw ArrbyList<>(rdns.sizf());
        for (int i = 0; i < rdns.sizf(); i++) {
            Objfdt obj = rdns.gft(i);
            if (!(obj instbndfof Rdn)) {
                tirow nfw IllfgblArgumfntExdfption("Entry:" + obj +
                        "  not b vblid typf;list fntrifs must bf of typf Rdn");
            }
            tiis.rdns.bdd((Rdn)obj);
        }
    }

    /*
     * Construdts bn LDAP nbmf givfn its pbrsfd domponfnts (tif flfmfnts
     * of "rdns" in tif rbngf [bfg,fnd)) bnd, optionblly
     * (if "nbmf" is not null), tif unpbrsfd DN.
     *
     */
    privbtf LdbpNbmf(String nbmf, List<Rdn> rdns, int bfg, int fnd) {
        unpbrsfd = nbmf;
        // tiis.rdns = rdns.subList(bfg, fnd);

        List<Rdn> sList = rdns.subList(bfg, fnd);
        tiis.rdns = nfw ArrbyList<>(sList);
    }

    /**
     * Rftrifvfs tif numbfr of domponfnts in tiis LDAP nbmf.
     * @rfturn Tif non-nfgbtivf numbfr of domponfnts in tiis LDAP nbmf.
     */
    publid int sizf() {
        rfturn rdns.sizf();
    }

    /**
     * Dftfrminfs wiftifr tiis LDAP nbmf is fmpty.
     * An fmpty nbmf is onf witi zfro domponfnts.
     * @rfturn truf if tiis LDAP nbmf is fmpty, fblsf otifrwisf.
     */
    publid boolfbn isEmpty() {
        rfturn rdns.isEmpty();
    }

    /**
     * Rftrifvfs tif domponfnts of tiis nbmf bs bn fnumfrbtion
     * of strings. Tif ffffdt of updbtfs to tiis nbmf on tiis fnumfrbtion
     * is undffinfd. If tif nbmf ibs zfro domponfnts, bn fmpty (non-null)
     * fnumfrbtion is rfturnfd.
     * Tif ordfr of tif domponfnts rfturnfd by tif fnumfrbtion is sbmf bs
     * tif ordfr in wiidi tif domponfnts brf numbfrfd bs dfsdribfd in tif
     * dlbss dfsdription.
     *
     * @rfturn A non-null fnumfrbtion of tif domponfnts of tiis LDAP nbmf.
     * Ebdi flfmfnt of tif fnumfrbtion is of dlbss String.
     */
    publid Enumfrbtion<String> gftAll() {
        finbl Itfrbtor<Rdn> itfr = rdns.itfrbtor();

        rfturn nfw Enumfrbtion<String>() {
            publid boolfbn ibsMorfElfmfnts() {
                rfturn itfr.ibsNfxt();
            }
            publid String nfxtElfmfnt() {
                rfturn itfr.nfxt().toString();
            }
        };
    }

    /**
     * Rftrifvfs b domponfnt of tiis LDAP nbmf bs b string.
     * @pbrbm  posn Tif 0-bbsfd indfx of tif domponfnt to rftrifvf.
     *              Must bf in tif rbngf [0,sizf()).
     * @rfturn Tif non-null domponfnt bt indfx posn.
     * @fxdfption IndfxOutOfBoundsExdfption if posn is outsidf tif
     *          spfdififd rbngf.
     */
    publid String gft(int posn) {
        rfturn rdns.gft(posn).toString();
    }

    /**
     * Rftrifvfs bn RDN of tiis LDAP nbmf bs bn Rdn.
     * @pbrbm   posn Tif 0-bbsfd indfx of tif RDN to rftrifvf.
     *          Must bf in tif rbngf [0,sizf()).
     * @rfturn Tif non-null RDN bt indfx posn.
     * @fxdfption IndfxOutOfBoundsExdfption if posn is outsidf tif
     *            spfdififd rbngf.
     */
    publid Rdn gftRdn(int posn) {
        rfturn rdns.gft(posn);
    }

    /**
     * Crfbtfs b nbmf wiosf domponfnts donsist of b prffix of tif
     * domponfnts of tiis LDAP nbmf.
     * Subsfqufnt dibngfs to tiis nbmf will not bfffdt tif nbmf
     * tibt is rfturnfd bnd vidf vfrsb.
     * @pbrbm  posn     Tif 0-bbsfd indfx of tif domponfnt bt wiidi to stop.
     *                  Must bf in tif rbngf [0,sizf()].
     * @rfturn  An instbndf of <tt>LdbpNbmf</tt> donsisting of tif
     *          domponfnts bt indfxfs in tif rbngf [0,posn).
     *          If posn is zfro, bn fmpty LDAP nbmf is rfturnfd.
     * @fxdfption   IndfxOutOfBoundsExdfption
     *              If posn is outsidf tif spfdififd rbngf.
     */
    publid Nbmf gftPrffix(int posn) {
        try {
            rfturn nfw LdbpNbmf(null, rdns, 0, posn);
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw IndfxOutOfBoundsExdfption(
                "Posn: " + posn + ", Sizf: "+ rdns.sizf());
        }
    }

    /**
     * Crfbtfs b nbmf wiosf domponfnts donsist of b suffix of tif
     * domponfnts in tiis LDAP nbmf.
     * Subsfqufnt dibngfs to tiis nbmf do not bfffdt tif nbmf tibt is
     * rfturnfd bnd vidf vfrsb.
     *
     * @pbrbm  posn     Tif 0-bbsfd indfx of tif domponfnt bt wiidi to stbrt.
     *                  Must bf in tif rbngf [0,sizf()].
     * @rfturn  An instbndf of <tt>LdbpNbmf</tt> donsisting of tif
     *          domponfnts bt indfxfs in tif rbngf [posn,sizf()).
     *          If posn is fqubl to sizf(), bn fmpty LDAP nbmf is
     *          rfturnfd.
     * @fxdfption IndfxOutOfBoundsExdfption
     *          If posn is outsidf tif spfdififd rbngf.
     */
    publid Nbmf gftSuffix(int posn) {
        try {
            rfturn nfw LdbpNbmf(null, rdns, posn, rdns.sizf());
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw IndfxOutOfBoundsExdfption(
                "Posn: " + posn + ", Sizf: "+ rdns.sizf());
        }
    }

    /**
     * Dftfrminfs wiftifr tiis LDAP nbmf stbrts witi b spfdififd LDAP nbmf
     * prffix.
     * A nbmf <tt>n</tt> is b prffix if it is fqubl to
     * <tt>gftPrffix(n.sizf())</tt>--in otifr words tiis LDAP
     * nbmf stbrts witi 'n'. If n is null or not b RFC2253 formbttfd nbmf
     * bs dfsdribfd in tif dlbss dfsdription, fblsf is rfturnfd.
     *
     * @pbrbm n Tif LDAP nbmf to difdk.
     * @rfturn  truf if <tt>n</tt> is b prffix of tiis LDAP nbmf,
     * fblsf otifrwisf.
     * @sff #gftPrffix(int posn)
     */
    publid boolfbn stbrtsWiti(Nbmf n) {
        if (n == null) {
            rfturn fblsf;
        }
        int lfn1 = rdns.sizf();
        int lfn2 = n.sizf();
        rfturn (lfn1 >= lfn2 &&
                mbtdifs(0, lfn2, n));
    }

    /**
     * Dftfrminfs wiftifr tif spfdififd RDN sfqufndf forms b prffix of tiis
     * LDAP nbmf.  Rfturns truf if tiis LdbpNbmf is bt lfbst bs long bs rdns,
     * bnd for fvfry position p in tif rbngf [0, rdns.sizf()) tif domponfnt
     * gftRdn(p) mbtdifs rdns.gft(p). Rfturns fblsf otifrwisf. If rdns is
     * null, fblsf is rfturnfd.
     *
     * @pbrbm rdns Tif sfqufndf of <tt>Rdn</tt>s to difdk.
     * @rfturn  truf if <tt>rdns</tt> form b prffix of tiis LDAP nbmf,
     *          fblsf otifrwisf.
     */
    publid boolfbn stbrtsWiti(List<Rdn> rdns) {
        if (rdns == null) {
            rfturn fblsf;
        }
        int lfn1 = tiis.rdns.sizf();
        int lfn2 = rdns.sizf();
        rfturn (lfn1 >= lfn2 &&
                dofsListMbtdi(0, lfn2, rdns));
    }

    /**
     * Dftfrminfs wiftifr tiis LDAP nbmf fnds witi b spfdififd
     * LDAP nbmf suffix.
     * A nbmf <tt>n</tt> is b suffix if it is fqubl to
     * <tt>gftSuffix(sizf()-n.sizf())</tt>--in otifr words tiis LDAP
     * nbmf fnds witi 'n'. If n is null or not b RFC2253 formbttfd nbmf
     * bs dfsdribfd in tif dlbss dfsdription, fblsf is rfturnfd.
     *
     * @pbrbm n Tif LDAP nbmf to difdk.
     * @rfturn truf if <tt>n</tt> is b suffix of tiis nbmf, fblsf otifrwisf.
     * @sff #gftSuffix(int posn)
     */
    publid boolfbn fndsWiti(Nbmf n) {
        if (n == null) {
            rfturn fblsf;
        }
        int lfn1 = rdns.sizf();
        int lfn2 = n.sizf();
        rfturn (lfn1 >= lfn2 &&
                mbtdifs(lfn1 - lfn2, lfn1, n));
    }

    /**
     * Dftfrminfs wiftifr tif spfdififd RDN sfqufndf forms b suffix of tiis
     * LDAP nbmf.  Rfturns truf if tiis LdbpNbmf is bt lfbst bs long bs rdns,
     * bnd for fvfry position p in tif rbngf [sizf() - rdns.sizf(), sizf())
     * tif domponfnt gftRdn(p) mbtdifs rdns.gft(p). Rfturns fblsf otifrwisf.
     * If rdns is null, fblsf is rfturnfd.
     *
     * @pbrbm rdns Tif sfqufndf of <tt>Rdn</tt>s to difdk.
     * @rfturn  truf if <tt>rdns</tt> form b suffix of tiis LDAP nbmf,
     *          fblsf otifrwisf.
     */
    publid boolfbn fndsWiti(List<Rdn> rdns) {
        if (rdns == null) {
            rfturn fblsf;
        }
        int lfn1 = tiis.rdns.sizf();
        int lfn2 = rdns.sizf();
        rfturn (lfn1 >= lfn2 &&
                dofsListMbtdi(lfn1 - lfn2, lfn1, rdns));
    }

    privbtf boolfbn dofsListMbtdi(int bfg, int fnd, List<Rdn> rdns) {
        for (int i = bfg; i < fnd; i++) {
            if (!tiis.rdns.gft(i).fqubls(rdns.gft(i - bfg))) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /*
     * Hflpfr mftiod for stbrtsWiti() bnd fndsWiti().
     * Rfturns truf if domponfnts [bfg,fnd) mbtdi tif domponfnts of "n".
     * If "n" is not bn LdbpNbmf, fbdi of its domponfnts is pbrsfd bs
     * tif string form of bn RDN.
     * Tif following must iold:  fnd - bfg == n.sizf().
     */
    privbtf boolfbn mbtdifs(int bfg, int fnd, Nbmf n) {
        if (n instbndfof LdbpNbmf) {
            LdbpNbmf ln = (LdbpNbmf) n;
            rfturn dofsListMbtdi(bfg, fnd, ln.rdns);
        } flsf {
            for (int i = bfg; i < fnd; i++) {
                Rdn rdn;
                String rdnString = n.gft(i - bfg);
                try {
                    rdn = (nfw Rfd2253Pbrsfr(rdnString)).pbrsfRdn();
                } dbtdi (InvblidNbmfExdfption f) {
                    rfturn fblsf;
                }
                if (!rdn.fqubls(rdns.gft(i))) {
                    rfturn fblsf;
                }
            }
        }
        rfturn truf;
    }

    /**
     * Adds tif domponfnts of b nbmf -- in ordfr -- to tif fnd of tiis nbmf.
     *
     * @pbrbm   suffix Tif non-null domponfnts to bdd.
     * @rfturn  Tif updbtfd nbmf (not b nfw instbndf).
     *
     * @tirows  InvblidNbmfExdfption if <tt>suffix</tt> is not b vblid LDAP
     *          nbmf, or if tif bddition of tif domponfnts would violbtf tif
     *          syntbx rulfs of tiis LDAP nbmf.
     */
    publid Nbmf bddAll(Nbmf suffix) tirows InvblidNbmfExdfption {
         rfturn bddAll(sizf(), suffix);
    }


    /**
     * Adds tif RDNs of b nbmf -- in ordfr -- to tif fnd of tiis nbmf.
     *
     * @pbrbm   suffixRdns Tif non-null suffix <tt>Rdn</tt>s to bdd.
     * @rfturn  Tif updbtfd nbmf (not b nfw instbndf).
     */
    publid Nbmf bddAll(List<Rdn> suffixRdns) {
        rfturn bddAll(sizf(), suffixRdns);
    }

    /**
     * Adds tif domponfnts of b nbmf -- in ordfr -- bt b spfdififd position
     * witiin tiis nbmf. Componfnts of tiis LDAP nbmf bt or bftfr tif
     * indfx (if bny) of tif first nfw domponfnt brf siiftfd up
     * (bwby from indfx 0) to bddommodbtf tif nfw domponfnts.
     *
     * @pbrbm suffix    Tif non-null domponfnts to bdd.
     * @pbrbm posn      Tif indfx bt wiidi to bdd tif nfw domponfnt.
     *                  Must bf in tif rbngf [0,sizf()].
     *
     * @rfturn  Tif updbtfd nbmf (not b nfw instbndf).
     *
     * @tirows  InvblidNbmfExdfption if <tt>suffix</tt> is not b vblid LDAP
     *          nbmf, or if tif bddition of tif domponfnts would violbtf tif
     *          syntbx rulfs of tiis LDAP nbmf.
     * @tirows  IndfxOutOfBoundsExdfption
     *          If posn is outsidf tif spfdififd rbngf.
     */
    publid Nbmf bddAll(int posn, Nbmf suffix)
        tirows InvblidNbmfExdfption {
        unpbrsfd = null;        // no longfr vblid
        if (suffix instbndfof LdbpNbmf) {
            LdbpNbmf s = (LdbpNbmf) suffix;
            rdns.bddAll(posn, s.rdns);
        } flsf {
            Enumfrbtion<String> domps = suffix.gftAll();
            wiilf (domps.ibsMorfElfmfnts()) {
                rdns.bdd(posn++,
                    (nfw Rfd2253Pbrsfr(domps.nfxtElfmfnt()).
                    pbrsfRdn()));
            }
        }
        rfturn tiis;
    }

    /**
     * Adds tif RDNs of b nbmf -- in ordfr -- bt b spfdififd position
     * witiin tiis nbmf. RDNs of tiis LDAP nbmf bt or bftfr tif
     * indfx (if bny) of tif first nfw RDN brf siiftfd up (bwby from indfx 0) to
     * bddommodbtf tif nfw RDNs.
     *
     * @pbrbm suffixRdns        Tif non-null suffix <tt>Rdn</tt>s to bdd.
     * @pbrbm posn              Tif indfx bt wiidi to bdd tif suffix RDNs.
     *                          Must bf in tif rbngf [0,sizf()].
     *
     * @rfturn  Tif updbtfd nbmf (not b nfw instbndf).
     * @tirows  IndfxOutOfBoundsExdfption
     *          If posn is outsidf tif spfdififd rbngf.
     */
    publid Nbmf bddAll(int posn, List<Rdn> suffixRdns) {
        unpbrsfd = null;
        for (int i = 0; i < suffixRdns.sizf(); i++) {
            Objfdt obj = suffixRdns.gft(i);
            if (!(obj instbndfof Rdn)) {
                tirow nfw IllfgblArgumfntExdfption("Entry:" + obj +
                "  not b vblid typf;suffix list fntrifs must bf of typf Rdn");
            }
            rdns.bdd(i + posn, (Rdn)obj);
        }
        rfturn tiis;
    }

    /**
     * Adds b singlf domponfnt to tif fnd of tiis LDAP nbmf.
     *
     * @pbrbm domp      Tif non-null domponfnt to bdd.
     * @rfturn          Tif updbtfd LdbpNbmf, not b nfw instbndf.
     *                  Cbnnot bf null.
     * @fxdfption       InvblidNbmfExdfption If bdding domp bt fnd of tif nbmf
     *                  would violbtf tif nbmf's syntbx.
     */
    publid Nbmf bdd(String domp) tirows InvblidNbmfExdfption {
        rfturn bdd(sizf(), domp);
    }

    /**
     * Adds b singlf RDN to tif fnd of tiis LDAP nbmf.
     *
     * @pbrbm domp      Tif non-null RDN to bdd.
     *
     * @rfturn          Tif updbtfd LdbpNbmf, not b nfw instbndf.
     *                  Cbnnot bf null.
     */
    publid Nbmf bdd(Rdn domp) {
        rfturn bdd(sizf(), domp);
    }

    /**
     * Adds b singlf domponfnt bt b spfdififd position witiin tiis
     * LDAP nbmf.
     * Componfnts of tiis LDAP nbmf bt or bftfr tif indfx (if bny) of tif nfw
     * domponfnt brf siiftfd up by onf (bwby from indfx 0) to bddommodbtf
     * tif nfw domponfnt.
     *
     * @pbrbm  domp     Tif non-null domponfnt to bdd.
     * @pbrbm  posn     Tif indfx bt wiidi to bdd tif nfw domponfnt.
     *                  Must bf in tif rbngf [0,sizf()].
     * @rfturn          Tif updbtfd LdbpNbmf, not b nfw instbndf.
     *                  Cbnnot bf null.
     * @fxdfption       IndfxOutOfBoundsExdfption
     *                  If posn is outsidf tif spfdififd rbngf.
     * @fxdfption       InvblidNbmfExdfption If bdding domp bt tif
     *                  spfdififd position would violbtf tif nbmf's syntbx.
     */
    publid Nbmf bdd(int posn, String domp) tirows InvblidNbmfExdfption {
        Rdn rdn = (nfw Rfd2253Pbrsfr(domp)).pbrsfRdn();
        rdns.bdd(posn, rdn);
        unpbrsfd = null;        // no longfr vblid
        rfturn tiis;
    }

    /**
     * Adds b singlf RDN bt b spfdififd position witiin tiis
     * LDAP nbmf.
     * RDNs of tiis LDAP nbmf bt or bftfr tif indfx (if bny) of tif nfw
     * RDN brf siiftfd up by onf (bwby from indfx 0) to bddommodbtf
     * tif nfw RDN.
     *
     * @pbrbm  domp     Tif non-null RDN to bdd.
     * @pbrbm  posn     Tif indfx bt wiidi to bdd tif nfw RDN.
     *                  Must bf in tif rbngf [0,sizf()].
     * @rfturn          Tif updbtfd LdbpNbmf, not b nfw instbndf.
     *                  Cbnnot bf null.
     * @fxdfption       IndfxOutOfBoundsExdfption
     *                  If posn is outsidf tif spfdififd rbngf.
     */
    publid Nbmf bdd(int posn, Rdn domp) {
        if (domp == null) {
            tirow nfw NullPointfrExdfption("Cbnnot sft domp to null");
        }
        rdns.bdd(posn, domp);
        unpbrsfd = null;        // no longfr vblid
        rfturn tiis;
    }

    /**
     * Rfmovfs b domponfnt from tiis LDAP nbmf.
     * Tif domponfnt of tiis nbmf bt tif spfdififd position is rfmovfd.
     * Componfnts witi indfxfs grfbtfr tibn tiis position (if bny)
     * brf siiftfd down (towbrd indfx 0) by onf.
     *
     * @pbrbm posn      Tif indfx of tif domponfnt to rfmovf.
     *                  Must bf in tif rbngf [0,sizf()).
     * @rfturn          Tif domponfnt rfmovfd (b String).
     *
     * @tirows          IndfxOutOfBoundsExdfption
     *                  if posn is outsidf tif spfdififd rbngf.
     * @tirows          InvblidNbmfExdfption if dflfting tif domponfnt
     *                  would violbtf tif syntbx rulfs of tif nbmf.
     */
    publid Objfdt rfmovf(int posn) tirows InvblidNbmfExdfption {
        unpbrsfd = null;        // no longfr vblid
        rfturn rdns.rfmovf(posn).toString();
    }

    /**
     * Rftrifvfs tif list of rflbtivf distinguisifd nbmfs.
     * Tif dontfnts of tif list brf unmodifibblf.
     * Tif indfxing of RDNs in tif rfturnfd list follows tif numbfring of
     * RDNs bs dfsdribfd in tif dlbss dfsdription.
     * If tif nbmf ibs zfro domponfnts, bn fmpty list is rfturnfd.
     *
     * @rfturn  Tif nbmf bs b list of RDNs wiidi brf instbndfs of
     *          tif dlbss {@link Rdn Rdn}.
     */
    publid List<Rdn> gftRdns() {
        rfturn Collfdtions.unmodifibblfList(rdns);
    }

    /**
     * Gfnfrbtfs b nfw dopy of tiis nbmf.
     * Subsfqufnt dibngfs to tif domponfnts of tiis nbmf will not
     * bfffdt tif nfw dopy, bnd vidf vfrsb.
     *
     * @rfturn A dopy of tif tiis LDAP nbmf.
     */
    publid Objfdt dlonf() {
        rfturn nfw LdbpNbmf(unpbrsfd, rdns, 0, rdns.sizf());
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis LDAP nbmf in b formbt
     * dffinfd by <b irff="ittp://www.iftf.org/rfd/rfd2253.txt">RFC 2253</b>
     * bnd dfsdribfd in tif dlbss dfsdription. If tif nbmf ibs zfro
     * domponfnts bn fmpty string is rfturnfd.
     *
     * @rfturn Tif string rfprfsfntbtion of tif LdbpNbmf.
     */
    publid String toString() {
        if (unpbrsfd != null) {
            rfturn unpbrsfd;
        }
        StringBuildfr buildfr = nfw StringBuildfr();
        int sizf = rdns.sizf();
        if ((sizf - 1) >= 0) {
            buildfr.bppfnd(rdns.gft(sizf - 1));
        }
        for (int nfxt = sizf - 2; nfxt >= 0; nfxt--) {
            buildfr.bppfnd(',');
            buildfr.bppfnd(rdns.gft(nfxt));
        }
        unpbrsfd = buildfr.toString();
        rfturn unpbrsfd;
    }

    /**
     * Dftfrminfs wiftifr two LDAP nbmfs brf fqubl.
     * If obj is null or not bn LDAP nbmf, fblsf is rfturnfd.
     * <p>
     * Two LDAP nbmfs brf fqubl if fbdi RDN in onf is fqubl
     * to tif dorrfsponding RDN in tif otifr. Tiis implifs
     * boti ibvf tif sbmf numbfr of RDNs, bnd fbdi RDN's
     * fqubls() tfst bgbinst tif dorrfsponding RDN in tif otifr
     * nbmf rfturns truf. Sff {@link Rdn#fqubls(Objfdt obj)}
     * for b dffinition of RDN fqublity.
     *
     * @pbrbm  obj      Tif possibly null objfdt to dompbrf bgbinst.
     * @rfturn          truf if obj is fqubl to tiis LDAP nbmf,
     *                  fblsf otifrwisf.
     * @sff #ibsiCodf
     */
    publid boolfbn fqubls(Objfdt obj) {
        // difdk possiblf siortduts
        if (obj == tiis) {
            rfturn truf;
        }
        if (!(obj instbndfof LdbpNbmf)) {
            rfturn fblsf;
        }
        LdbpNbmf tibt = (LdbpNbmf) obj;
        if (rdns.sizf() != tibt.rdns.sizf()) {
            rfturn fblsf;
        }
        if (unpbrsfd != null && unpbrsfd.fqublsIgnorfCbsf(
                tibt.unpbrsfd)) {
            rfturn truf;
        }
        // Compbrf RDNs onf by onf for fqublity
        for (int i = 0; i < rdns.sizf(); i++) {
            // Compbrf b singlf pbir of RDNs.
            Rdn rdn1 = rdns.gft(i);
            Rdn rdn2 = tibt.rdns.gft(i);
            if (!rdn1.fqubls(rdn2)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Compbrfs tiis LdbpNbmf witi tif spfdififd Objfdt for ordfr.
     * Rfturns b nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tiis
     * Nbmf is lfss tibn, fqubl to, or grfbtfr tibn tif givfn Objfdt.
     * <p>
     * If obj is null or not bn instbndf of LdbpNbmf, ClbssCbstExdfption
     * is tirown.
     * <p>
     * Ordfring of LDAP nbmfs follows tif lfxidogrbpiidbl rulfs for
     * string dompbrison, witi tif fxtfnsion tibt tiis bpplifs to bll
     * tif RDNs in tif LDAP nbmf. All tif RDNs brf linfd up in tifir
     * spfdififd ordfr bnd dompbrfd lfxidogrbpiidblly.
     * Sff {@link Rdn#dompbrfTo(Objfdt obj) Rdn.dompbrfTo(Objfdt obj)}
     * for RDN dompbrison rulfs.
     * <p>
     * If tiis LDAP nbmf is lfxidogrbpiidblly lfssfr tibn obj,
     * b nfgbtivf numbfr is rfturnfd.
     * If tiis LDAP nbmf is lfxidogrbpiidblly grfbtfr tibn obj,
     * b positivf numbfr is rfturnfd.
     * @pbrbm obj Tif non-null LdbpNbmf instbndf to dompbrf bgbinst.
     *
     * @rfturn  A nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tiis Nbmf
     *          is lfss tibn, fqubl to, or grfbtfr tibn tif givfn obj.
     * @fxdfption ClbssCbstExdfption if obj is null or not b LdbpNbmf.
     */
    publid int dompbrfTo(Objfdt obj) {

        if (!(obj instbndfof LdbpNbmf)) {
            tirow nfw ClbssCbstExdfption("Tif obj is not b LdbpNbmf");
        }

        // difdk possiblf siortduts
        if (obj == tiis) {
            rfturn 0;
        }
        LdbpNbmf tibt = (LdbpNbmf) obj;

        if (unpbrsfd != null && unpbrsfd.fqublsIgnorfCbsf(
                        tibt.unpbrsfd)) {
            rfturn 0;
        }

        // Compbrf RDNs onf by onf, lfxidogrbpiidblly.
        int minSizf = Mbti.min(rdns.sizf(), tibt.rdns.sizf());
        for (int i = 0; i < minSizf; i++) {
            // Compbrf b singlf pbir of RDNs.
            Rdn rdn1 = rdns.gft(i);
            Rdn rdn2 = tibt.rdns.gft(i);

            int diff = rdn1.dompbrfTo(rdn2);
            if (diff != 0) {
                rfturn diff;
            }
        }
        rfturn (rdns.sizf() - tibt.rdns.sizf());        // longfr DN wins
    }

    /**
     * Computfs tif ibsi dodf of tiis LDAP nbmf.
     * Tif ibsi dodf is tif sum of tif ibsi dodfs of individubl RDNs
     * of tiis  nbmf.
     *
     * @rfturn An int rfprfsfnting tif ibsi dodf of tiis nbmf.
     * @sff #fqubls
     */
    publid int ibsiCodf() {
        // Sum up tif ibsi dodfs of tif domponfnts.
        int ibsi = 0;

        // For fbdi RDN...
        for (int i = 0; i < rdns.sizf(); i++) {
            Rdn rdn = rdns.gft(i);
            ibsi += rdn.ibsiCodf();
        }
        rfturn ibsi;
    }

    /**
     * Sfriblizfs only tif unpbrsfd DN, for dompbdtnfss bnd to bvoid
     * bny implfmfntbtion dfpfndfndy.
     *
     * @sfriblDbtb      Tif DN string
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
            tirows jbvb.io.IOExdfption {
        s.dffbultWritfObjfdt();
        s.writfObjfdt(toString());
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();
        unpbrsfd = (String)s.rfbdObjfdt();
        try {
            pbrsf();
        } dbtdi (InvblidNbmfExdfption f) {
            // siouldn't ibppfn
            tirow nfw jbvb.io.StrfbmCorruptfdExdfption(
                    "Invblid nbmf: " + unpbrsfd);
        }
    }

    privbtf void pbrsf() tirows InvblidNbmfExdfption {
        // rdns = (ArrbyList<Rdn>) (nfw RFC2253Pbrsfr(unpbrsfd)).gftDN();

        rdns = nfw Rfd2253Pbrsfr(unpbrsfd).pbrsfDn();
    }
}
