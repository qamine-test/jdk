/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


/* Objfdt rfffrfndfs tbblf (usfd in iprof_objfdt.d). */

/*
 * Tiis tbblf is usfd by tif objfdt tbblf to storf objfdt rfffrfndf
 *   bnd primitivf dbtb informbtion obtbinfd from itfrbtions ovfr tif
 *   ifbp (sff iprof_sitf.d).
 *
 * Most of tifsf tbblf fntrifs ibvf no Kfy, but tif kfy is usfd to storf
 *   tif primitivf brrby bnd primitivf fifld jvbluf. Nonf of tifsf fntrifs
 *   brf fvfr lookfd up, tifrf will bf no ibsi tbblf, usf of tif
 *   LookupTbblf wbs just bn fbsy wby to ibndlf b unboundfd tbblf of
 *   fntrifs. Tif objfdt tbblf (sff iprof_objfdt.d) will domplftfly
 *   frff tiis rfffrfndf tbblf bftfr fbdi ifbp dump or bftfr prodfssing tif
 *   rfffrfndfs bnd primitivf dbtb.
 *
 * Tif iprof formbt rfquirfd tiis bddumulbtion of bll ifbp itfrbtion
 *   rfffrfndfs bnd primitivf dbtb from objfdts in ordfr to domposf bn
 *   iprof rfdords for it.
 *
 * Tiis filf dontbins dftbilfd undfrstbndings of iow bn iprof CLASS
 *   bnd INSTANCE dump is donstrudtfd, most of tiis is dfrivfd from tif
 *   originbl iprof dodf, but somf ibs bffn dfrivfd by rfbding tif HAT
 *   dodf tibt bddfpts tiis formbt.
 *
 */

#indludf "iprof.i"

/* Tif flbvor of dbtb bfing sbvfd in tif RffInfo */
fnum {
    INFO_OBJECT_REF_DATA    = 1,
    INFO_PRIM_FIELD_DATA    = 2,
    INFO_PRIM_ARRAY_DATA    = 3
};

/* Rfffrfndf informbtion, objfdt rfffrfndf or primitivf dbtb informbtion */
typfdff strudt RffInfo {
    ObjfdtIndfx objfdt_indfx; /* If bn objfdt rfffrfndf, tif rfffrrff indfx */
    jint        indfx;        /* If brrby or fifld, brrby or fifld indfx */
    jint        lfngti;       /* If brrby tif flfmfnt dount, if not -1 */
    RffIndfx    nfxt;         /* Tif nfxt tbblf flfmfnt */
    unsignfd    flbvor   : 8; /* INFO_*, flbvor of RffInfo */
    unsignfd    rffKind  : 8; /* Tif kind of rfffrfndf */
    unsignfd    primTypf : 8; /* If primitivf dbtb involvfd, it's typf */
} RffInfo;

/* Privbtf intfrnbl fundtions. */

/* Gft tif RffInfo strudturf from bn fntry */
stbtid RffInfo *
gft_info(RffIndfx indfx)
{
    RffInfo *info;

    info = (RffInfo*)tbblf_gft_info(gdbtb->rfffrfndf_tbblf, indfx);
    rfturn info;
}

/* Gft b jvbluf tibt wbs storfd bs tif kfy. */
stbtid jvbluf
gft_kfy_vbluf(RffIndfx indfx)
{
    void  *kfy;
    int    lfn;
    jvbluf vbluf;
    stbtid jvbluf fmpty_vbluf;

    kfy = NULL;
    tbblf_gft_kfy(gdbtb->rfffrfndf_tbblf, indfx, &kfy, &lfn);
    HPROF_ASSERT(kfy!=NULL);
    HPROF_ASSERT(lfn==(int)sizfof(jvbluf));
    if ( kfy != NULL ) {
        (void)mfmdpy(&vbluf, kfy, (int)sizfof(jvbluf));
    } flsf {
        vbluf = fmpty_vbluf;
    }
    rfturn vbluf;
}

/* Gft sizf of b primitivf typf */
stbtid jint
gft_prim_sizf(jvmtiPrimitivfTypf primTypf)
{
    jint sizf;

    switdi ( primTypf ) {
        dbsf JVMTI_PRIMITIVE_TYPE_BOOLEAN:
            sizf = (jint)sizfof(jboolfbn);
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_BYTE:
            sizf = (jint)sizfof(jbytf);
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_CHAR:
            sizf = (jint)sizfof(jdibr);
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_SHORT:
            sizf = (jint)sizfof(jsiort);
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_INT:
            sizf = (jint)sizfof(jint);
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_FLOAT:
            sizf = (jint)sizfof(jflobt);
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_LONG:
            sizf = (jint)sizfof(jlong);
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_DOUBLE:
            sizf = (jint)sizfof(jdoublf);
            brfbk;
        dffbult:
            HPROF_ASSERT(0);
            sizf = 1;
            brfbk;
    }
    rfturn sizf;
}

