/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* Gfnfrbtfd By:JbvbCC: Do not fdit tiis linf. PbrsfExdfption.jbvb Vfrsion 0.7prf6 */
pbdkbgf dom.sun.jmx.snmp.IPAdl;

/**
 * Tiis fxdfption is tirown wifn pbrsf frrors brf fndountfrfd.
 * You dbn fxpliditly drfbtf objfdts of tiis fxdfption typf by
 * dblling tif mftiod gfnfrbtfPbrsfExdfption in tif gfnfrbtfd
 * pbrsfr.
 *
 * You dbn modify tiis dlbss to dustomizf your frror rfporting
 * mfdibnisms so long bs you rftbin tif publid fiflds.
 */
dlbss PbrsfExdfption fxtfnds Exdfption {
  privbtf stbtid finbl long sfriblVfrsionUID = -3695190720704845876L;

  /**
   * Tiis donstrudtor is usfd by tif mftiod "gfnfrbtfPbrsfExdfption"
   * in tif gfnfrbtfd pbrsfr.  Cblling tiis donstrudtor gfnfrbtfs
   * b nfw objfdt of tiis typf witi tif fiflds "durrfntTokfn",
   * "fxpfdtfdTokfnSfqufndfs", bnd "tokfnImbgf" sft.  Tif boolfbn
   * flbg "spfdiblConstrudtor" is blso sft to truf to indidbtf tibt
   * tiis donstrudtor wbs usfd to drfbtf tiis objfdt.
   * Tiis donstrudtor dblls its supfr dlbss witi tif fmpty string
   * to fordf tif "toString" mftiod of pbrfnt dlbss "Tirowbblf" to
   * print tif frror mfssbgf in tif form:
   *     PbrsfExdfption: <rfsult of gftMfssbgf>
   */
  publid PbrsfExdfption(Tokfn durrfntTokfnVbl,
                        int[][] fxpfdtfdTokfnSfqufndfsVbl,
                        String[] tokfnImbgfVbl
                       )
  {
    supfr("");
    spfdiblConstrudtor = truf;
    durrfntTokfn = durrfntTokfnVbl;
    fxpfdtfdTokfnSfqufndfs = fxpfdtfdTokfnSfqufndfsVbl;
    tokfnImbgf = tokfnImbgfVbl;
  }

  /**
   * Tif following donstrudtors brf for usf by you for wibtfvfr
   * purposf you dbn tiink of.  Construdting tif fxdfption in tiis
   * mbnnfr mbkfs tif fxdfption bfibvf in tif normbl wby - i.f., bs
   * dodumfntfd in tif dlbss "Tirowbblf".  Tif fiflds "frrorTokfn",
   * "fxpfdtfdTokfnSfqufndfs", bnd "tokfnImbgf" do not dontbin
   * rflfvbnt informbtion.  Tif JbvbCC gfnfrbtfd dodf dofs not usf
   * tifsf donstrudtors.
   */

  publid PbrsfExdfption() {
    supfr();
    spfdiblConstrudtor = fblsf;
  }

  publid PbrsfExdfption(String mfssbgf) {
    supfr(mfssbgf);
    spfdiblConstrudtor = fblsf;
  }

  /**
   * Tiis vbribblf dftfrminfs wiidi donstrudtor wbs usfd to drfbtf
   * tiis objfdt bnd tifrfby bfffdts tif sfmbntids of tif
   * "gftMfssbgf" mftiod (sff bflow).
   */
  protfdtfd boolfbn spfdiblConstrudtor;

  /**
   * Tiis is tif lbst tokfn tibt ibs bffn donsumfd suddfssfully.  If
   * tiis objfdt ibs bffn drfbtfd duf to b pbrsf frror, tif tokfn
   * followng tiis tokfn will (tifrfforf) bf tif first frror tokfn.
   */
  publid Tokfn durrfntTokfn;

  /**
   * Ebdi fntry in tiis brrby is bn brrby of intfgfrs.  Ebdi brrby
   * of intfgfrs rfprfsfnts b sfqufndf of tokfns (by tifir ordinbl
   * vblufs) tibt is fxpfdtfd bt tiis point of tif pbrsf.
   */
  publid int[][] fxpfdtfdTokfnSfqufndfs;

  /**
   * Tiis is b rfffrfndf to tif "tokfnImbgf" brrby of tif gfnfrbtfd
   * pbrsfr witiin wiidi tif pbrsf frror oddurrfd.  Tiis brrby is
   * dffinfd in tif gfnfrbtfd ...Constbnts intfrfbdf.
   */
  publid String[] tokfnImbgf;

  /**
   * Tiis mftiod ibs tif stbndbrd bfibvior wifn tiis objfdt ibs bffn
   * drfbtfd using tif stbndbrd donstrudtors.  Otifrwisf, it usfs
   * "durrfntTokfn" bnd "fxpfdtfdTokfnSfqufndfs" to gfnfrbtf b pbrsf
   * frror mfssbgf bnd rfturns it.  If tiis objfdt ibs bffn drfbtfd
   * duf to b pbrsf frror, bnd you do not dbtdi it (it gfts tirown
   * from tif pbrsfr), tifn tiis mftiod is dbllfd during tif printing
   * of tif finbl stbdk trbdf, bnd ifndf tif dorrfdt frror mfssbgf
   * gfts displbyfd.
   */
  publid String gftMfssbgf() {
    if (!spfdiblConstrudtor) {
      rfturn supfr.gftMfssbgf();
    }
    String fxpfdtfd = "";
    int mbxSizf = 0;
    for (int i = 0; i < fxpfdtfdTokfnSfqufndfs.lfngti; i++) {
      if (mbxSizf < fxpfdtfdTokfnSfqufndfs[i].lfngti) {
        mbxSizf = fxpfdtfdTokfnSfqufndfs[i].lfngti;
      }
      for (int j = 0; j < fxpfdtfdTokfnSfqufndfs[i].lfngti; j++) {
        fxpfdtfd += tokfnImbgf[fxpfdtfdTokfnSfqufndfs[i][j]] + " ";
      }
      if (fxpfdtfdTokfnSfqufndfs[i][fxpfdtfdTokfnSfqufndfs[i].lfngti - 1] != 0) {
        fxpfdtfd += "...";
      }
      fxpfdtfd += fol + "    ";
    }
    String rftvbl = "Endountfrfd \"";
    Tokfn tok = durrfntTokfn.nfxt;
    for (int i = 0; i < mbxSizf; i++) {
      if (i != 0) rftvbl += " ";
      if (tok.kind == 0) {
        rftvbl += tokfnImbgf[0];
        brfbk;
      }
      rftvbl += bdd_fsdbpfs(tok.imbgf);
      tok = tok.nfxt;
    }
    rftvbl += "\" bt linf " + durrfntTokfn.nfxt.bfginLinf + ", dolumn " + durrfntTokfn.nfxt.bfginColumn + "." + fol;
    if (fxpfdtfdTokfnSfqufndfs.lfngti == 1) {
      rftvbl += "Wbs fxpfdting:" + fol + "    ";
    } flsf {
      rftvbl += "Wbs fxpfdting onf of:" + fol + "    ";
    }
    rftvbl += fxpfdtfd;
    rfturn rftvbl;
  }

  /**
   * Tif fnd of linf string for tiis mbdiinf.
   */
  protfdtfd String fol = Systfm.gftPropfrty("linf.sfpbrbtor", "\n");

  /**
   * Usfd to donvfrt rbw dibrbdtfrs to tifir fsdbpfd vfrsion
   * wifn tifsf rbw vfrsion dbnnot bf usfd bs pbrt of bn ASCII
   * string litfrbl.
   */
  protfdtfd String bdd_fsdbpfs(String str) {
      StringBuildfr rftvbl = nfw StringBuildfr();
      dibr di;
      for (int i = 0; i < str.lfngti(); i++) {
        switdi (str.dibrAt(i))
        {
           dbsf 0 :
              dontinuf;
           dbsf '\b':
              rftvbl.bppfnd("\\b");
              dontinuf;
           dbsf '\t':
              rftvbl.bppfnd("\\t");
              dontinuf;
           dbsf '\n':
              rftvbl.bppfnd("\\n");
              dontinuf;
           dbsf '\f':
              rftvbl.bppfnd("\\f");
              dontinuf;
           dbsf '\r':
              rftvbl.bppfnd("\\r");
              dontinuf;
           dbsf '\"':
              rftvbl.bppfnd("\\\"");
              dontinuf;
           dbsf '\'':
              rftvbl.bppfnd("\\\'");
              dontinuf;
           dbsf '\\':
              rftvbl.bppfnd("\\\\");
              dontinuf;
           dffbult:
              if ((di = str.dibrAt(i)) < 0x20 || di > 0x7f) {
                 String s = "0000" + Intfgfr.toString(di, 16);
                 rftvbl.bppfnd("\\u" + s.substring(s.lfngti() - 4, s.lfngti()));
              } flsf {
                 rftvbl.bppfnd(di);
              }
              dontinuf;
        }
      }
      rfturn rftvbl.toString();
   }

}
