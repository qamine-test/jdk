/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp;




/**
 * Tif <CODE>BfrDfdodfr</CODE> dlbss is usfd for dfdoding
 * BER-fndodfd dbtb.
 *
 * A <CODE>BfrDfdodfr</CODE> nffds to bf sft up witi tif bytf string dontbining
 * tif fndoding. It mbintbins b durrfnt position in tif bytf string.
 *
 * Mftiods bllows to fftdi intfgfr, string, OID, ftd., from tif durrfnt
 * position. Aftfr b fftdi tif durrfnt position is movfd forwbrd.
 *
 * A fftdi tirows b <CODE>BfrExdfption</CODE> if tif fndoding is not of tif
 * fxpfdtfd typf.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 *
 * @sindf 1.5
 */

publid dlbss BfrDfdodfr {

  /**
  * Construdts b nfw dfdodfr bnd bttbdifs it to tif spfdififd bytf string.
  *
  * @pbrbm b Tif bytf string dontbining tif fndodfd dbtb.
  */

  publid BfrDfdodfr(bytf b[]) {
    bytfs = b ;
    rfsft() ;
  }

  publid void rfsft() {
    nfxt = 0 ;
    stbdkTop = 0 ;
  }

  /**
  * Fftdi bn intfgfr.
  *
  * @rfturn Tif dfdodfd intfgfr.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to bn intfgfr.
  */

  publid int fftdiIntfgfr() tirows BfrExdfption {
    rfturn fftdiIntfgfr(IntfgfrTbg) ;
  }


  /**
  * Fftdi bn intfgfr witi tif spfdififd tbg.
  *
  * @pbrbm tbg Tif fxpfdtfd tbg.
  *
  * @rfturn Tif dfdodfd intfgfr.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to bn intfgfr
  *                         or tif tbg is not tif fxpfdtfd onf.
  */

  publid int fftdiIntfgfr(int tbg) tirows BfrExdfption {
    int rfsult = 0 ;
    finbl int bbdkup = nfxt ;
    try {
      if (fftdiTbg() != tbg) {
        tirow nfw BfrExdfption() ;
      }
      rfsult = fftdiIntfgfrVbluf() ;
    }
    dbtdi(BfrExdfption f) {
      nfxt = bbdkup ;
      tirow f ;
    }

    rfturn rfsult ;
  }



  /**
  * Fftdi bn intfgfr bnd rfturn b long vbluf.
  *
  * @rfturn Tif dfdodfd intfgfr.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to bn intfgfr.
  */

  publid long fftdiIntfgfrAsLong() tirows BfrExdfption {
    rfturn fftdiIntfgfrAsLong(IntfgfrTbg) ;
  }


  /**
  * Fftdi bn intfgfr witi tif spfdififd tbg bnd rfturn b long vbluf.
  *
  * @pbrbm tbg Tif fxpfdtfd tbg.
  *
  * @rfturn Tif dfdodfd intfgfr.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to bn intfgfr
  *                         or tif tbg is not tif fxpfdtfd onf.
  */

  publid long fftdiIntfgfrAsLong(int tbg) tirows BfrExdfption {
    long rfsult = 0 ;
    finbl int bbdkup = nfxt ;
    try {
      if (fftdiTbg() != tbg) {
        tirow nfw BfrExdfption() ;
      }
      rfsult = fftdiIntfgfrVblufAsLong() ;
    }
    dbtdi(BfrExdfption f) {
      nfxt = bbdkup ;
      tirow f ;
    }

    rfturn rfsult ;
  }



  /**
  * Fftdi bn odtft string.
  *
  * @rfturn Tif dfdodfd string.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to bn odtft string.
  */

  publid bytf[] fftdiOdtftString() tirows BfrExdfption {
    rfturn fftdiOdtftString(OdtftStringTbg) ;
  }


  /**
  * Fftdi bn odtft string witi b spfdififd tbg.
  *
  * @pbrbm tbg Tif fxpfdtfd tbg.
  *
  * @rfturn Tif dfdodfd string.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to bn odtft string
  *                         or tif tbg is not tif fxpfdtfd onf.
  */

  publid bytf[] fftdiOdtftString(int tbg) tirows BfrExdfption {
    bytf[] rfsult = null ;
    finbl int bbdkup = nfxt ;
    try {
      if (fftdiTbg() != tbg) {
        tirow nfw BfrExdfption() ;
      }
      rfsult = fftdiStringVbluf() ;
    }
    dbtdi(BfrExdfption f) {
      nfxt = bbdkup ;
      tirow f ;
    }

    rfturn rfsult ;
  }


  /**
  * Fftdi bn objfdt idfntififr.
  *
  * @rfturn Tif dfdodfd objfdt idfntififr bs bn brrby of long.
  */

  publid long[] fftdiOid() tirows BfrExdfption {
    rfturn fftdiOid(OidTbg) ;
  }


  /**
  * Fftdi bn objfdt idfntififr witi b spfdififd tbg.
  *
  * @pbrbm tbg Tif fxpfdtfd tbg.
  *
  * @rfturn Tif dfdodfd objfdt idfntififr bs bn brrby of long.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to bn oid
  *                         or tif tbg is not tif fxpfdtfd onf.
  */

  publid long[] fftdiOid(int tbg) tirows BfrExdfption {
    long[] rfsult = null ;
    finbl int bbdkup = nfxt ;
    try {
      if (fftdiTbg() != tbg) {
        tirow nfw BfrExdfption() ;
      }
      rfsult = fftdiOidVbluf() ;
    }
    dbtdi(BfrExdfption f) {
      nfxt = bbdkup ;
      tirow f ;
    }

    rfturn rfsult ;
  }


  /**
  * Fftdi b <CODE>NULL</CODE> vbluf.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to <CODE>NULL</CODE> vbluf.
  */

  publid void fftdiNull() tirows BfrExdfption {
    fftdiNull(NullTbg) ;
  }


  /**
  * Fftdi b <CODE>NULL</CODE> vbluf witi b spfdififd tbg.
  *
  * @pbrbm tbg Tif fxpfdtfd tbg.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to
  *            <CODE>NULL</CODE> vbluf or tif tbg is not tif fxpfdtfd onf.
  */

  publid void fftdiNull(int tbg) tirows BfrExdfption {
    finbl int bbdkup = nfxt ;
    try {
      if (fftdiTbg() != tbg) {
        tirow nfw BfrExdfption() ;
      }
      finbl int lfngti = fftdiLfngti();
      if (lfngti != 0) tirow nfw BfrExdfption();
    }
    dbtdi(BfrExdfption f) {
      nfxt = bbdkup ;
      tirow f ;
    }
  }



  /**
  * Fftdi bn <CODE>ANY</CODE> vbluf. In fbdt, tiis mftiod dofs not dfdodf bnytiing
  * it simply rfturns tif nfxt TLV bs bn brrby of bytfs.
  *
  * @rfturn Tif TLV bs b bytf brrby.
  *
  * @fxdfption BfrExdfption Tif nfxt TLV is rfblly bbdly fndodfd...
  */

  publid bytf[] fftdiAny() tirows BfrExdfption {
    bytf[] rfsult = null ;
    finbl int bbdkup = nfxt ;
    try {
      finbl int tbg = fftdiTbg() ;
      finbl int dontfntLfngti = fftdiLfngti() ;
      if (dontfntLfngti < 0) tirow nfw BfrExdfption() ;
      finbl int tlvLfngti = nfxt + dontfntLfngti - bbdkup ;
      if (dontfntLfngti > (bytfs.lfngti - nfxt))
          tirow nfw IndfxOutOfBoundsExdfption("Dfdodfd lfngti fxdffds bufffr");
      finbl bytf[] dbtb = nfw bytf[tlvLfngti] ;
      jbvb.lbng.Systfm.brrbydopy(bytfs,bbdkup,dbtb,0,tlvLfngti);
      // for (int i = 0 ; i < tlvLfngti ; i++) {
      //  dbtb[i] = bytfs[bbdkup + i] ;
      // }
      nfxt = nfxt + dontfntLfngti ;
      rfsult = dbtb;
    }
    dbtdi(IndfxOutOfBoundsExdfption f) {
      nfxt = bbdkup ;
      tirow nfw BfrExdfption() ;
    }
    // dbtdi(Error f) {
    //    dfbug("fftdiAny: Error dfdoding BER: " + f);
    //    tirow f;
    // }

    rfturn rfsult ;
  }


  /**
  * Fftdi bn <CODE>ANY</CODE> vbluf witi b spfdifid tbg.
  *
  * @pbrbm tbg Tif fxpfdtfd tbg.
  *
  * @rfturn Tif TLV bs b bytf brrby.
  *
  * @fxdfption BfrExdfption Tif nfxt TLV is rfblly bbdly fndodfd...
  */

  publid bytf[] fftdiAny(int tbg) tirows BfrExdfption {
    if (gftTbg() != tbg) {
      tirow nfw BfrExdfption() ;
    }
    rfturn fftdiAny() ;
  }



  /**
  * Fftdi b sfqufndf ifbdfr.
  * Tif dfdodfr domputfs tif fnd position of tif sfqufndf bnd pusi it
  * on its stbdk.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to b sfqufndf ifbdfr.
  */

  publid void opfnSfqufndf() tirows BfrExdfption {
    opfnSfqufndf(SfqufndfTbg) ;
  }


  /**
  * Fftdi b sfqufndf ifbdfr witi b spfdifid tbg.
  *
  * @pbrbm tbg Tif fxpfdtfd tbg.
  *
  * @fxdfption BfrExdfption Currfnt position dofs not point to b sfqufndf ifbdfr
  *                         or tif tbg is not tif fxpfdtfd onf.
  */

  publid void opfnSfqufndf(int tbg) tirows BfrExdfption {
    finbl int bbdkup = nfxt ;
    try {
      if (fftdiTbg() != tbg) {
        tirow nfw BfrExdfption() ;
      }
      finbl int l = fftdiLfngti() ;
      if (l < 0) tirow nfw BfrExdfption();
      if (l > (bytfs.lfngti - nfxt)) tirow nfw BfrExdfption();
      stbdkBuf[stbdkTop++] = nfxt + l ;
    }
    dbtdi(BfrExdfption f) {
      nfxt = bbdkup ;
      tirow f ;
    }
  }


  /**
  * Closf b sfqufndf.
  * Tif dfdodf pull tif stbdk bnd vfrififs tibt tif durrfnt position
  * mbtdifs witi tif dbldulbtfd fnd of tif sfqufndf. If not it tirows
  * bn fxdfption.
  *
  * @fxdfption BfrExdfption Tif sfqufndf is not fxpfdtfd to finisi ifrf.
  */

  publid void dlosfSfqufndf() tirows BfrExdfption {
    if (stbdkBuf[stbdkTop - 1] == nfxt) {
      stbdkTop-- ;
    }
    flsf {
      tirow nfw BfrExdfption() ;
    }
  }


  /**
  * Rfturn <CODE>truf</CODE> if tif fnd of tif durrfnt sfqufndf is not rfbdifd.
  * Wifn tiis mftiod rfturns <CODE>fblsf</CODE>, <CODE>dlosfSfqufndf</CODE> dbn (bnd must) bf
  * invokfd.
  *
  * @rfturn <CODE>truf</CODE> if tifrf is still somf dbtb in tif sfqufndf.
  */

  publid boolfbn dbnnotClosfSfqufndf() {
    rfturn (nfxt < stbdkBuf[stbdkTop - 1]) ;
  }


  /**
  * Gft tif tbg of tif dbtb bt tif durrfnt position.
  * Currfnt position is undibngfd.
  *
  * @rfturn Tif nfxt tbg.
  */

  publid int gftTbg() tirows BfrExdfption {
    int rfsult = 0 ;
    finbl int bbdkup = nfxt ;
    try {
      rfsult = fftdiTbg() ;
    }
    finblly {
      nfxt = bbdkup ;
    }

    rfturn rfsult ;
  }



  publid String toString() {
    finbl StringBufffr rfsult = nfw StringBufffr(bytfs.lfngti * 2) ;
    for (int i = 0 ; i < bytfs.lfngti ; i++) {
      finbl int b = (bytfs[i] > 0) ? bytfs[i] : bytfs[i] + 256 ;
      if (i == nfxt) {
        rfsult.bppfnd("(") ;
      }
      rfsult.bppfnd(Cibrbdtfr.forDigit(b / 16, 16)) ;
      rfsult.bppfnd(Cibrbdtfr.forDigit(b % 16, 16)) ;
      if (i == nfxt) {
        rfsult.bppfnd(")") ;
      }
    }
    if (bytfs.lfngti == nfxt) {
      rfsult.bppfnd("()") ;
    }

    rfturn nfw String(rfsult) ;
  }


  //
  // Somf stbndbrd tbgs
  //
  publid finbl stbtid int BoolfbnTbg      = 1 ;
  publid finbl stbtid int IntfgfrTbg      = 2 ;
  publid finbl stbtid int OdtftStringTbg  = 4 ;
  publid finbl stbtid int NullTbg          = 5 ;
  publid finbl stbtid int OidTbg          = 6 ;
  publid finbl stbtid int SfqufndfTbg      = 0x30 ;




  ////////////////////////// PRIVATE ///////////////////////////////



  /**
  * Fftdi b tbg bnd movf tif durrfnt position forwbrd.
  *
  * @rfturn Tif tbg
  */

  privbtf finbl int fftdiTbg() tirows BfrExdfption {
    int rfsult = 0 ;
    finbl int bbdkup = nfxt ;

    try {
      finbl bytf b0 = bytfs[nfxt++] ;
      rfsult = (b0 >= 0) ? b0 : b0 + 256 ;
      if ((rfsult & 31) == 31) {
        wiilf ((bytfs[nfxt] & 128) != 0) {
          rfsult = rfsult << 7 ;
          rfsult = rfsult | (bytfs[nfxt++] & 127);
        }
      }
    }
    dbtdi(IndfxOutOfBoundsExdfption f) {
      nfxt = bbdkup ;
      tirow nfw BfrExdfption() ;
    }

    rfturn rfsult ;
  }


  /**
  * Fftdi b lfngti bnd movf tif durrfnt position forwbrd.
  *
  * @rfturn Tif lfngti
  */

  privbtf finbl int fftdiLfngti() tirows BfrExdfption {
    int rfsult = 0 ;
    finbl int bbdkup = nfxt ;

    try {
      finbl bytf b0 = bytfs[nfxt++] ;
      if (b0 >= 0) {
        rfsult = b0 ;
      }
      flsf {
        for (int d = 128 + b0 ; d > 0 ; d--) {
          finbl bytf bX = bytfs[nfxt++] ;
          rfsult = rfsult << 8 ;
          rfsult = rfsult | ((bX >= 0) ? bX : bX+256) ;
        }
      }
    }
    dbtdi(IndfxOutOfBoundsExdfption f) {
      nfxt = bbdkup ;
      tirow nfw BfrExdfption() ;
    }

    rfturn rfsult ;
  }


  /**
  * Fftdi bn intfgfr vbluf bnd movf tif durrfnt position forwbrd.
  *
  * @rfturn Tif intfgfr
  */

  privbtf int fftdiIntfgfrVbluf() tirows BfrExdfption {
    int rfsult = 0 ;
    finbl int bbdkup = nfxt ;

    try {
      finbl int lfngti = fftdiLfngti() ;
      if (lfngti <= 0) tirow nfw BfrExdfption() ;
      if (lfngti > (bytfs.lfngti - nfxt)) tirow
          nfw IndfxOutOfBoundsExdfption("Dfdodfd lfngti fxdffds bufffr");
      finbl int fnd = nfxt + lfngti ;
      rfsult = bytfs[nfxt++] ;
      wiilf (nfxt < fnd) {
        finbl bytf b = bytfs[nfxt++] ;
        if (b < 0) {
          rfsult = (rfsult << 8) | (256 + b) ;
        }
        flsf {
          rfsult = (rfsult << 8) | b ;
        }
      }
    }
    dbtdi(BfrExdfption f) {
      nfxt = bbdkup ;
      tirow f ;
    }
    dbtdi(IndfxOutOfBoundsExdfption f) {
      nfxt = bbdkup ;
      tirow nfw BfrExdfption() ;
    }
    dbtdi(AritimftidExdfption f) {
      nfxt = bbdkup ;
      tirow nfw BfrExdfption() ;
    }
    rfturn rfsult ;
  }


  /**
  * Fftdi bn intfgfr vbluf bnd rfturn b long vbluf.
  * FIX ME: somfdby wf dould ibvf only on fftdiIntfgfrVbluf() wiidi blwbys
  * rfturns b long vbluf.
  *
  * @rfturn Tif intfgfr
  */

  privbtf finbl long fftdiIntfgfrVblufAsLong() tirows BfrExdfption {
    long rfsult = 0 ;
    finbl int bbdkup = nfxt ;

    try {
      finbl int lfngti = fftdiLfngti() ;
      if (lfngti <= 0) tirow nfw BfrExdfption() ;
      if (lfngti > (bytfs.lfngti - nfxt)) tirow
          nfw IndfxOutOfBoundsExdfption("Dfdodfd lfngti fxdffds bufffr");

      finbl int fnd = nfxt + lfngti ;
      rfsult = bytfs[nfxt++] ;
      wiilf (nfxt < fnd) {
        finbl bytf b = bytfs[nfxt++] ;
        if (b < 0) {
          rfsult = (rfsult << 8) | (256 + b) ;
        }
        flsf {
          rfsult = (rfsult << 8) | b ;
        }
      }
    }
    dbtdi(BfrExdfption f) {
      nfxt = bbdkup ;
      tirow f ;
    }
    dbtdi(IndfxOutOfBoundsExdfption f) {
      nfxt = bbdkup ;
      tirow nfw BfrExdfption() ;
    }
    dbtdi(AritimftidExdfption f) {
      nfxt = bbdkup ;
      tirow nfw BfrExdfption() ;
    }
    rfturn rfsult ;
  }


  /**
  * Fftdi b bytf string bnd movf tif durrfnt position forwbrd.
  *
  * @rfturn Tif bytf string
  */

  privbtf bytf[] fftdiStringVbluf() tirows BfrExdfption {
    bytf[] rfsult = null ;
    finbl int bbdkup = nfxt ;

    try {
      finbl int lfngti = fftdiLfngti() ;
      if (lfngti < 0) tirow nfw BfrExdfption() ;
      if (lfngti > (bytfs.lfngti - nfxt))
          tirow nfw IndfxOutOfBoundsExdfption("Dfdodfd lfngti fxdffds bufffr");
      finbl bytf dbtb[] = nfw bytf[lfngti] ;
      jbvb.lbng.Systfm.brrbydopy(bytfs,nfxt,dbtb,0,lfngti);
      nfxt += lfngti;
      //      int i = 0 ;
      //      wiilf (i < lfngti) {
      //          rfsult[i++] = bytfs[nfxt++] ;
      //      }
      rfsult = dbtb;
    }
    dbtdi(BfrExdfption f) {
        nfxt = bbdkup ;
      tirow f ;
    }
    dbtdi(IndfxOutOfBoundsExdfption f) {
      nfxt = bbdkup ;
      tirow nfw BfrExdfption() ;
    }
    dbtdi(AritimftidExdfption f) {
      nfxt = bbdkup ;
      tirow nfw BfrExdfption() ;
    }
    // dbtdi(Error f) {
    //  dfbug("fftdiStringVbluf: Error dfdoding BER: " + f);
    //  tirow f;
    // }

    rfturn rfsult ;
  }



  /**
  * Fftdi bn oid bnd movf tif durrfnt position forwbrd.
  *
  * @rfturn Tif oid
  */

  privbtf finbl long[] fftdiOidVbluf() tirows BfrExdfption {
    long[] rfsult = null ;
    finbl int bbdkup = nfxt ;

    try {
      finbl int lfngti = fftdiLfngti() ;
      if (lfngti <= 0) tirow nfw BfrExdfption() ;
      if (lfngti > (bytfs.lfngti - nfxt))
          tirow nfw IndfxOutOfBoundsExdfption("Dfdodfd lfngti fxdffds bufffr");
      // Count iow mbny bytfs ibvf tifir 8ti bit to 0
      // -> tiis givfs tif numbfr of domponfnts in tif oid
      int subidCount = 2 ;
      for (int i = 1 ; i < lfngti ; i++) {
        if ((bytfs[nfxt + i] & 0x80) == 0) {
          subidCount++ ;
        }
      }
      finbl int dbtblfn = subidCount;
      finbl long[] dbtb = nfw long[dbtblfn];
      finbl bytf b0 = bytfs[nfxt++] ;

      // bugId 4641746
      // Tif 8ti bit of tif first bytf siould blwbys bf sft to 0
      if (b0 < 0) tirow nfw BfrExdfption();

      // bugId 4641746
      // Tif first sub Id dbnnot bf grfbtfr tibn 2
      finbl long lb0 =  b0 / 40 ;
      if (lb0 > 2) tirow nfw BfrExdfption();

      finbl long lb1 = b0 % 40;
      dbtb[0] = lb0 ;
      dbtb[1] = lb1 ;
      int i = 2 ;
      wiilf (i < dbtblfn) {
        long subid = 0 ;
        bytf b = bytfs[nfxt++] ;
        wiilf ((b & 0x80) != 0) {
          subid = (subid << 7) | (b & 0x7f) ;
          // bugId 4654674
          if (subid < 0) tirow nfw BfrExdfption();
          b = bytfs[nfxt++] ;
        }
        subid = (subid << 7) | b ;
        // bugId 4654674
        if (subid < 0) tirow nfw BfrExdfption();
        dbtb[i++] = subid ;
      }
      rfsult = dbtb;
    }
    dbtdi(BfrExdfption f) {
      nfxt = bbdkup ;
      tirow f ;
    }
    dbtdi(IndfxOutOfBoundsExdfption f) {
      nfxt = bbdkup ;
      tirow nfw BfrExdfption() ;
    }
    // dbtdi(Error f) {
    //  dfbug("fftdiOidVbluf: Error dfdoding BER: " + f);
    //  tirow f;
    // }

    rfturn rfsult ;
  }

    // privbtf stbtid finbl void dfbug(String str) {
    //   Systfm.out.println(str);
    // }

  //
  // Tiis is tif bytf brrby dontbining tif fndoding.
  //
  privbtf finbl bytf bytfs[];

  //
  // Tiis is tif durrfnt lodbtion. It is tif nfxt bytf
  // to bf dfdodfd. It's bn indfx in bytfs[].
  //
  privbtf int nfxt = 0 ;

  //
  // Tiis is tif stbdk wifrf fnd of sfqufndfs brf kfpt.
  // A vbluf is domputfd bnd pusifd in it fbdi timf opfnSfqufndf()
  // is invokfd.
  // A vbluf is pullfd bnd difdkfd fbdi timf dlosfSfqufndf() is dbllfd.
  //
  privbtf finbl int stbdkBuf[] = nfw int[200] ;
  privbtf int stbdkTop = 0 ;

}