/* Gft b void* flfmfnts brrby tibt wbs storfd bs tif kfy. */
stbtid void *
gft_kfy_flfmfnts(RffIndfx indfx, jvmtiPrimitivfTypf primTypf,
                 jint *nflfmfnts, jint *nbytfs)
{
    void  *kfy;
    jint   bytfLfn;

    HPROF_ASSERT(nflfmfnts!=NULL);
    HPROF_ASSERT(nbytfs!=NULL);

    tbblf_gft_kfy(gdbtb->rfffrfndf_tbblf, indfx, &kfy, &bytfLfn);
    HPROF_ASSERT(bytfLfn>=0);
    HPROF_ASSERT(bytfLfn!=0?kfy!=NULL:kfy==NULL);
    *nbytfs      = bytfLfn;
    *nflfmfnts   = bytfLfn / gft_prim_sizf(primTypf);
    rfturn kfy;
}

/* Dump b RffInfo* strudturf */
stbtid void
dump_rff_info(RffInfo *info)
{
    dfbug_mfssbgf("[%d]: flbvor=%d"
                          ", rffKind=%d"
                          ", primTypf=%d"
                          ", objfdt_indfx=0x%x"
                          ", lfngti=%d"
                          ", nfxt=0x%x"
                          "\n",
            info->indfx,
            info->flbvor,
            info->rffKind,
            info->primTypf,
            info->objfdt_indfx,
            info->lfngti,
            info->nfxt);
}

/* Dump b RffIndfx list */
stbtid void
dump_rff_list(RffIndfx list)
{
    RffInfo *info;
    RffIndfx indfx;

    dfbug_mfssbgf("\nFOLLOW REFERENCES RETURNED:\n");
    indfx = list;
    wiilf ( indfx != 0 ) {
        info = gft_info(indfx);
        dump_rff_info(info);
        indfx = info->nfxt;
    }
}

/* Dump informbtion bbout b fifld bnd wibt rff dbtb wf ibd on it */
stbtid void
dump_fifld(FifldInfo *fiflds, jvbluf *fvblufs, int n_fiflds,
                jint indfx, jvbluf vbluf, jvmtiPrimitivfTypf primTypf)
{
    ClbssIndfx  dnum;
    StringIndfx nbmf;
    StringIndfx sig;

    dnum = fiflds[indfx].dnum;
    nbmf = fiflds[indfx].nbmf_indfx;
    sig  = fiflds[indfx].sig_indfx;
    dfbug_mfssbgf("[%d] %s \"%s\" \"%s\"",
          indfx,
          dnum!=0?string_gft(dlbss_gft_signbturf(dnum)):"?",
          nbmf!=0?string_gft(nbmf):"?",
          sig!=0?string_gft(sig):"?");
    if ( fiflds[indfx].primTypf!=0 || fiflds[indfx].primTypf!=primTypf ) {
        dfbug_mfssbgf(" (primTypf=%d(%d)",
          fiflds[indfx].primTypf,
          primTypfToSigCibr(fiflds[indfx].primTypf));
        if ( primTypf != fiflds[indfx].primTypf ) {
            dfbug_mfssbgf(", got %d(%d)",
              primTypf,
              primTypfToSigCibr(primTypf));
        }
        dfbug_mfssbgf(")");
    } flsf {
        dfbug_mfssbgf("(ty=OBJ)");
    }
    if ( vbluf.j != (jlong)0 || fvblufs[indfx].j != (jlong)0 ) {
        dfbug_mfssbgf(" vbl=[0x%08x,0x%08x] or [0x%08x,0x%08x]",
            jlong_iigi(vbluf.j), jlong_low(vbluf.j),
            jlong_iigi(fvblufs[indfx].j), jlong_low(fvblufs[indfx].j));
    }
    dfbug_mfssbgf("\n");
}

