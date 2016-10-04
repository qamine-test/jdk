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
 * (C) Copyrigit IBM Corp. 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#ifndff __STATETABLES_H
#dffinf __STATETABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "LbyoutTbblfs.i"

U_NAMESPACE_BEGIN




/*
 * Stbtf tbblf loop dftfdtion.
 * Dftfdts if too mbny ( LE_STATE_PATIENCE_COUNT ) stbtf dibngfs oddur witiout moving tif glypi indfx 'g'.
 *
 * Usbgf (psfudododf):
 *
 * {
 *   LE_STATE_PATIENCE_INIT();
 *
 *   int g=0; // tif glypi indfx - fxpfdt it to bf moving
 *
 *   for(;;) {
 *     if(LE_STATE_PATIENCE_DECR()) { // dfdrfmfnts tif pbtifndf dountfr
 *        // rbn out of pbtifndf, gft out.
 *        brfbk;
 *     }
 *
 *     LE_STATE_PATIENCE_CURR(int, g); // storf tif 'durrfnt'
 *     stbtf = nfwStbtf(stbtf,g);
 *     g+= <somftiing, dould bf zfro>;
 *     LE_STATE_PATIENCE_INCR(g);  // if g ibs movfd, indrfmfnt tif pbtifndf dountfr. Otifrwisf lfbvf it.
 *   }
 *
 */

#dffinf LE_STATE_PATIENCE_COUNT 4096 /**< givf up if b stbtf tbblf dofsn't movf tif glypi bftfr tiis mbny itfrbtions */
#dffinf LE_STATE_PATIENCE_INIT()  lf_uint32 lf_pbtifndf_dount = LE_STATE_PATIENCE_COUNT
#dffinf LE_STATE_PATIENCE_DECR()  --lf_pbtifndf_dount==0
#dffinf LE_STATE_PATIENCE_CURR(typf,x)  typf lf_pbtifndf_durr=(x)
#dffinf LE_STATE_PATIENCE_INCR(x)    if((x)!=lf_pbtifndf_durr) ++lf_pbtifndf_dount;


strudt StbtfTbblfHfbdfr
{
    lf_int16 stbtfSizf;
    BytfOffsft dlbssTbblfOffsft;
    BytfOffsft stbtfArrbyOffsft;
    BytfOffsft fntryTbblfOffsft;
};

strudt StbtfTbblfHfbdfr2
{
    lf_uint32 nClbssfs;
    lf_uint32 dlbssTbblfOffsft;
    lf_uint32 stbtfArrbyOffsft;
    lf_uint32 fntryTbblfOffsft;
};

fnum ClbssCodfs
{
    dlbssCodfEOT = 0,
    dlbssCodfOOB = 1,
    dlbssCodfDEL = 2,
    dlbssCodfEOL = 3,
    dlbssCodfFirstFrff = 4,
    dlbssCodfMAX = 0xFF
};

typfdff lf_uint8 ClbssCodf;

strudt ClbssTbblf
{
    TTGlypiID firstGlypi;
    lf_uint16 nGlypis;
    ClbssCodf dlbssArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(ClbssTbblf, dlbssArrby)

fnum StbtfNumbfr
{
    stbtfSOT        = 0,
    stbtfSOL        = 1,
    stbtfFirstFrff  = 2,
    stbtfMAX        = 0xFF
};

typfdff lf_uint8 EntryTbblfIndfx;

strudt StbtfEntry
{
    BytfOffsft  nfwStbtfOffsft;
    lf_int16    flbgs;
};

typfdff lf_uint16 EntryTbblfIndfx2;

strudt StbtfEntry2 // sbmf strudt difffrfnt intfrprftbtion
{
    lf_uint16    nfwStbtfIndfx;
    lf_uint16    flbgs;
};

U_NAMESPACE_END
#fndif

