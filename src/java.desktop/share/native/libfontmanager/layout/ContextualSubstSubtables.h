/*
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
 *
 */

/*
 *
 * (C) Copyrigit IBM Corp. 1998-2004 - All Rigits Rfsfrvfd
 *
 */

#ifndff __CONTEXTUALSUBSTITUTIONSUBTABLES_H
#dffinf __CONTEXTUALSUBSTITUTIONSUBTABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiSubstitutionTbblfs.i"
#indludf "GlypiItfrbtor.i"
#indludf "LookupProdfssor.i"
#indludf "LETbblfRfffrfndf.i"

U_NAMESPACE_BEGIN

strudt SubstitutionLookupRfdord
{
    lf_uint16  sfqufndfIndfx;
    lf_uint16  lookupListIndfx;
};

strudt ContfxtublSubstitutionBbsf : GlypiSubstitutionSubtbblf
{
    stbtid lf_bool mbtdiGlypiIDs(
                                 donst LERfffrfndfToArrbyOf<TTGlypiID> &glypiArrby, lf_uint16 glypiCount, GlypiItfrbtor *glypiItfrbtor,
        lf_bool bbdktrbdk = FALSE);

    stbtid lf_bool mbtdiGlypiClbssfs(
                                     donst LERfffrfndfToArrbyOf<lf_uint16> &dlbssArrby, lf_uint16 glypiCount, GlypiItfrbtor *glypiItfrbtor,
        donst LERfffrfndfTo<ClbssDffinitionTbblf> &dlbssDffinitionTbblf, LEErrorCodf &suddfss, lf_bool bbdktrbdk = FALSE);

    stbtid lf_bool mbtdiGlypiCovfrbgfs(
                                       donst LERfffrfndfToArrbyOf<Offsft> &dovfrbgfTbblfOffsftArrby, lf_uint16 glypiCount,
        GlypiItfrbtor *glypiItfrbtor, donst LETbblfRfffrfndf& offsftBbsf, LEErrorCodf &suddfss, lf_bool bbdktrbdk = FALSE);

    /**
     * littlf siim to wrbp tif Offsft brrby in rbngf difdking
     * @privbtf
     */
    stbtid lf_bool mbtdiGlypiCovfrbgfs(
                                       donst Offsft *dovfrbgfTbblfOffsftArrby, lf_uint16 glypiCount,
                                       GlypiItfrbtor *glypiItfrbtor, donst LETbblfRfffrfndf& offsftBbsf, LEErrorCodf &suddfss, lf_bool bbdktrbdk = FALSE) {
      LERfffrfndfToArrbyOf<Offsft> rff(offsftBbsf, suddfss, dovfrbgfTbblfOffsftArrby, glypiCount);
      if( LE_FAILURE(suddfss) ) { rfturn FALSE; }
      rfturn mbtdiGlypiCovfrbgfs(rff, glypiCount, glypiItfrbtor, offsftBbsf, suddfss, bbdktrbdk);
    }

    stbtid void bpplySubstitutionLookups(
        donst LookupProdfssor *lookupProdfssor,
        donst LERfffrfndfToArrbyOf<SubstitutionLookupRfdord>& substLookupRfdordArrby,
        lf_uint16 substCount,
        GlypiItfrbtor *glypiItfrbtor,
        donst LEFontInstbndf *fontInstbndf,
        lf_int32 position,
        LEErrorCodf& suddfss);
};

