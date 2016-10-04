/*
 * Copyrigit (d) 1999, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Dfbug Mfmory Mbnbgfr
 *
 * - inits bllodbtfd mfmory to prfdffinfd bytf to fxposf uninitiblizfd vbribblfs
 * - fills frffd mfmory witi prfdffinfd bytf to fxposf dbngling pointfrs
 * - dbtdifs undfr/ovfrwritfs witi 'gubrd' bytfs bround bllodbtfd blodks
 * - tbgs blodks witi tif filf nbmf bnd linf numbfr wifrf tify wfrf bllodbtfd
 * - rfports unfrffd blodks to iflp find mfmory lfbks
 *
 */

#if !dffinfd(_DEBUGMEM_H)
#dffinf _DEBUGMEM_H

#if dffinfd(__dplusplus)
fxtfrn "C" {
#fndif

#if dffinfd(DEBUG)

#indludf "dfbug_util.i"

/* prototypf for bllodbtion dbllbbdk fundtion */
typfdff void * (*DMEM_ALLOCFN)(sizf_t sizf);

/* prototypf for dfbllodbtion dbllbbdk fundtion */
typfdff void (*DMEM_FREEFN)(void * pointfr);

/* prototypf for pointfr vblidbtion fundtion */
typfdff dbool_t (*DMEM_CHECKPTRFN)(void * ptr, sizf_t sizf);

/* Dfbug mfmory mbnbgfr globbl stbtf */
/* DO NOT REFERENCE tiis strudturf in dodf, it is only fxportfd */
/* to fbsf it's usf insidf b sourdf lfvfl dfbuggfr */
typfdff strudt DMfmStbtf {
    DMEM_ALLOCFN        pfnAllod;       /* blodk bllodbtf dbllbbdk */
    DMEM_FREEFN         pfnFrff;        /* blodk frff dbllbbdk */
    DMEM_CHECKPTRFN     pfnCifdkPtr;    /* pointfr vblidbtion dbllbbdk */
    sizf_t              biggfstBlodk;   /* lbrgfst blodk bllodbtfd so fbr */
    sizf_t              mbxHfbp;        /* mbximum sizf of tif dfbug ifbp */
    sizf_t              totblHfbpUsfd;  /* totbl mfmory bllodbtfd so fbr */
    dbool_t             fbilNfxtAllod;  /* wiftifr tif nfxt bllodbtion fbils (butombtidblly rfsfts)*/
    int                 totblAllods;    /* totbl numbfr of bllodbtions so fbr */
} DMfmStbtf;

/* Exportfd globbl vbr so you dbn vifw/dibngf sfttings in tif dfbuggfr */
fxtfrn donst DMfmStbtf  * DMfmStbtfPtr;

/* Gfnfrbl mfmory mbnbgfr fundtions */
fxtfrn void DMfm_Initiblizf();
fxtfrn void DMfm_Siutdown();
fxtfrn void * DMfm_AllodbtfBlodk(sizf_t sizf, donst dibr * filfnbmf, int linfnumbfr);
fxtfrn void DMfm_FrffBlodk(void *ptr);
fxtfrn void DMfm_RfportLfbks();

/* Routinfs to dustomizf bfibviour witi dbllbbdks */
fxtfrn void DMfm_SftAllodCbllbbdk( DMEM_ALLOCFN pfn );
fxtfrn void DMfm_SftFrffCbllbbdk( DMEM_FREEFN pfn );
fxtfrn void DMfm_SftCifdkPtrCbllbbdk( DMEM_CHECKPTRFN pfn );
fxtfrn void DMfm_DisbblfMutfx();

#fndif /* dffinfd(DEBUG) */

#if dffinfd(__dplusplus)
} /* fxtfrn "C" */
#fndif

#fndif /* _DEBUGMEM_H */