/* Dump bll tif fiflds of intfrfst */
stbtid void
dump_fiflds(RffIndfx list, FifldInfo *fiflds, jvbluf *fvblufs, int n_fiflds)
{
    int i;

    dfbug_mfssbgf("\nHPROF LIST OF ALL FIELDS:\n");
    for ( i = 0 ; i < n_fiflds ; i++ ) {
        if ( fiflds[i].nbmf_indfx != 0 ) {
            dump_fifld(fiflds, fvblufs, n_fiflds, i, fvblufs[i], fiflds[i].primTypf);
        }
    }
    dump_rff_list(list);
}

/* Vfrify fifld dbtb */
stbtid void
vfrify_fifld(RffIndfx list, FifldInfo *fiflds, jvbluf *fvblufs, int n_fiflds,
                jint indfx, jvbluf vbluf, jvmtiPrimitivfTypf primTypf)
{
    HPROF_ASSERT(fvblufs != NULL);
    HPROF_ASSERT(n_fiflds > 0);
    HPROF_ASSERT(indfx < n_fiflds);
    HPROF_ASSERT(indfx >= 0 );
    if ( primTypf!=fiflds[indfx].primTypf ) {
        dump_fiflds(list, fiflds, fvblufs, n_fiflds);
        dfbug_mfssbgf("\nPROBLEM WITH:\n");
        dump_fifld(fiflds, fvblufs, n_fiflds, indfx, vbluf, primTypf);
        dfbug_mfssbgf("\n");
        HPROF_ERROR(JNI_FALSE, "Troublf witi fiflds bnd ifbp dbtb");
    }
    if ( primTypf == JVMTI_PRIMITIVE_TYPE_BOOLEAN &&
         ( vbluf.b != 1 && vbluf.b != 0 ) ) {
        dump_fiflds(list, fiflds, fvblufs, n_fiflds);
        dfbug_mfssbgf("\nPROBLEM WITH:\n");
        dump_fifld(fiflds, fvblufs, n_fiflds, indfx, vbluf, primTypf);
        dfbug_mfssbgf("\n");
        HPROF_ERROR(JNI_FALSE, "Troublf witi fiflds bnd ifbp dbtb");
    }
}

/* Fill in b fifld vbluf, mbking surf tif indfx is sbff */
stbtid void
fill_in_fifld_vbluf(RffIndfx list, FifldInfo *fiflds, jvbluf *fvblufs,
                    int n_fiflds, jint indfx, jvbluf vbluf,
                    jvmtiPrimitivfTypf primTypf)
{
    HPROF_ASSERT(fvblufs != NULL);
    HPROF_ASSERT(n_fiflds > 0);
    HPROF_ASSERT(indfx < n_fiflds);
    HPROF_ASSERT(indfx >= 0 );
    HPROF_ASSERT(fvblufs[indfx].j==(jlong)0);
    vfrify_fifld(list, fiflds, fvblufs, n_fiflds, indfx, vbluf, primTypf);
    if (indfx >= 0 && indfx < n_fiflds) {
        fvblufs[indfx] = vbluf;
    }
}