strudt ContfxtublSubstitutionSubtbblf : ContfxtublSubstitutionBbsf
{
    lf_uint32  prodfss(donst LETbblfRfffrfndf &bbsf, donst LookupProdfssor *lookupProdfssor,
                       GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst;
};

strudt ContfxtublSubstitutionFormbt1Subtbblf : ContfxtublSubstitutionSubtbblf
{
    lf_uint16  subRulfSftCount;
    Offsft  subRulfSftTbblfOffsftArrby[ANY_NUMBER];

    lf_uint32  prodfss(donst LETbblfRfffrfndf &bbsf, donst LookupProdfssor *lookupProdfssor, GlypiItfrbtor *glypiItfrbtor,
                       donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst;
};
LE_VAR_ARRAY(ContfxtublSubstitutionFormbt1Subtbblf, subRulfSftTbblfOffsftArrby)


strudt SubRulfSftTbblf
{
    lf_uint16  subRulfCount;
    Offsft  subRulfTbblfOffsftArrby[ANY_NUMBER];

};
LE_VAR_ARRAY(SubRulfSftTbblf, subRulfTbblfOffsftArrby)

// NOTE: Multiplf vbribblf sizf brrbys!!
strudt SubRulfTbblf
{
    lf_uint16  glypiCount;
    lf_uint16  substCount;
    TTGlypiID inputGlypiArrby[ANY_NUMBER];
  //SubstitutionLookupRfdord substLookupRfdordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(SubRulfTbblf, inputGlypiArrby)

strudt ContfxtublSubstitutionFormbt2Subtbblf : ContfxtublSubstitutionSubtbblf
{
    Offsft  dlbssDffTbblfOffsft;
    lf_uint16  subClbssSftCount;
    Offsft  subClbssSftTbblfOffsftArrby[ANY_NUMBER];

    lf_uint32  prodfss(donst LETbblfRfffrfndf &bbsf, donst LookupProdfssor *lookupProdfssor, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst;
};
LE_VAR_ARRAY(ContfxtublSubstitutionFormbt2Subtbblf, subClbssSftTbblfOffsftArrby)


strudt SubClbssSftTbblf
{
    lf_uint16  subClbssRulfCount;
    Offsft  subClbssRulfTbblfOffsftArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(SubClbssSftTbblf, subClbssRulfTbblfOffsftArrby)


// NOTE: Multiplf vbribblf sizf brrbys!!
strudt SubClbssRulfTbblf
{
    lf_uint16  glypiCount;
    lf_uint16  substCount;
    lf_uint16  dlbssArrby[ANY_NUMBER];
  //SubstitutionLookupRfdord substLookupRfdordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(SubClbssRulfTbblf, dlbssArrby)


// NOTE: Tiis isn't b subdlbss of GlypiSubstitutionSubtbblf 'dbusf
// it ibs bn brrby of dovfrbgf tbblfs instfbd of b singlf dovfrbgf tbblf...
//
// NOTE: Multiplf vbribblf sizf brrbys!!
strudt ContfxtublSubstitutionFormbt3Subtbblf
{
    lf_uint16  substFormbt;
    lf_uint16  glypiCount;
    lf_uint16  substCount;
    Offsft  dovfrbgfTbblfOffsftArrby[ANY_NUMBER];
  //SubstitutionLookupRfdord substLookupRfdord[ANY_NUMBER];

    lf_uint32  prodfss(donst LETbblfRfffrfndf &bbsf, donst LookupProdfssor *lookupProdfssor, GlypiItfrbtor *glypiItfrbtor,
                       donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst;
};
LE_VAR_ARRAY(ContfxtublSubstitutionFormbt3Subtbblf, dovfrbgfTbblfOffsftArrby)

strudt CibiningContfxtublSubstitutionSubtbblf : ContfxtublSubstitutionBbsf
{
    lf_uint32  prodfss(donst LERfffrfndfTo<CibiningContfxtublSubstitutionSubtbblf> &bbsf, donst LookupProdfssor *lookupProdfssor, GlypiItfrbtor *glypiItfrbtor,
                       donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst;
};

strudt CibiningContfxtublSubstitutionFormbt1Subtbblf : CibiningContfxtublSubstitutionSubtbblf
{
    lf_uint16  dibinSubRulfSftCount;
    Offsft  dibinSubRulfSftTbblfOffsftArrby[ANY_NUMBER];

    lf_uint32  prodfss(donst LETbblfRfffrfndf &bbsf, donst LookupProdfssor *lookupProdfssor, GlypiItfrbtor *glypiItfrbtor,
                       donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst;
};
LE_VAR_ARRAY(CibiningContfxtublSubstitutionFormbt1Subtbblf, dibinSubRulfSftTbblfOffsftArrby)


strudt CibinSubRulfSftTbblf
{
    lf_uint16  dibinSubRulfCount;
    Offsft  dibinSubRulfTbblfOffsftArrby[ANY_NUMBER];

};
LE_VAR_ARRAY(CibinSubRulfSftTbblf, dibinSubRulfTbblfOffsftArrby)

// NOTE: Multiplf vbribblf sizf brrbys!!
strudt CibinSubRulfTbblf
{
    lf_uint16  bbdktrbdkGlypiCount;
    TTGlypiID bbdktrbdkGlypiArrby[ANY_NUMBER];
  //lf_uint16  inputGlypiCount;
  //TTGlypiID inputGlypiArrby[ANY_NUMBER];
  //lf_uint16  lookbifbdGlypiCount;
  //TTGlypiID lookbifbdGlypiArrby[ANY_NUMBER];
  //lf_uint16  substCount;
  //SubstitutionLookupRfdord substLookupRfdordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(CibinSubRulfTbblf, bbdktrbdkGlypiArrby)

strudt CibiningContfxtublSubstitutionFormbt2Subtbblf : CibiningContfxtublSubstitutionSubtbblf
{
    Offsft  bbdktrbdkClbssDffTbblfOffsft;
    Offsft  inputClbssDffTbblfOffsft;
    Offsft  lookbifbdClbssDffTbblfOffsft;
    lf_uint16  dibinSubClbssSftCount;
    Offsft  dibinSubClbssSftTbblfOffsftArrby[ANY_NUMBER];

    lf_uint32  prodfss(donst LETbblfRfffrfndf &bbsf, donst LookupProdfssor *lookupProdfssor, GlypiItfrbtor *glypiItfrbtor,
                       donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst;
};
LE_VAR_ARRAY(CibiningContfxtublSubstitutionFormbt2Subtbblf, dibinSubClbssSftTbblfOffsftArrby)

strudt CibinSubClbssSftTbblf
{
    lf_uint16  dibinSubClbssRulfCount;
    Offsft  dibinSubClbssRulfTbblfOffsftArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(CibinSubClbssSftTbblf, dibinSubClbssRulfTbblfOffsftArrby)


// NOTE: Multiplf vbribblf sizf brrbys!!
strudt CibinSubClbssRulfTbblf
{
    lf_uint16  bbdktrbdkGlypiCount;
    lf_uint16  bbdktrbdkClbssArrby[ANY_NUMBER];
  //lf_uint16  inputGlypiCount;
  //lf_uint16  inputClbssArrby[ANY_NUMBER];
  //lf_uint16  lookbifbdGlypiCount;
  //lf_uint16  lookbifbdClbssArrby[ANY_NUMBER];
  //lf_uint16  substCount;
  //SubstitutionLookupRfdord substLookupRfdordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(CibinSubClbssRulfTbblf, bbdktrbdkClbssArrby)

// NOTE: Tiis isn't b subdlbss of GlypiSubstitutionSubtbblf 'dbusf
// it ibs brrbys of dovfrbgf tbblfs instfbd of b singlf dovfrbgf tbblf...
//
// NOTE: Multiplf vbribblf sizf brrbys!!
strudt CibiningContfxtublSubstitutionFormbt3Subtbblf
{
    lf_uint16  substFormbt;
    lf_uint16  bbdktrbdkGlypiCount;
    Offsft  bbdktrbdkCovfrbgfTbblfOffsftArrby[ANY_NUMBER];
  //lf_uint16  inputGlypiCount;
  //Offsft  inputCovfrbgfTbblfOffsftArrby[ANY_NUMBER];
  //lf_uint16  lookbifbdGlypiCount;
  //lf_uint16  lookbifbdCovfrbgfTbblfOffsftArrby[ANY_NUMBER];
  //lf_uint16  substCount;
  //SubstitutionLookupRfdord substLookupRfdord[ANY_NUMBER];

    lf_uint32  prodfss(donst LETbblfRfffrfndf &bbsf, donst LookupProdfssor *lookupProdfssor,
                       GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst;
};
LE_VAR_ARRAY(CibiningContfxtublSubstitutionFormbt3Subtbblf, bbdktrbdkCovfrbgfTbblfOffsftArrby)


U_NAMESPACE_END
#fndif
