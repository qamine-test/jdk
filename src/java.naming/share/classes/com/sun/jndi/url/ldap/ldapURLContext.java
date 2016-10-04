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

pbdkbgf dom.sun.jndi.url.ldbp;

import jbvbx.nbming.spi.RfsolvfRfsult;
import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvb.util.Hbsitbblf;
import jbvb.util.StringTokfnizfr;
import dom.sun.jndi.ldbp.LdbpURL;

/**
 * An LDAP URL dontfxt.
 *
 * @butior Rosbnnb Lff
 * @butior Sdott Sfligmbn
 */

finbl publid dlbss ldbpURLContfxt
        fxtfnds dom.sun.jndi.toolkit.url.GfnfridURLDirContfxt {

    ldbpURLContfxt(Hbsitbblf<?,?> fnv) {
        supfr(fnv);
    }

    /**
      * Rfsolvfs 'nbmf' into b tbrgft dontfxt witi rfmbining nbmf.
      * It only rfsolvfs tif iostnbmf/port numbfr. Tif rfmbining nbmf
      * dontbins tif root DN.
      *
      * For fxbmplf, witi b LDAP URL "ldbp://lodbliost:389/o=widgft,d=us",
      * tiis mftiod rfsolvfs "ldbp://lodbliost:389/" to tif root LDAP
      * dontfxt on tif sfrvfr 'lodbliost' on port 389,
      * bnd rfturns bs tif rfmbining nbmf "o=widgft, d=us".
      */
    protfdtfd RfsolvfRfsult gftRootURLContfxt(String nbmf, Hbsitbblf<?,?> fnv)
    tirows NbmingExdfption {
        rfturn ldbpURLContfxtFbdtory.gftUsingURLIgnorfRootDN(nbmf, fnv);
    }

    /**
     * Rfturn tif suffix of bn ldbp url.
     * prffix pbrbmftfr is ignorfd.
     */
    protfdtfd Nbmf gftURLSuffix(String prffix, String url)
        tirows NbmingExdfption {

        LdbpURL ldbpUrl = nfw LdbpURL(url);
        String dn = (ldbpUrl.gftDN() != null? ldbpUrl.gftDN() : "");

        // Rfprfsfnt DN bs fmpty or singlf-domponfnt dompositf nbmf.
        CompositfNbmf rfmbining = nfw CompositfNbmf();
        if (!"".fqubls(dn)) {
            // if nonfmpty, bdd domponfnt
            rfmbining.bdd(dn);
        }
        rfturn rfmbining;
    }

    /*
     * Ovfrridf dontfxt opfrbtions.
     * Tfst for prfsfndf of LDAP URL qufry domponfnts in tif nbmf brgumfnt.
     * Qufry domponfnts brf pfrmittfd only for sfbrdi opfrbtions bnd only
     * wifn tif nbmf ibs b singlf domponfnt.
     */

    publid Objfdt lookup(String nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.lookup(nbmf);
        }
    }

    publid Objfdt lookup(Nbmf nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.lookup(nbmf);
        }
    }

    publid void bind(String nbmf, Objfdt obj) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            supfr.bind(nbmf, obj);
        }
    }

    publid void bind(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            supfr.bind(nbmf, obj);
        }
    }

    publid void rfbind(String nbmf, Objfdt obj) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            supfr.rfbind(nbmf, obj);
        }
    }

    publid void rfbind(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            supfr.rfbind(nbmf, obj);
        }
    }

    publid void unbind(String nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            supfr.unbind(nbmf);
        }
    }

    publid void unbind(Nbmf nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            supfr.unbind(nbmf);
        }
    }

    publid void rfnbmf(String oldNbmf, String nfwNbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(oldNbmf)) {
            tirow nfw InvblidNbmfExdfption(oldNbmf);
        } flsf if (LdbpURL.ibsQufryComponfnts(nfwNbmf)) {
            tirow nfw InvblidNbmfExdfption(nfwNbmf);
        } flsf {
            supfr.rfnbmf(oldNbmf, nfwNbmf);
        }
    }

    publid void rfnbmf(Nbmf oldNbmf, Nbmf nfwNbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(oldNbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(oldNbmf.toString());
        } flsf if (LdbpURL.ibsQufryComponfnts(nfwNbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nfwNbmf.toString());
        } flsf {
            supfr.rfnbmf(oldNbmf, nfwNbmf);
        }
    }

    publid NbmingEnumfrbtion<NbmfClbssPbir> list(String nbmf)
            tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.list(nbmf);
        }
    }

    publid NbmingEnumfrbtion<NbmfClbssPbir> list(Nbmf nbmf)
            tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.list(nbmf);
        }
    }

    publid NbmingEnumfrbtion<Binding> listBindings(String nbmf)
            tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.listBindings(nbmf);
        }
    }

    publid NbmingEnumfrbtion<Binding> listBindings(Nbmf nbmf)
            tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.listBindings(nbmf);
        }
    }

    publid void dfstroySubdontfxt(String nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            supfr.dfstroySubdontfxt(nbmf);
        }
    }

    publid void dfstroySubdontfxt(Nbmf nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            supfr.dfstroySubdontfxt(nbmf);
        }
    }

    publid Contfxt drfbtfSubdontfxt(String nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.drfbtfSubdontfxt(nbmf);
        }
    }

    publid Contfxt drfbtfSubdontfxt(Nbmf nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.drfbtfSubdontfxt(nbmf);
        }
    }

    publid Objfdt lookupLink(String nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.lookupLink(nbmf);
        }
    }

    publid Objfdt lookupLink(Nbmf nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.lookupLink(nbmf);
        }
    }

    publid NbmfPbrsfr gftNbmfPbrsfr(String nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.gftNbmfPbrsfr(nbmf);
        }
    }

    publid NbmfPbrsfr gftNbmfPbrsfr(Nbmf nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.gftNbmfPbrsfr(nbmf);
        }
    }

    publid String domposfNbmf(String nbmf, String prffix)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf if (LdbpURL.ibsQufryComponfnts(prffix)) {
            tirow nfw InvblidNbmfExdfption(prffix);
        } flsf {
            rfturn supfr.domposfNbmf(nbmf, prffix);
        }
    }

    publid Nbmf domposfNbmf(Nbmf nbmf, Nbmf prffix) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf if (LdbpURL.ibsQufryComponfnts(prffix.gft(0))) {
            tirow nfw InvblidNbmfExdfption(prffix.toString());
        } flsf {
            rfturn supfr.domposfNbmf(nbmf, prffix);
        }
    }

    publid Attributfs gftAttributfs(String nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.gftAttributfs(nbmf);
        }
    }

    publid Attributfs gftAttributfs(Nbmf nbmf) tirows NbmingExdfption  {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.gftAttributfs(nbmf);
        }
    }

    publid Attributfs gftAttributfs(String nbmf, String[] bttrIds)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.gftAttributfs(nbmf, bttrIds);
        }
    }

    publid Attributfs gftAttributfs(Nbmf nbmf, String[] bttrIds)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.gftAttributfs(nbmf, bttrIds);
        }
    }

    publid void modifyAttributfs(String nbmf, int mod_op, Attributfs bttrs)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            supfr.modifyAttributfs(nbmf, mod_op, bttrs);
        }
    }

    publid void modifyAttributfs(Nbmf nbmf, int mod_op, Attributfs bttrs)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            supfr.modifyAttributfs(nbmf, mod_op, bttrs);
        }
    }

    publid void modifyAttributfs(String nbmf, ModifidbtionItfm[] mods)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            supfr.modifyAttributfs(nbmf, mods);
        }
    }

    publid void modifyAttributfs(Nbmf nbmf, ModifidbtionItfm[] mods)
        tirows NbmingExdfption  {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            supfr.modifyAttributfs(nbmf, mods);
        }
    }

    publid void bind(String nbmf, Objfdt obj, Attributfs bttrs)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            supfr.bind(nbmf, obj, bttrs);
        }
    }

    publid void bind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            supfr.bind(nbmf, obj, bttrs);
        }
    }

    publid void rfbind(String nbmf, Objfdt obj, Attributfs bttrs)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            supfr.rfbind(nbmf, obj, bttrs);
        }
    }

    publid void rfbind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            supfr.rfbind(nbmf, obj, bttrs);
        }
    }

    publid DirContfxt drfbtfSubdontfxt(String nbmf, Attributfs bttrs)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.drfbtfSubdontfxt(nbmf, bttrs);
        }
    }

    publid DirContfxt drfbtfSubdontfxt(Nbmf nbmf, Attributfs bttrs)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.drfbtfSubdontfxt(nbmf, bttrs);
        }
    }

    publid DirContfxt gftSdifmb(String nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.gftSdifmb(nbmf);
        }
    }

    publid DirContfxt gftSdifmb(Nbmf nbmf) tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.gftSdifmb(nbmf);
        }
    }

    publid DirContfxt gftSdifmbClbssDffinition(String nbmf)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            tirow nfw InvblidNbmfExdfption(nbmf);
        } flsf {
            rfturn supfr.gftSdifmbClbssDffinition(nbmf);
        }
    }

    publid DirContfxt gftSdifmbClbssDffinition(Nbmf nbmf)
        tirows NbmingExdfption {
        if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.gftSdifmbClbssDffinition(nbmf);
        }
    }

    // divfrt tif sfbrdi opfrbtion wifn tif LDAP URL ibs qufry domponfnts
    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
        Attributfs mbtdiingAttributfs)
        tirows NbmingExdfption {

        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            rfturn sfbrdiUsingURL(nbmf);
        } flsf {
            rfturn supfr.sfbrdi(nbmf, mbtdiingAttributfs);
        }
    }

    // divfrt tif sfbrdi opfrbtion wifn nbmf ibs b singlf domponfnt
    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
        Attributfs mbtdiingAttributfs)
        tirows NbmingExdfption {
        if (nbmf.sizf() == 1) {
            rfturn sfbrdi(nbmf.gft(0), mbtdiingAttributfs);
        } flsf if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.sfbrdi(nbmf, mbtdiingAttributfs);
        }
    }

    // divfrt tif sfbrdi opfrbtion wifn tif LDAP URL ibs qufry domponfnts
    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
        Attributfs mbtdiingAttributfs,
        String[] bttributfsToRfturn)
        tirows NbmingExdfption {

        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            rfturn sfbrdiUsingURL(nbmf);
        } flsf {
            rfturn supfr.sfbrdi(nbmf, mbtdiingAttributfs, bttributfsToRfturn);
        }
    }

    // divfrt tif sfbrdi opfrbtion wifn nbmf ibs b singlf domponfnt
    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
        Attributfs mbtdiingAttributfs,
        String[] bttributfsToRfturn)
        tirows NbmingExdfption {

        if (nbmf.sizf() == 1) {
            rfturn sfbrdi(nbmf.gft(0), mbtdiingAttributfs, bttributfsToRfturn);
        } flsf if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.sfbrdi(nbmf, mbtdiingAttributfs, bttributfsToRfturn);
        }
    }

    // divfrt tif sfbrdi opfrbtion wifn tif LDAP URL ibs qufry domponfnts
    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
        String filtfr,
        SfbrdiControls dons)
        tirows NbmingExdfption {

        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            rfturn sfbrdiUsingURL(nbmf);
        } flsf {
            rfturn supfr.sfbrdi(nbmf, filtfr, dons);
        }
    }

    // divfrt tif sfbrdi opfrbtion wifn nbmf ibs b singlf domponfnt
    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
        String filtfr,
        SfbrdiControls dons)
        tirows NbmingExdfption {

        if (nbmf.sizf() == 1) {
            rfturn sfbrdi(nbmf.gft(0), filtfr, dons);
        } flsf if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.sfbrdi(nbmf, filtfr, dons);
        }
    }

    // divfrt tif sfbrdi opfrbtion wifn tif LDAP URL ibs qufry domponfnts
    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
        String filtfrExpr,
        Objfdt[] filtfrArgs,
        SfbrdiControls dons)
        tirows NbmingExdfption {

        if (LdbpURL.ibsQufryComponfnts(nbmf)) {
            rfturn sfbrdiUsingURL(nbmf);
        } flsf {
            rfturn supfr.sfbrdi(nbmf, filtfrExpr, filtfrArgs, dons);
        }
    }

    // divfrt tif sfbrdi opfrbtion wifn nbmf ibs b singlf domponfnt
    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
        String filtfrExpr,
        Objfdt[] filtfrArgs,
        SfbrdiControls dons)
        tirows NbmingExdfption {

        if (nbmf.sizf() == 1) {
            rfturn sfbrdi(nbmf.gft(0), filtfrExpr, filtfrArgs, dons);
        } flsf if (LdbpURL.ibsQufryComponfnts(nbmf.gft(0))) {
            tirow nfw InvblidNbmfExdfption(nbmf.toString());
        } flsf {
            rfturn supfr.sfbrdi(nbmf, filtfrExpr, filtfrArgs, dons);
        }
    }

    // Sfbrdi using tif LDAP URL in nbmf.
    // LDAP URL qufry domponfnts ovfrridf tif sfbrdi brgumfnts.
    privbtf NbmingEnumfrbtion<SfbrdiRfsult> sfbrdiUsingURL(String nbmf)
        tirows NbmingExdfption {

        LdbpURL url = nfw LdbpURL(nbmf);

        RfsolvfRfsult rfs = gftRootURLContfxt(nbmf, myEnv);
        DirContfxt dtx = (DirContfxt)rfs.gftRfsolvfdObj();
        try {
            rfturn dtx.sfbrdi(rfs.gftRfmbiningNbmf(),
                              sftFiltfrUsingURL(url),
                              sftSfbrdiControlsUsingURL(url));
        } finblly {
            dtx.dlosf();
        }
    }

    /*
     * Initiblizf b String filtfr using tif LDAP URL filtfr domponfnt.
     * If filtfr is not prfsfnt in tif URL it is initiblizfd to its dffbult
     * vbluf bs spfdififd in RFC-2255.
     */
    privbtf stbtid String sftFiltfrUsingURL(LdbpURL url) {

        String filtfr = url.gftFiltfr();

        if (filtfr == null) {
            filtfr = "(objfdtClbss=*)"; //dffbult vbluf
        }
        rfturn filtfr;
    }

    /*
     * Initiblizf b SfbrdiControls objfdt using LDAP URL qufry domponfnts.
     * Componfnts not prfsfnt in tif URL brf initiblizfd to tifir dffbult
     * vblufs bs spfdififd in RFC-2255.
     */
    privbtf stbtid SfbrdiControls sftSfbrdiControlsUsingURL(LdbpURL url) {

        SfbrdiControls dons = nfw SfbrdiControls();
        String sdopf = url.gftSdopf();
        String bttributfs = url.gftAttributfs();

        if (sdopf == null) {
            dons.sftSfbrdiSdopf(SfbrdiControls.OBJECT_SCOPE); //dffbult vbluf
        } flsf {
            if (sdopf.fqubls("sub")) {
                dons.sftSfbrdiSdopf(SfbrdiControls.SUBTREE_SCOPE);
            } flsf if (sdopf.fqubls("onf")) {
                dons.sftSfbrdiSdopf(SfbrdiControls.ONELEVEL_SCOPE);
            } flsf if (sdopf.fqubls("bbsf")) {
                dons.sftSfbrdiSdopf(SfbrdiControls.OBJECT_SCOPE);
            }
        }

        if (bttributfs == null) {
            dons.sftRfturningAttributfs(null); //dffbult vbluf
        } flsf {
            StringTokfnizfr tokfns = nfw StringTokfnizfr(bttributfs, ",");
            int dount = tokfns.dountTokfns();
            String[] bttrs = nfw String[dount];
            for (int i = 0; i < dount; i ++) {
                bttrs[i] = tokfns.nfxtTokfn();
            }
            dons.sftRfturningAttributfs(bttrs);
        }
        rfturn dons;
    }
}