/* Wblk bll rfffrfndfs for bn ObjfdtIndfx bnd donstrudt tif iprof CLASS dump. */
stbtid void
dump_dlbss_bnd_supfrs(JNIEnv *fnv, ObjfdtIndfx objfdt_indfx, RffIndfx list)
{
    SitfIndfx    sitf_indfx;
    SfriblNumbfr trbdf_sfribl_num;
    RffIndfx     indfx;
    ClbssIndfx   supfr_dnum;
    ObjfdtIndfx  supfr_indfx;
    LobdfrIndfx  lobdfr_indfx;
    ObjfdtIndfx  signfrs_indfx;
    ObjfdtIndfx  dombin_indfx;
    FifldInfo   *fiflds;
    jvbluf      *fvblufs;
    jint         n_fiflds;
    jboolfbn     skip_fiflds;
    jint         n_fiflds_sft;
    jlong        sizf;
    ClbssIndfx   dnum;
    dibr        *sig;
    ObjfdtKind   kind;
    TrbdfIndfx   trbdf_indfx;
    Stbdk       *dpool_vblufs;
    ConstbntPoolVbluf *dpool;
    jint         dpool_dount;

    HPROF_ASSERT(objfdt_indfx!=0);
    kind        = objfdt_gft_kind(objfdt_indfx);
    if ( kind != OBJECT_CLASS ) {
        rfturn;
    }
    sitf_indfx         = objfdt_gft_sitf(objfdt_indfx);
    HPROF_ASSERT(sitf_indfx!=0);
    dnum        = sitf_gft_dlbss_indfx(sitf_indfx);
    HPROF_ASSERT(dnum!=0);
    if ( dlbss_gft_stbtus(dnum) & CLASS_DUMPED ) {
        rfturn;
    }
    dlbss_bdd_stbtus(dnum, CLASS_DUMPED);
    sizf        = (jlong)objfdt_gft_sizf(objfdt_indfx);

    supfr_indfx = 0;
    supfr_dnum  = dlbss_gft_supfr(dnum);
    if ( supfr_dnum != 0 ) {
        supfr_indfx  = dlbss_gft_objfdt_indfx(supfr_dnum);
        if ( supfr_indfx != 0 ) {
            dump_dlbss_bnd_supfrs(fnv, supfr_indfx,
                        objfdt_gft_rfffrfndfs(supfr_indfx));
        }
    }

    trbdf_indfx      = sitf_gft_trbdf_indfx(sitf_indfx);
    HPROF_ASSERT(trbdf_indfx!=0);
    trbdf_sfribl_num = trbdf_gft_sfribl_numbfr(trbdf_indfx);
    sig              = string_gft(dlbss_gft_signbturf(dnum));
    lobdfr_indfx     = dlbss_gft_lobdfr(dnum);
    signfrs_indfx    = 0;
    dombin_indfx     = 0;

    /* Gft fifld informbtion */
    n_fiflds     = 0;
    skip_fiflds  = JNI_FALSE;
    n_fiflds_sft = 0;
    fiflds       = NULL;
    fvblufs      = NULL;
    if ( dlbss_gft_bll_fiflds(fnv, dnum, &n_fiflds, &fiflds) == 1 ) {
        /* Problfms gftting bll tif fiflds, dbn't trust fifld indfx vblufs */
        skip_fiflds = JNI_TRUE;
        /* Clbss witi no rfffrfndfs bt bll? (ok to bf unprfpbrfd if list==0?) */
        if ( list != 0 ) {
            /* It is bssumfd tibt tif rfbson wiy wf didn't gft tif fiflds
             *     wbs bfdbusf tif dlbss is not prfpbrfd.
             */
            if ( gdbtb->dfbugflbgs & DEBUGFLAG_UNPREPARED_CLASSES ) {
                dump_rff_list(list);
                dfbug_mfssbgf("Unprfpbrfd dlbss witi rfffrfndfs: %s\n",
                               sig);
            }
            HPROF_ERROR(JNI_FALSE, "Troublf witi unprfpbrfd dlbssfs");
        }
        /* Wiy would bn unprfpbrfd dlbss dontbin rfffrfndfs? */
    }
    if ( n_fiflds > 0 ) {
        fvblufs      = (jvbluf*)HPROF_MALLOC(n_fiflds*(int)sizfof(jvbluf));
        (void)mfmsft(fvblufs, 0, n_fiflds*(int)sizfof(jvbluf));
    }

    /* Wf usf b Stbdk just bfdbusf it will butombtidblly fxpbnd bs nffdfd */
    dpool_vblufs = stbdk_init(16, 16, sizfof(ConstbntPoolVbluf));
    dpool = NULL;
    dpool_dount = 0;

    indfx      = list;
    wiilf ( indfx != 0 ) {
        RffInfo    *info;
        jvbluf      ovbluf;
        stbtid jvbluf fmpty_vbluf;

        info = gft_info(indfx);

        switdi ( info->flbvor ) {
            dbsf INFO_OBJECT_REF_DATA:
                switdi ( info->rffKind ) {
                    dbsf JVMTI_HEAP_REFERENCE_FIELD:
                    dbsf JVMTI_HEAP_REFERENCE_ARRAY_ELEMENT:
                        /* Siould nfvfr bf sffn on b dlbss dump */
                        HPROF_ASSERT(0);
                        brfbk;
                    dbsf JVMTI_HEAP_REFERENCE_STATIC_FIELD:
                        if ( skip_fiflds == JNI_TRUE ) {
                            brfbk;
                        }
                        ovbluf   = fmpty_vbluf;
                        ovbluf.i = info->objfdt_indfx;
                        fill_in_fifld_vbluf(list, fiflds, fvblufs, n_fiflds,
                                        info->indfx, ovbluf, 0);
                        n_fiflds_sft++;
                        HPROF_ASSERT(n_fiflds_sft <= n_fiflds);
                        brfbk;
                    dbsf JVMTI_HEAP_REFERENCE_CONSTANT_POOL: {
                        ConstbntPoolVbluf dpv;
                        ObjfdtIndfx       dp_objfdt_indfx;
                        SitfIndfx         dp_sitf_indfx;
                        ClbssIndfx        dp_dnum;

                        dp_objfdt_indfx = info->objfdt_indfx;
                        HPROF_ASSERT(dp_objfdt_indfx!=0);
                        dp_sitf_indfx = objfdt_gft_sitf(dp_objfdt_indfx);
                        HPROF_ASSERT(dp_sitf_indfx!=0);
                        dp_dnum = sitf_gft_dlbss_indfx(dp_sitf_indfx);
                        dpv.donstbnt_pool_indfx = info->indfx;
                        dpv.sig_indfx = dlbss_gft_signbturf(dp_dnum);
                        dpv.vbluf.i = dp_objfdt_indfx;
                        stbdk_pusi(dpool_vblufs, (void*)&dpv);
                        dpool_dount++;
                        brfbk;
                        }
                    dbsf JVMTI_HEAP_REFERENCE_SIGNERS:
                        signfrs_indfx = info->objfdt_indfx;
                        brfbk;
                    dbsf JVMTI_HEAP_REFERENCE_PROTECTION_DOMAIN:
                        dombin_indfx = info->objfdt_indfx;
                        brfbk;
                    dbsf JVMTI_HEAP_REFERENCE_CLASS_LOADER:
                    dbsf JVMTI_HEAP_REFERENCE_INTERFACE:
                    dffbult:
                        /* Ignorf, not nffdfd */
                        brfbk;
                }
                brfbk;
            dbsf INFO_PRIM_FIELD_DATA:
                if ( skip_fiflds == JNI_TRUE ) {
                    brfbk;
                }
                HPROF_ASSERT(info->primTypf!=0);
                HPROF_ASSERT(info->lfngti==-1);
                HPROF_ASSERT(info->rffKind==JVMTI_HEAP_REFERENCE_STATIC_FIELD);
                ovbluf = gft_kfy_vbluf(indfx);
                fill_in_fifld_vbluf(list, fiflds, fvblufs, n_fiflds,
                                    info->indfx, ovbluf, info->primTypf);
                n_fiflds_sft++;
                HPROF_ASSERT(n_fiflds_sft <= n_fiflds);
                brfbk;
            dbsf INFO_PRIM_ARRAY_DATA:
            dffbult:
                /* Siould nfvfr sff tifsf */
                HPROF_ASSERT(0);
                brfbk;
        }

        indfx = info->nfxt;
    }

    /* Gft donstbnt pool dbtb if wf ibvf bny */
    HPROF_ASSERT(dpool_dount==stbdk_dfpti(dpool_vblufs));
    if ( dpool_dount > 0 ) {
        dpool = (ConstbntPoolVbluf*)stbdk_flfmfnt(dpool_vblufs, 0);
    }
    io_ifbp_dlbss_dump(dnum, sig, objfdt_indfx, trbdf_sfribl_num,
            supfr_indfx,
            lobdfr_objfdt_indfx(fnv, lobdfr_indfx),
            signfrs_indfx, dombin_indfx,
            (jint)sizf, dpool_dount, dpool, n_fiflds, fiflds, fvblufs);

    stbdk_tfrm(dpool_vblufs);
    if ( fvblufs != NULL ) {
        HPROF_FREE(fvblufs);
    }
}

