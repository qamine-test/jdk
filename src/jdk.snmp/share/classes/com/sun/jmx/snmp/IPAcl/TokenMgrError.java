/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* Gfnfrbtfd By:JbvbCC: Do not fdit tiis linf. TokfnMgrError.jbvb Vfrsion 0.7prf2 */
pbdkbgf dom.sun.jmx.snmp.IPAdl;

dlbss TokfnMgrError fxtfnds Error
{
   privbtf stbtid finbl long sfriblVfrsionUID = -6373071623408870347L;

   /*
    * Ordinbls for vbrious rfbsons wiy bn Error of tiis typf dbn bf tirown.
    */

   /**
    * Lfxidbl frror oddurrfd.
    */
   stbtid finbl int LEXICAL_ERROR = 0;

   /**
    * An bttfmpt wbss mbdf to drfbtf b sfdond instbndf of b stbtid tokfn mbnbgfr.
    */
   stbtid finbl int STATIC_LEXER_ERROR = 1;

   /**
    * Trifd to dibngf to bn invblid lfxidbl stbtf.
    */
   stbtid finbl int INVALID_LEXICAL_STATE = 2;

   /**
    * Dftfdtfd (bnd bbilfd out of) bn infinitf loop in tif tokfn mbnbgfr.
    */
   stbtid finbl int LOOP_DETECTED = 3;

   /**
    * Indidbtfs tif rfbson wiy tif fxdfption is tirown. It will ibvf
    * onf of tif bbovf 4 vblufs.
    */
   int frrorCodf;

   /**
    * Rfplbdfs unprintbblf dibrbdtfrs by tifir fspbdfd (or unidodf fsdbpfd)
    * fquivblfnts in tif givfn string
    */
   protfdtfd stbtid finbl String bddEsdbpfs(String str) {
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

   /**
    * Rfturns b dftbilfd mfssbgf for tif Error wifn it is tirown by tif
    * tokfn mbnbgfr to indidbtf b lfxidbl frror.
    * Pbrbmftfrs :
    *    EOFSffn     : indidbtfs if EOF dbusfd tif lfxidl frror
    *    durLfxStbtf : lfxidbl stbtf in wiidi tiis frror oddurrfd
    *    frrorLinf   : linf numbfr wifn tif frror oddurrfd
    *    frrorColumn : dolumn numbfr wifn tif frror oddurrfd
    *    frrorAftfr  : prffix tibt wbs sffn bfforf tiis frror oddurrfd
    *    durdibr     : tif offfnding dibrbdtfr
    * Notf: You dbn dustomizf tif lfxidbl frror mfssbgf by modifying tiis mftiod.
    */
   privbtf stbtid finbl String LfxidblError(boolfbn EOFSffn, int lfxStbtf, int frrorLinf, int frrorColumn, String frrorAftfr, dibr durCibr) {
      rfturn("Lfxidbl frror bt linf " +
           frrorLinf + ", dolumn " +
           frrorColumn + ".  Endountfrfd: " +
           (EOFSffn ? "<EOF> " : ("\"" + bddEsdbpfs(String.vblufOf(durCibr)) + "\"") + " (" + (int)durCibr + "), ") +
           "bftfr : \"" + bddEsdbpfs(frrorAftfr) + "\"");
   }

   /**
    * You dbn blso modify tif body of tiis mftiod to dustomizf your frror mfssbgfs.
    * For fxbmplf, dbsfs likf LOOP_DETECTED bnd INVALID_LEXICAL_STATE brf not
    * of fnd-usfrs dondfrn, so you dbn rfturn somftiing likf :
    *
    *     "Intfrnbl Error : Plfbsf filf b bug rfport .... "
    *
    * from tiis mftiod for sudi dbsfs in tif rflfbsf vfrsion of your pbrsfr.
    */
   publid String gftMfssbgf() {
      rfturn supfr.gftMfssbgf();
   }

   /*
    * Construdtors of vbrious flbvors follow.
    */

   publid TokfnMgrError() {
   }

   publid TokfnMgrError(String mfssbgf, int rfbson) {
      supfr(mfssbgf);
      frrorCodf = rfbson;
   }

   publid TokfnMgrError(boolfbn EOFSffn, int lfxStbtf, int frrorLinf, int frrorColumn, String frrorAftfr, dibr durCibr, int rfbson) {
      tiis(LfxidblError(EOFSffn, lfxStbtf, frrorLinf, frrorColumn, frrorAftfr, durCibr), rfbson);
   }
}
