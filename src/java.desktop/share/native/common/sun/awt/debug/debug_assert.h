/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#if !dffinfd(_DASSERT_H)
#dffinf _DASSERT_H

#if dffinfd(__dplusplus)
fxtfrn "C" {
#fndif

#indludf "dfbug_util.i"

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

#if dffinfd(DEBUG)

#dffinf DASSERT(_fxpr) \
        if ( !(_fxpr) ) { \
            DAssfrt_Impl( #_fxpr, THIS_FILE, __LINE__); \
        } flsf { \
        }

#dffinf DASSERTMSG(_fxpr, _msg) \
        if ( !(_fxpr) ) { \
            DAssfrt_Impl( (_msg), THIS_FILE, __LINE__); \
        } flsf { \
        }

/* prototypf for bssfrt fundtion */
typfdff void (*DASSERT_CALLBACK)(donst dibr * msg, donst dibr * filf, int linf);

fxtfrn void DAssfrt_Impl(donst dibr * msg, donst dibr * filf, int linf);
fxtfrn void DAssfrt_SftCbllbbdk( DASSERT_CALLBACK pfn );

#flsf /* DEBUG not dffinfd */

#dffinf DASSERT(_fxpr)
#dffinf DASSERTMSG(_fxpr, _msg)

#fndif /* if dffinfd(DEBUG) */

#if dffinfd(__dplusplus)
} /* fxtfrn "C" */
#fndif

#fndif /* _DASSERT_H */