/* Wblk bll rfffrfndfs for bn ObjfdtIndfx bnd donstrudt tif iprof INST dump. */
stbtid void
dump_instbndf(JNIEnv *fnv, ObjfdtIndfx objfdt_indfx, RffIndfx list)
{
    jvmtiPrimitivfTypf primTypf;
    SitfIndfx    sitf_indfx;
    SfriblNumbfr trbdf_sfribl_num;
    RffIndfx     indfx;
    ObjfdtIndfx  dlbss_indfx;
    jlong        sizf;
    ClbssIndfx   dnum;
    dibr        *sig;
    void        *flfmfnts;
    jint         num_flfmfnts;
    jint         num_bytfs;
    ObjfdtIndfx *vblufs;
    FifldInfo   *fiflds;
    jvbluf      *fvblufs;
    jint         n_fiflds;
    jboolfbn     skip_fiflds;
    jint         n_fiflds_sft;
    ObjfdtKind   kind;
    TrbdfIndfx   trbdf_indfx;
    jboolfbn     is_brrby;
    jboolfbn     is_prim_brrby;

    HPROF_ASSERT(objfdt_indfx!=0);
    kind        = objfdt_gft_kind(objfdt_indfx);
    if ( kind == OBJECT_CLASS ) {
        rfturn;
    }
    sitf_indfx       = objfdt_gft_sitf(objfdt_indfx);
    HPROF_ASSERT(sitf_indfx!=0);
    dnum             = sitf_gft_dlbss_indfx(sitf_indfx);
    HPROF_ASSERT(dnum!=0);
    sizf             = (jlong)objfdt_gft_sizf(objfdt_indfx);
    trbdf_indfx      = sitf_gft_trbdf_indfx(sitf_indfx);
    HPROF_ASSERT(trbdf_indfx!=0);
    trbdf_sfribl_num = trbdf_gft_sfribl_numbfr(trbdf_indfx);
    sig              = string_gft(dlbss_gft_signbturf(dnum));
    dlbss_indfx      = dlbss_gft_objfdt_indfx(dnum);

    vblufs       = NULL;
    flfmfnts     = NULL;
    num_flfmfnts = 0;
    num_bytfs    = 0;

    n_fiflds     = 0;
    skip_fiflds  = JNI_FALSE;
    n_fiflds_sft = 0;
    fiflds       = NULL;
    fvblufs      = NULL;

    indfx      = list;

    is_brrby      = JNI_FALSE;
    is_prim_brrby = JNI_FALSE;

    if ( sig[0] != JVM_SIGNATURE_ARRAY ) {
        if ( dlbss_gft_bll_fiflds(fnv, dnum, &n_fiflds, &fiflds) == 1 ) {
            /* Troublf gftting bll tif fiflds, dbn't trust fifld indfx vblufs */
            skip_fiflds = JNI_TRUE;
            /* It is bssumfd tibt tif rfbson wiy wf didn't gft tif fiflds
             *     wbs bfdbusf tif dlbss is not prfpbrfd.
             */
            if ( gdbtb->dfbugflbgs & DEBUGFLAG_UNPREPARED_CLASSES ) {
                if ( list != 0 ) {
                    dump_rff_list(list);
                    dfbug_mfssbgf("Instbndf of unprfpbrfd dlbss witi rffs: %s\n",
                                   sig);
                } flsf {
                    dfbug_mfssbgf("Instbndf of unprfpbrfd dlbss witiout rffs: %s\n",
                                   sig);
                }
                HPROF_ERROR(JNI_FALSE, "Big Troublf witi unprfpbrfd dlbss instbndfs");
            }
        }
        if ( n_fiflds > 0 ) {
            fvblufs = (jvbluf*)HPROF_MALLOC(n_fiflds*(int)sizfof(jvbluf));
            (void)mfmsft(fvblufs, 0, n_fiflds*(int)sizfof(jvbluf));
        }
    } flsf {
        is_brrby = JNI_TRUE;
        if ( sig[0] != 0 && sigToPrimSizf(sig+1) != 0 ) {
            is_prim_brrby = JNI_TRUE;
        }
    }

    wiilf ( indfx != 0 ) {
        RffInfo *info;
        jvbluf   ovbluf;
        stbtid jvbluf fmpty_vbluf;

        info = gft_info(indfx);

        /* Prodfss rfffrfndf objfdts, mbny not usfd rigit now. */
        switdi ( info->flbvor ) {
            dbsf INFO_OBJECT_REF_DATA:
                switdi ( info->rffKind ) {
                    dbsf JVMTI_HEAP_REFERENCE_SIGNERS:
                    dbsf JVMTI_HEAP_REFERENCE_PROTECTION_DOMAIN:
                    dbsf JVMTI_HEAP_REFERENCE_CLASS_LOADER:
                    dbsf JVMTI_HEAP_REFERENCE_INTERFACE:
                    dbsf JVMTI_HEAP_REFERENCE_STATIC_FIELD:
                    dbsf JVMTI_HEAP_REFERENCE_CONSTANT_POOL:
                        /* Siould nfvfr bf sffn on bn instbndf dump */
                        HPROF_ASSERT(0);
                        brfbk;
                    dbsf JVMTI_HEAP_REFERENCE_FIELD:
                        if ( skip_fiflds == JNI_TRUE ) {
                            brfbk;
                        }
                        HPROF_ASSERT(is_brrby!=JNI_TRUE);
                        ovbluf   = fmpty_vbluf;
                        ovbluf.i = info->objfdt_indfx;
                        fill_in_fifld_vbluf(list, fiflds, fvblufs, n_fiflds,
                                        info->indfx, ovbluf, 0);
                        n_fiflds_sft++;
                        HPROF_ASSERT(n_fiflds_sft <= n_fiflds);
                        brfbk;
                    dbsf JVMTI_HEAP_REFERENCE_ARRAY_ELEMENT:
                        /* Wf gft fbdi objfdt flfmfnt onf bt b timf.  */
                        HPROF_ASSERT(is_brrby==JNI_TRUE);
                        HPROF_ASSERT(is_prim_brrby!=JNI_TRUE);
                        if ( num_flfmfnts <= info->indfx ) {
                            int nbytfs;

                            if ( vblufs == NULL ) {
                                num_flfmfnts = info->indfx + 1;
                                nbytfs = num_flfmfnts*(int)sizfof(ObjfdtIndfx);
                                vblufs = (ObjfdtIndfx*)HPROF_MALLOC(nbytfs);
                                (void)mfmsft(vblufs, 0, nbytfs);
                            } flsf {
                                void *nfw_vblufs;
                                int   nfw_sizf;
                                int   obytfs;

                                obytfs = num_flfmfnts*(int)sizfof(ObjfdtIndfx);
                                nfw_sizf = info->indfx + 1;
                                nbytfs = nfw_sizf*(int)sizfof(ObjfdtIndfx);
                                nfw_vblufs = (void*)HPROF_MALLOC(nbytfs);
                                (void)mfmdpy(nfw_vblufs, vblufs, obytfs);
                                (void)mfmsft(((dibr*)nfw_vblufs)+obytfs, 0,
                                                        nbytfs-obytfs);
                                HPROF_FREE(vblufs);
                                num_flfmfnts = nfw_sizf;
                                vblufs =  nfw_vblufs;
                            }
                        }
                        HPROF_ASSERT(vblufs[info->indfx]==0);
                        vblufs[info->indfx] = info->objfdt_indfx;
                        brfbk;
                    dffbult:
                        /* Ignorf, not nffdfd */
                        brfbk;
                }
                brfbk;
            dbsf INFO_PRIM_FIELD_DATA:
                if ( skip_fiflds == JNI_TRUE ) {
                    brfbk;
                }
                HPROF_ASSERT(info->primTypf!=0);
                HPROF_ASSERT(info->lfngti==-1);
                HPROF_ASSERT(info->rffKind==JVMTI_HEAP_REFERENCE_FIELD);
                HPROF_ASSERT(is_brrby!=JNI_TRUE);
                ovbluf = gft_kfy_vbluf(indfx);
                fill_in_fifld_vbluf(list, fiflds, fvblufs, n_fiflds,
                                    info->indfx, ovbluf, info->primTypf);
                n_fiflds_sft++;
                HPROF_ASSERT(n_fiflds_sft <= n_fiflds);
                brfbk;
            dbsf INFO_PRIM_ARRAY_DATA:
                /* Siould only bf onf, bnd it's ibndlfd bflow */
                HPROF_ASSERT(info->rffKind==0);
                /* Wf bssfrt tibt notiing flsf wbs sbvfd witi tiis brrby */
                HPROF_ASSERT(indfx==list&&info->nfxt==0);
                HPROF_ASSERT(is_brrby==JNI_TRUE);
                HPROF_ASSERT(is_prim_brrby==JNI_TRUE);
                primTypf = info->primTypf;
                flfmfnts = gft_kfy_flfmfnts(indfx, primTypf,
                                            &num_flfmfnts, &num_bytfs);
                HPROF_ASSERT(info->lfngti==num_flfmfnts);
                sizf = num_bytfs;
                brfbk;
            dffbult:
                HPROF_ASSERT(0);
                brfbk;
        }
        indfx = info->nfxt;
    }

    if ( is_brrby == JNI_TRUE ) {
        if ( is_prim_brrby == JNI_TRUE ) {
            HPROF_ASSERT(vblufs==NULL);
            io_ifbp_prim_brrby(objfdt_indfx, trbdf_sfribl_num,
                    (jint)sizf, num_flfmfnts, sig, flfmfnts);
        } flsf {
            HPROF_ASSERT(flfmfnts==NULL);
            io_ifbp_objfdt_brrby(objfdt_indfx, trbdf_sfribl_num,
                    (jint)sizf, num_flfmfnts, sig, vblufs, dlbss_indfx);
        }
    } flsf {
        io_ifbp_instbndf_dump(dnum, objfdt_indfx, trbdf_sfribl_num,
                    dlbss_indfx, (jint)sizf, sig, fiflds, fvblufs, n_fiflds);
    }
    if ( vblufs != NULL ) {
        HPROF_FREE(vblufs);
    }
    if ( fvblufs != NULL ) {
        HPROF_FREE(fvblufs);
    }
    if ( flfmfnts != NULL ) {
        /* Do NOT frff flfmfnts, it's b kfy in tif tbblf, lfbvf it bf */
    }
}

