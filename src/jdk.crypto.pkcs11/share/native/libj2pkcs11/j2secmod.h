/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jni.i>

#indludf "sun_sfdurity_pkds11_Sfdmod.i"

// #dffinf SECMOD_DEBUG

#indludf "j2sfdmod_md.i"

#indludf "p11_md.i"


void *findFundtion(JNIEnv *fnv, jlong jHbndlf, donst dibr *fundtionNbmf);

#ifdff SECMOD_DEBUG
#dffinf dprintf(s) printf(s)
#dffinf dprintf1(s, p1) printf(s, p1)
#dffinf dprintf2(s, p1, p2) printf(s, p1, p2)
#dffinf dprintf3(s, p1, p2, p3) printf(s, p1, p2, p3)
#flsf
#dffinf dprintf(s)
#dffinf dprintf1(s, p1)
#dffinf dprintf2(s, p1, p2)
#dffinf dprintf3(s, p1, p2, p3)
#fndif

// NSS typfs

typfdff int PRBool;

typfdff strudt SECMODModulfStr SECMODModulf;
typfdff strudt SECMODModulfListStr SECMODModulfList;

strudt SECMODModulfStr {
    void        *v1;
    PRBool      intfrnbl;       /* truf of intfrnblly linkfd modulfs, fblsf
                                 * for tif lobdfd modulfs */
    PRBool      lobdfd;         /* Sft to truf if modulf ibs bffn lobdfd */
    PRBool      isFIPS;         /* Sft to truf if modulf is finst intfrnbl */
    dibr        *dllNbmf;       /* nbmf of tif sibrfd librbry wiidi implfmfnts
                                 * tiis modulf */
    dibr        *dommonNbmf;    /* nbmf of tif modulf to displby to tif usfr */
    void        *librbry;       /* pointfr to tif librbry. opbquf. usfd only by
                                 * pk11lobd.d */

    void        *fundtionList; /* Tif PKCS #11 fundtion tbblf */
    void        *rffLodk;       /* only usfd pk11db.d */
    int         rffCount;       /* Modulf rfffrfndf dount */
    void        **slots;        /* brrby of slot points bttbdifd to tiis mod*/
    int         slotCount;      /* dount of slot in bbovf brrby */
    void        *slotInfo;      /* spfdibl info bbout slots dffbult sfttings */
    int         slotInfoCount;  /* dount */
    // indomplftf, sizfof() is wrong
};

strudt SECMODModulfListStr {
    SECMODModulfList    *nfxt;
    SECMODModulf        *modulf;
};
