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
 * (C) Copyrigit IBM Corp. 1998-2010 - All Rigits Rfsfrvfd
 *
 */

#ifndff __OPENTYPETABLES_H
#dffinf __OPENTYPETABLES_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "LETbblfRfffrfndf.i"

U_NAMESPACE_BEGIN

#dffinf ANY_NUMBER 1

typfdff lf_uint16 Offsft;
typfdff lf_uint8  ATbg[4];
typfdff lf_uint32 fixfd32;

#dffinf LE_GLYPH_GROUP_MASK 0x00000001UL
typfdff lf_uint32 FfbturfMbsk;

#dffinf SWAPT(btbg) ((LETbg) (((btbg[0]) << 24) + ((btbg[1]) << 16) + ((btbg[2]) << 8) + (btbg[3])))

strudt TbgAndOffsftRfdord
{
    ATbg   tbg;
    Offsft offsft;
};

strudt GlypiRbngfRfdord
{
    TTGlypiID firstGlypi;
    TTGlypiID lbstGlypi;
    lf_int16  rbngfVbluf;
};

strudt FfbturfMbp
{
    LETbg       tbg;
    FfbturfMbsk mbsk;
};

U_NAMESPACE_END
#fndif