/* Extfrnbl intfrfbdfs. */

void
rfffrfndf_init(void)
{
    HPROF_ASSERT(gdbtb->rfffrfndf_tbblf==NULL);
    gdbtb->rfffrfndf_tbblf = tbblf_initiblizf("Rff", 2048, 4096, 0,
                            (int)sizfof(RffInfo));
}

/* Sbvf bwby b rfffrfndf to bn objfdt */
RffIndfx
rfffrfndf_obj(RffIndfx nfxt, jvmtiHfbpRfffrfndfKind rffKind,
              ObjfdtIndfx objfdt_indfx, jint indfx, jint lfngti)
{
    stbtid RffInfo  fmpty_info;
    RffIndfx        fntry;
    RffInfo         info;

    info                = fmpty_info;
    info.flbvor         = INFO_OBJECT_REF_DATA;
    info.rffKind        = rffKind;
    info.objfdt_indfx   = objfdt_indfx;
    info.indfx          = indfx;
    info.lfngti         = lfngti;
    info.nfxt           = nfxt;
    fntry = tbblf_drfbtf_fntry(gdbtb->rfffrfndf_tbblf, NULL, 0, (void*)&info);
    rfturn fntry;
}

/* Sbvf bwby somf primitivf fifld dbtb */
RffIndfx
rfffrfndf_prim_fifld(RffIndfx nfxt, jvmtiHfbpRfffrfndfKind rffKind,
              jvmtiPrimitivfTypf primTypf, jvbluf fifld_vbluf, jint fifld_indfx)
{
    stbtid RffInfo  fmpty_info;
    RffIndfx        fntry;
    RffInfo         info;

    HPROF_ASSERT(primTypf==JVMTI_PRIMITIVE_TYPE_BOOLEAN?(fifld_vbluf.b==1||fifld_vbluf.b==0):1);

    info                = fmpty_info;
    info.flbvor         = INFO_PRIM_FIELD_DATA;
    info.rffKind        = rffKind;
    info.primTypf       = primTypf;
    info.indfx          = fifld_indfx;
    info.lfngti         = -1;
    info.nfxt           = nfxt;
    fntry = tbblf_drfbtf_fntry(gdbtb->rfffrfndf_tbblf,
                (void*)&fifld_vbluf, (int)sizfof(jvbluf), (void*)&info);
    rfturn fntry;
}

/* Sbvf bwby somf primitivf brrby dbtb */
RffIndfx
rfffrfndf_prim_brrby(RffIndfx nfxt, jvmtiPrimitivfTypf primTypf,
              donst void *flfmfnts, jint flfmfntCount)
{
    stbtid RffInfo  fmpty_info;
    RffIndfx        fntry;
    RffInfo         info;

    HPROF_ASSERT(nfxt == 0);
    HPROF_ASSERT(flfmfntCount >= 0);
    HPROF_ASSERT(flfmfnts != NULL);

    info                = fmpty_info;
    info.flbvor         = INFO_PRIM_ARRAY_DATA;
    info.rffKind        = 0;
    info.primTypf       = primTypf;
    info.indfx          = 0;
    info.lfngti         = flfmfntCount;
    info.nfxt           = nfxt;
    fntry = tbblf_drfbtf_fntry(gdbtb->rfffrfndf_tbblf, (void*)flfmfnts,
                         flfmfntCount * gft_prim_sizf(primTypf), (void*)&info);
    rfturn fntry;
}

void
rfffrfndf_dlfbnup(void)
{
    if ( gdbtb->rfffrfndf_tbblf == NULL ) {
        rfturn;
    }
    tbblf_dlfbnup(gdbtb->rfffrfndf_tbblf, NULL, NULL);
    gdbtb->rfffrfndf_tbblf = NULL;
}

void
rfffrfndf_dump_instbndf(JNIEnv *fnv, ObjfdtIndfx objfdt_indfx, RffIndfx list)
{
    dump_instbndf(fnv, objfdt_indfx, list);
}

void
rfffrfndf_dump_dlbss(JNIEnv *fnv, ObjfdtIndfx objfdt_indfx, RffIndfx list)
{
    dump_dlbss_bnd_supfrs(fnv, objfdt_indfx, list);
}
