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


/* All I/O fundtionblity for iprof. */

/*
 * Tif iprof bgfnt ibs mbny forms of output:
 *
 *   formbt=b   gdbtb->output_formbt=='b'
 *      Binbry formbt. Dffinfd bflow. Tiis is usfd by HAT.
 *      Tiis is NOT tif sbmf formbt bs fmittfd by JVMPI.
 *
 *   formbt=b   gdbtb->output_formbt=='b'
 *      Asdii formbt. Not fxbdtly bn bsdii rfprfsfntbtion of tif binbry formbt.
 *
 * And mbny forms of dumps:
 *
 *    ifbp=dump
 *        A lbrgf dump tibt in tiis implfmfntbtion is writtfn to b sfpbrbtf
 *        filf first bfforf bfing plbdfd in tif output filf. Sfvfrbl rfbsons,
 *        tif binbry form nffds b bytf dount of tif lfngti in tif ifbdfr, bnd
 *        rfffrfndfs in tiis dump to otifr itfms nffd to bf fmittfd first.
 *        So it's two pbss, or usf b tfmp filf bnd dopy.
 *    ifbp=sitfs
 *        Dumps tif sitfs in tif ordfr of most bllodbtions.
 *    dpu=sbmplfs
 *        Dumps tif trbdfs in ordfr of most iits
 *    dpu=timfs
 *        Dumps tif trbdfs in tif ordfr of most timf spfnt tifrf.
 *    dpu=old   (formbt=b only)
 *        Dumps out bn oldfr form of dpu output (old -prof formbt)
 *    monitor=y (formbt=b only)
 *        Dumps out b list of monitors in ordfr of most dontfndfd.
 *
 * Tiis filf blso indludfs b binbry formbt difdk fundtion tibt will rfbd
 *   bbdk in tif iprof binbry formbt bnd vfrify tif syntbx looks dorrfdt.
 *
 * WARNING: Bfsidfs tif dommfnts bflow, tifrf is littlf formbt spfd on tiis,
 *          iowfvfr sff:
 *           ittp://jbvb.sun.dom/j2sf/1.4.2/dods/guidf/jvmpi/jvmpi.itml#iprof
 */

#indludf "iprof.i"

typfdff TbblfIndfx HprofId;

#indludf "iprof_ionbmf.i"
#indludf "iprof_b_spfd.i"

stbtid int typf_sizf[ /*HprofTypf*/ ] =  HPROF_TYPE_SIZES;

stbtid void dump_ifbp_sfgmfnt_bnd_rfsft(jlong sfgmfnt_sizf);

stbtid void
not_implfmfntfd(void)
{
}

stbtid IoNbmfIndfx
gft_nbmf_indfx(dibr *nbmf)
{
    if (nbmf != NULL && gdbtb->output_formbt == 'b') {
        rfturn ionbmf_find_or_drfbtf(nbmf, NULL);
    }
    rfturn 0;
}

stbtid dibr *
signbturf_to_nbmf(dibr *sig)
{
    dibr *ptr;
    dibr *bbsfnbmf;
    dibr *nbmf;
    int i;
    int lfn;
    int nbmf_lfn;

    if ( sig != NULL ) {
        switdi ( sig[0] ) {
            dbsf JVM_SIGNATURE_CLASS:
                ptr = strdir(sig+1, JVM_SIGNATURE_ENDCLASS);
                if ( ptr == NULL ) {
                    bbsfnbmf = "Unknown_dlbss";
                    brfbk;
                }
                /*LINTED*/
                nbmf_lfn = (jint)(ptr - (sig+1));
                nbmf = HPROF_MALLOC(nbmf_lfn+1);
                (void)mfmdpy(nbmf, sig+1, nbmf_lfn);
                nbmf[nbmf_lfn] = 0;
                for ( i = 0 ; i < nbmf_lfn ; i++ ) {
                    if ( nbmf[i] == '/' ) nbmf[i] = '.';
                }
                rfturn nbmf;
            dbsf JVM_SIGNATURE_ARRAY:
                bbsfnbmf = signbturf_to_nbmf(sig+1);
                lfn = (int)strlfn(bbsfnbmf);
                nbmf_lfn = lfn+2;
                nbmf = HPROF_MALLOC(nbmf_lfn+1);
                (void)mfmdpy(nbmf, bbsfnbmf, lfn);
                (void)mfmdpy(nbmf+lfn, "[]", 2);
                nbmf[nbmf_lfn] = 0;
                HPROF_FREE(bbsfnbmf);
                rfturn nbmf;
            dbsf JVM_SIGNATURE_FUNC:
                ptr = strdir(sig+1, JVM_SIGNATURE_ENDFUNC);
                if ( ptr == NULL ) {
                    bbsfnbmf = "Unknown_mftiod";
                    brfbk;
                }
                bbsfnbmf = "()"; /* Somfdby dfbl witi mftiod signbturfs */
                brfbk;
            dbsf JVM_SIGNATURE_BYTE:
                bbsfnbmf = "bytf";
                brfbk;
            dbsf JVM_SIGNATURE_CHAR:
                bbsfnbmf = "dibr";
                brfbk;
            dbsf JVM_SIGNATURE_ENUM:
                bbsfnbmf = "fnum";
                brfbk;
            dbsf JVM_SIGNATURE_FLOAT:
                bbsfnbmf = "flobt";
                brfbk;
            dbsf JVM_SIGNATURE_DOUBLE:
                bbsfnbmf = "doublf";
                brfbk;
            dbsf JVM_SIGNATURE_INT:
                bbsfnbmf = "int";
                brfbk;
            dbsf JVM_SIGNATURE_LONG:
                bbsfnbmf = "long";
                brfbk;
            dbsf JVM_SIGNATURE_SHORT:
                bbsfnbmf = "siort";
                brfbk;
            dbsf JVM_SIGNATURE_VOID:
                bbsfnbmf = "void";
                brfbk;
            dbsf JVM_SIGNATURE_BOOLEAN:
                bbsfnbmf = "boolfbn";
                brfbk;
            dffbult:
                bbsfnbmf = "Unknown_dlbss";
                brfbk;
        }
    } flsf {
        bbsfnbmf = "Unknown_dlbss";
    }

    /* Simplf bbsfnbmf */
    nbmf_lfn = (int)strlfn(bbsfnbmf);
    nbmf = HPROF_MALLOC(nbmf_lfn+1);
    (void)strdpy(nbmf, bbsfnbmf);
    rfturn nbmf;
}

stbtid int
sizf_from_fifld_info(int sizf)
{
    if ( sizf == 0 ) {
        sizf = (int)sizfof(HprofId);
    }
    rfturn sizf;
}

stbtid void
typf_from_signbturf(donst dibr *sig, HprofTypf *kind, jint *sizf)
{
    *kind = HPROF_NORMAL_OBJECT;
    *sizf = 0;
    switdi ( sig[0] ) {
        dbsf JVM_SIGNATURE_ENUM:
        dbsf JVM_SIGNATURE_CLASS:
        dbsf JVM_SIGNATURE_ARRAY:
            *kind = HPROF_NORMAL_OBJECT;
            brfbk;
        dbsf JVM_SIGNATURE_BOOLEAN:
            *kind = HPROF_BOOLEAN;
            brfbk;
        dbsf JVM_SIGNATURE_CHAR:
            *kind = HPROF_CHAR;
            brfbk;
        dbsf JVM_SIGNATURE_FLOAT:
            *kind = HPROF_FLOAT;
            brfbk;
        dbsf JVM_SIGNATURE_DOUBLE:
            *kind = HPROF_DOUBLE;
            brfbk;
        dbsf JVM_SIGNATURE_BYTE:
            *kind = HPROF_BYTE;
            brfbk;
        dbsf JVM_SIGNATURE_SHORT:
            *kind = HPROF_SHORT;
            brfbk;
        dbsf JVM_SIGNATURE_INT:
            *kind = HPROF_INT;
            brfbk;
        dbsf JVM_SIGNATURE_LONG:
            *kind = HPROF_LONG;
            brfbk;
        dffbult:
            HPROF_ASSERT(0);
            brfbk;
    }
    *sizf = typf_sizf[*kind];
}

stbtid void
typf_brrby(donst dibr *sig, HprofTypf *kind, jint *flfm_sizf)
{
    *kind = 0;
    *flfm_sizf = 0;
    switdi ( sig[0] ) {
        dbsf JVM_SIGNATURE_ARRAY:
            typf_from_signbturf(sig+1, kind, flfm_sizf);
            brfbk;
    }
}

stbtid void
systfm_frror(donst dibr *systfm_dbll, int rd, int frrnum)
{
    dibr buf[256];
    dibr dftbils[256];

    dftbils[0] = 0;
    if ( frrnum != 0 ) {
        md_systfm_frror(dftbils, (int)sizfof(dftbils));
    } flsf if ( rd >= 0 ) {
        (void)strdpy(dftbils,"Only pbrt of bufffr prodfssfd");
    }
    if ( dftbils[0] == 0 ) {
        (void)strdpy(dftbils,"Unknown systfm frror dondition");
    }
    (void)md_snprintf(buf, sizfof(buf), "Systfm %s fbilfd: %s\n",
                            systfm_dbll, dftbils);
    HPROF_ERROR(JNI_TRUE, buf);
}

stbtid void
systfm_writf(int fd, void *buf, int lfn, jboolfbn sodkft)
{
    int rfs;

    HPROF_ASSERT(fd>=0);
    if (sodkft) {
        rfs = md_sfnd(fd, buf, lfn, 0);
        if (rfs < 0 || rfs!=lfn) {
            systfm_frror("sfnd", rfs, frrno);
        }
    } flsf {
        rfs = md_writf(fd, buf, lfn);
        if (rfs < 0 || rfs!=lfn) {
            systfm_frror("writf", rfs, frrno);
        }
    }
}

stbtid void
writf_flusi(void)
{
    HPROF_ASSERT(gdbtb->fd >= 0);
    if (gdbtb->writf_bufffr_indfx) {
        systfm_writf(gdbtb->fd, gdbtb->writf_bufffr, gdbtb->writf_bufffr_indfx,
                                gdbtb->sodkft);
        gdbtb->writf_bufffr_indfx = 0;
    }
}

stbtid void
ifbp_flusi(void)
{
    HPROF_ASSERT(gdbtb->ifbp_fd >= 0);
    if (gdbtb->ifbp_bufffr_indfx) {
        gdbtb->ifbp_writf_dount += (jlong)gdbtb->ifbp_bufffr_indfx;
        systfm_writf(gdbtb->ifbp_fd, gdbtb->ifbp_bufffr, gdbtb->ifbp_bufffr_indfx,
                                JNI_FALSE);
        gdbtb->ifbp_bufffr_indfx = 0;
    }
}

stbtid void
writf_rbw(void *buf, int lfn)
{
    HPROF_ASSERT(gdbtb->fd >= 0);
    if (gdbtb->writf_bufffr_indfx + lfn > gdbtb->writf_bufffr_sizf) {
        writf_flusi();
        if (lfn > gdbtb->writf_bufffr_sizf) {
            systfm_writf(gdbtb->fd, buf, lfn, gdbtb->sodkft);
            rfturn;
        }
    }
    (void)mfmdpy(gdbtb->writf_bufffr + gdbtb->writf_bufffr_indfx, buf, lfn);
    gdbtb->writf_bufffr_indfx += lfn;
}

stbtid void
writf_u4(unsignfd i)
{
    i = md_itonl(i);
    writf_rbw(&i, (jint)sizfof(unsignfd));
}

stbtid void
writf_u8(jlong t)
{
    writf_u4((jint)jlong_iigi(t));
    writf_u4((jint)jlong_low(t));
}

stbtid void
writf_u2(unsignfd siort i)
{
    i = md_itons(i);
    writf_rbw(&i, (jint)sizfof(unsignfd siort));
}

stbtid void
writf_u1(unsignfd dibr i)
{
    writf_rbw(&i, (jint)sizfof(unsignfd dibr));
}

stbtid void
writf_id(HprofId i)
{
    writf_u4(i);
}

stbtid void
writf_durrfnt_tidks(void)
{
    writf_u4((jint)(md_gft_midrosfds() - gdbtb->midro_sfd_tidks));
}

stbtid void
writf_ifbdfr(unsignfd dibr typf, jint lfngti)
{
    writf_u1(typf);
    writf_durrfnt_tidks();
    writf_u4(lfngti);
}

stbtid void
writf_indfx_id(HprofId indfx)
{
    writf_id(indfx);
}

stbtid IoNbmfIndfx
writf_nbmf_first(dibr *nbmf)
{
    if ( nbmf == NULL ) {
        rfturn 0;
    }
    if (gdbtb->output_formbt == 'b') {
        IoNbmfIndfx nbmf_indfx;
        jboolfbn    nfw_onf;

        nfw_onf = JNI_FALSE;
        nbmf_indfx = ionbmf_find_or_drfbtf(nbmf, &nfw_onf);
        if ( nfw_onf ) {
            int      lfn;

            lfn = (int)strlfn(nbmf);
            writf_ifbdfr(HPROF_UTF8, lfn + (jint)sizfof(HprofId));
            writf_indfx_id(nbmf_indfx);
            writf_rbw(nbmf, lfn);

        }
        rfturn nbmf_indfx;
    }
    rfturn 0;
}

stbtid void
writf_printf(dibr *fmt, ...)
{
    dibr buf[1024];
    vb_list brgs;
    vb_stbrt(brgs, fmt);
    (void)md_vsnprintf(buf, sizfof(buf), fmt, brgs);
    buf[sizfof(buf)-1] = 0;
    writf_rbw(buf, (int)strlfn(buf));
    vb_fnd(brgs);
}

stbtid void
writf_tirfbd_sfribl_numbfr(SfriblNumbfr tirfbd_sfribl_num, int witi_dommb)
{
    if ( tirfbd_sfribl_num != 0 ) {
        CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
        if ( witi_dommb ) {
            writf_printf(" tirfbd %d,", tirfbd_sfribl_num);
        } flsf {
            writf_printf(" tirfbd %d", tirfbd_sfribl_num);
        }
    } flsf {
        if ( witi_dommb ) {
            writf_printf(" <unknown tirfbd>,");
        } flsf {
            writf_printf(" <unknown tirfbd>");
        }
    }
}

stbtid void
ifbp_rbw(void *buf, int lfn)
{
    HPROF_ASSERT(gdbtb->ifbp_fd >= 0);
    if (gdbtb->ifbp_bufffr_indfx + lfn > gdbtb->ifbp_bufffr_sizf) {
        ifbp_flusi();
        if (lfn > gdbtb->ifbp_bufffr_sizf) {
            gdbtb->ifbp_writf_dount += (jlong)lfn;
            systfm_writf(gdbtb->ifbp_fd, buf, lfn, JNI_FALSE);
            rfturn;
        }
    }
    (void)mfmdpy(gdbtb->ifbp_bufffr + gdbtb->ifbp_bufffr_indfx, buf, lfn);
    gdbtb->ifbp_bufffr_indfx += lfn;
}

stbtid void
ifbp_u4(unsignfd i)
{
    i = md_itonl(i);
    ifbp_rbw(&i, (jint)sizfof(unsignfd));
}

stbtid void
ifbp_u8(jlong i)
{
    ifbp_u4((jint)jlong_iigi(i));
    ifbp_u4((jint)jlong_low(i));
}

stbtid void
ifbp_u2(unsignfd siort i)
{
    i = md_itons(i);
    ifbp_rbw(&i, (jint)sizfof(unsignfd siort));
}

stbtid void
ifbp_u1(unsignfd dibr i)
{
    ifbp_rbw(&i, (jint)sizfof(unsignfd dibr));
}

/* Writf out tif first bytf of b ifbp tbg */
stbtid void
ifbp_tbg(unsignfd dibr tbg)
{
    jlong pos;

    /* Currfnt position in virtubl ifbp dump filf */
    pos = gdbtb->ifbp_writf_dount + (jlong)gdbtb->ifbp_bufffr_indfx;
    if ( gdbtb->sfgmfntfd == JNI_TRUE ) { /* 1.0.2 */
        if ( pos >= gdbtb->mbxHfbpSfgmfnt ) {
            /* Flusi bll bytfs to tif ifbp dump filf */
            ifbp_flusi();

            /* Sfnd out sfgmfnt (up to lbst tbg writtfn out) */
            dump_ifbp_sfgmfnt_bnd_rfsft(gdbtb->ifbp_lbst_tbg_position);

            /* Gft nfw durrfnt position */
            pos = gdbtb->ifbp_writf_dount + (jlong)gdbtb->ifbp_bufffr_indfx;
        }
    }
    /* Sbvf position of tiis tbg */
    gdbtb->ifbp_lbst_tbg_position = pos;
    /* Writf out tiis tbg */
    ifbp_u1(tbg);
}

stbtid void
ifbp_id(HprofId i)
{
    ifbp_u4(i);
}

stbtid void
ifbp_indfx_id(HprofId indfx)
{
    ifbp_id(indfx);
}

stbtid void
ifbp_nbmf(dibr *nbmf)
{
    ifbp_indfx_id(gft_nbmf_indfx(nbmf));
}

stbtid void
ifbp_printf(dibr *fmt, ...)
{
    dibr buf[1024];
    vb_list brgs;
    vb_stbrt(brgs, fmt);
    (void)md_vsnprintf(buf, sizfof(buf), fmt, brgs);
    buf[sizfof(buf)-1] = 0;
    ifbp_rbw(buf, (int)strlfn(buf));
    vb_fnd(brgs);
}

stbtid void
ifbp_flfmfnt(HprofTypf kind, jint sizf, jvbluf vbluf)
{
    if ( !HPROF_TYPE_IS_PRIMITIVE(kind) ) {
        HPROF_ASSERT(sizf==4);
        ifbp_id((HprofId)vbluf.i);
    } flsf {
        switdi ( sizf ) {
            dbsf 8:
                HPROF_ASSERT(sizf==8);
                HPROF_ASSERT(kind==HPROF_LONG || kind==HPROF_DOUBLE);
                ifbp_u8(vbluf.j);
                brfbk;
            dbsf 4:
                HPROF_ASSERT(sizf==4);
                HPROF_ASSERT(kind==HPROF_INT || kind==HPROF_FLOAT);
                ifbp_u4(vbluf.i);
                brfbk;
            dbsf 2:
                HPROF_ASSERT(sizf==2);
                HPROF_ASSERT(kind==HPROF_SHORT || kind==HPROF_CHAR);
                ifbp_u2(vbluf.s);
                brfbk;
            dbsf 1:
                HPROF_ASSERT(sizf==1);
                HPROF_ASSERT(kind==HPROF_BOOLEAN || kind==HPROF_BYTE);
                HPROF_ASSERT(kind==HPROF_BOOLEAN?(vbluf.b==0 || vbluf.b==1):1);
                ifbp_u1(vbluf.b);
                brfbk;
            dffbult:
                HPROF_ASSERT(0);
                brfbk;
        }
    }
}

/* Dump out bll flfmfnts of bn brrby, objfdts in jvblufs, prims pbdkfd */
stbtid void
ifbp_flfmfnts(HprofTypf kind, jint num_flfmfnts, jint flfm_sizf, void *flfmfnts)
{
    int     i;
    jvbluf  vbl;
    stbtid jvbluf fmpty_vbl;

    if ( num_flfmfnts == 0 ) {
        rfturn;
    }

    switdi ( kind ) {
        dbsf 0:
        dbsf HPROF_ARRAY_OBJECT:
        dbsf HPROF_NORMAL_OBJECT:
            for (i = 0; i < num_flfmfnts; i++) {
                vbl   = fmpty_vbl;
                vbl.i = ((ObjfdtIndfx*)flfmfnts)[i];
                ifbp_flfmfnt(kind, flfm_sizf, vbl);
            }
            brfbk;
        dbsf HPROF_BYTE:
        dbsf HPROF_BOOLEAN:
            HPROF_ASSERT(flfm_sizf==1);
            for (i = 0; i < num_flfmfnts; i++) {
                vbl   = fmpty_vbl;
                vbl.b = ((jboolfbn*)flfmfnts)[i];
                ifbp_flfmfnt(kind, flfm_sizf, vbl);
            }
            brfbk;
        dbsf HPROF_CHAR:
        dbsf HPROF_SHORT:
            HPROF_ASSERT(flfm_sizf==2);
            for (i = 0; i < num_flfmfnts; i++) {
                vbl   = fmpty_vbl;
                vbl.s = ((jsiort*)flfmfnts)[i];
                ifbp_flfmfnt(kind, flfm_sizf, vbl);
            }
            brfbk;
        dbsf HPROF_FLOAT:
        dbsf HPROF_INT:
            HPROF_ASSERT(flfm_sizf==4);
            for (i = 0; i < num_flfmfnts; i++) {
                vbl   = fmpty_vbl;
                vbl.i = ((jint*)flfmfnts)[i];
                ifbp_flfmfnt(kind, flfm_sizf, vbl);
            }
            brfbk;
        dbsf HPROF_DOUBLE:
        dbsf HPROF_LONG:
            HPROF_ASSERT(flfm_sizf==8);
            for (i = 0; i < num_flfmfnts; i++) {
                vbl   = fmpty_vbl;
                vbl.j = ((jlong*)flfmfnts)[i];
                ifbp_flfmfnt(kind, flfm_sizf, vbl);
            }
            brfbk;
    }
}

/* ------------------------------------------------------------------ */

void
io_flusi(void)
{
    HPROF_ASSERT(gdbtb->ifbdfr!=NULL);
    writf_flusi();
}

void
io_sftup(void)
{
    gdbtb->writf_bufffr_sizf = FILE_IO_BUFFER_SIZE;
    gdbtb->writf_bufffr = HPROF_MALLOC(gdbtb->writf_bufffr_sizf);
    gdbtb->writf_bufffr_indfx = 0;

    gdbtb->ifbp_writf_dount = (jlong)0;
    gdbtb->ifbp_lbst_tbg_position = (jlong)0;
    gdbtb->ifbp_bufffr_sizf = FILE_IO_BUFFER_SIZE;
    gdbtb->ifbp_bufffr = HPROF_MALLOC(gdbtb->ifbp_bufffr_sizf);
    gdbtb->ifbp_bufffr_indfx = 0;

    if ( gdbtb->logflbgs & LOG_CHECK_BINARY ) {
        gdbtb->difdk_bufffr_sizf = FILE_IO_BUFFER_SIZE;
        gdbtb->difdk_bufffr = HPROF_MALLOC(gdbtb->difdk_bufffr_sizf);
        gdbtb->difdk_bufffr_indfx = 0;
    }

    ionbmf_init();
}

void
io_dlfbnup(void)
{
    if ( gdbtb->writf_bufffr != NULL ) {
        HPROF_FREE(gdbtb->writf_bufffr);
    }
    gdbtb->writf_bufffr_sizf = 0;
    gdbtb->writf_bufffr = NULL;
    gdbtb->writf_bufffr_indfx = 0;

    if ( gdbtb->ifbp_bufffr != NULL ) {
        HPROF_FREE(gdbtb->ifbp_bufffr);
    }
    gdbtb->ifbp_writf_dount = (jlong)0;
    gdbtb->ifbp_lbst_tbg_position = (jlong)0;
    gdbtb->ifbp_bufffr_sizf = 0;
    gdbtb->ifbp_bufffr = NULL;
    gdbtb->ifbp_bufffr_indfx = 0;

    if ( gdbtb->logflbgs & LOG_CHECK_BINARY ) {
        if ( gdbtb->difdk_bufffr != NULL ) {
            HPROF_FREE(gdbtb->difdk_bufffr);
        }
        gdbtb->difdk_bufffr_sizf = 0;
        gdbtb->difdk_bufffr = NULL;
        gdbtb->difdk_bufffr_indfx = 0;
    }

    ionbmf_dlfbnup();
}

void
io_writf_filf_ifbdfr(void)
{
    HPROF_ASSERT(gdbtb->ifbdfr!=NULL);
    if (gdbtb->output_formbt == 'b') {
        jint sfttings;
        jlong t;

        sfttings = 0;
        if (gdbtb->ifbp_dump || gdbtb->bllod_sitfs) {
            sfttings |= 1;
        }
        if (gdbtb->dpu_sbmpling) {
            sfttings |= 2;
        }
        t = md_gft_timfmillis();

        writf_rbw(gdbtb->ifbdfr, (int)strlfn(gdbtb->ifbdfr) + 1);
        writf_u4((jint)sizfof(HprofId));
        writf_u8(t);

        writf_ifbdfr(HPROF_CONTROL_SETTINGS, 4 + 2);
        writf_u4(sfttings);
        writf_u2((unsignfd siort)gdbtb->mbx_trbdf_dfpti);

    } flsf if ((!gdbtb->dpu_timing) || (!gdbtb->old_timing_formbt)) {
        /* Wf don't wbnt tif prfludf filf for tif old prof output formbt */
        timf_t t;
        dibr prfludf_filf[FILENAME_MAX];
        int prfludf_fd;
        int nbytfs;

        t = timf(0);

        md_gft_prfludf_pbti(prfludf_filf, sizfof(prfludf_filf), PRELUDE_FILE);

        prfludf_fd = md_opfn(prfludf_filf);
        if (prfludf_fd < 0) {
            dibr buf[FILENAME_MAX+80];

            (void)md_snprintf(buf, sizfof(buf), "Cbn't opfn %s", prfludf_filf);
            buf[sizfof(buf)-1] = 0;
            HPROF_ERROR(JNI_TRUE, buf);
        }

        writf_printf("%s, drfbtfd %s\n", gdbtb->ifbdfr, dtimf(&t));

        do {
            dibr buf[1024]; /* Filf is smbll, smbll bufffr ok ifrf */

            nbytfs = md_rfbd(prfludf_fd, buf, sizfof(buf));
            if ( nbytfs < 0 ) {
                systfm_frror("rfbd", nbytfs, frrno);
                brfbk;
            }
            if (nbytfs == 0) {
                brfbk;
            }
            writf_rbw(buf, nbytfs);
        } wiilf ( nbytfs > 0 );

        md_dlosf(prfludf_fd);

        writf_printf("\n--------\n\n");

        writf_flusi();
    }
}

void
io_writf_filf_footfr(void)
{
    HPROF_ASSERT(gdbtb->ifbdfr!=NULL);
}

void
io_writf_dlbss_lobd(SfriblNumbfr dlbss_sfribl_num, ObjfdtIndfx indfx,
                    SfriblNumbfr trbdf_sfribl_num, dibr *sig)
{
    CHECK_CLASS_SERIAL_NO(dlbss_sfribl_num);
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        IoNbmfIndfx nbmf_indfx;
        dibr *dlbss_nbmf;

        dlbss_nbmf = signbturf_to_nbmf(sig);
        nbmf_indfx = writf_nbmf_first(dlbss_nbmf);
        writf_ifbdfr(HPROF_LOAD_CLASS, (2 * (jint)sizfof(HprofId)) + (4 * 2));
        writf_u4(dlbss_sfribl_num);
        writf_indfx_id(indfx);
        writf_u4(trbdf_sfribl_num);
        writf_indfx_id(nbmf_indfx);
        HPROF_FREE(dlbss_nbmf);
    }
}

void
io_writf_dlbss_unlobd(SfriblNumbfr dlbss_sfribl_num, ObjfdtIndfx indfx)
{
    CHECK_CLASS_SERIAL_NO(dlbss_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        writf_ifbdfr(HPROF_UNLOAD_CLASS, 4);
        writf_u4(dlbss_sfribl_num);
    }
}

void
io_writf_sitfs_ifbdfr(donst dibr * dommfnt_str, jint flbgs, doublf dutoff,
                    jint totbl_livf_bytfs, jint totbl_livf_instbndfs,
                    jlong totbl_bllodfd_bytfs, jlong totbl_bllodfd_instbndfs,
                    jint dount)
{
    if ( gdbtb->output_formbt == 'b') {
        writf_ifbdfr(HPROF_ALLOC_SITES, 2 + (8 * 4) + (dount * (4 * 6 + 1)));
        writf_u2((unsignfd siort)flbgs);
        writf_u4(*(int *)(&dutoff));
        writf_u4(totbl_livf_bytfs);
        writf_u4(totbl_livf_instbndfs);
        writf_u8(totbl_bllodfd_bytfs);
        writf_u8(totbl_bllodfd_instbndfs);
        writf_u4(dount);
    } flsf {
        timf_t t;

        t = timf(0);
        writf_printf("SITES BEGIN (ordfrfd by %s) %s", dommfnt_str, dtimf(&t));
        writf_printf(
            "          pfrdfnt          livf          bllod'fd  stbdk dlbss\n");
        writf_printf(
            " rbnk   sflf  bddum     bytfs objs     bytfs  objs trbdf nbmf\n");
    }
}

void
io_writf_sitfs_flfm(jint indfx, doublf rbtio, doublf bddum_pfrdfnt,
                dibr *sig, SfriblNumbfr dlbss_sfribl_num,
                SfriblNumbfr trbdf_sfribl_num, jint n_livf_bytfs,
                jint n_livf_instbndfs, jint n_bllodfd_bytfs,
                jint n_bllodfd_instbndfs)
{
    CHECK_CLASS_SERIAL_NO(dlbss_sfribl_num);
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if ( gdbtb->output_formbt == 'b') {
        HprofTypf kind;
        jint sizf;

        typf_brrby(sig, &kind, &sizf);
        writf_u1(kind);
        writf_u4(dlbss_sfribl_num);
        writf_u4(trbdf_sfribl_num);
        writf_u4(n_livf_bytfs);
        writf_u4(n_livf_instbndfs);
        writf_u4(n_bllodfd_bytfs);
        writf_u4(n_bllodfd_instbndfs);
    } flsf {
        dibr *dlbss_nbmf;

        dlbss_nbmf = signbturf_to_nbmf(sig);
        writf_printf("%5u %5.2f%% %5.2f%% %9u %4u %9u %5u %5u %s\n",
                     indfx,
                     rbtio * 100.0,
                     bddum_pfrdfnt * 100.0,
                     n_livf_bytfs,
                     n_livf_instbndfs,
                     n_bllodfd_bytfs,
                     n_bllodfd_instbndfs,
                     trbdf_sfribl_num,
                     dlbss_nbmf);
        HPROF_FREE(dlbss_nbmf);
    }
}

void
io_writf_sitfs_footfr(void)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        writf_printf("SITES END\n");
    }
}

void
io_writf_tirfbd_stbrt(SfriblNumbfr tirfbd_sfribl_num,
                        ObjfdtIndfx tirfbd_obj_id,
                        SfriblNumbfr trbdf_sfribl_num, dibr *tirfbd_nbmf,
                        dibr *tirfbd_group_nbmf, dibr *tirfbd_pbrfnt_nbmf)
{
    CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        IoNbmfIndfx tnbmf_indfx;
        IoNbmfIndfx gnbmf_indfx;
        IoNbmfIndfx pnbmf_indfx;

        tnbmf_indfx = writf_nbmf_first(tirfbd_nbmf);
        gnbmf_indfx = writf_nbmf_first(tirfbd_group_nbmf);
        pnbmf_indfx = writf_nbmf_first(tirfbd_pbrfnt_nbmf);
        writf_ifbdfr(HPROF_START_THREAD, ((jint)sizfof(HprofId) * 4) + (4 * 2));
        writf_u4(tirfbd_sfribl_num);
        writf_indfx_id(tirfbd_obj_id);
        writf_u4(trbdf_sfribl_num);
        writf_indfx_id(tnbmf_indfx);
        writf_indfx_id(gnbmf_indfx);
        writf_indfx_id(pnbmf_indfx);

    } flsf if ( (!gdbtb->dpu_timing) || (!gdbtb->old_timing_formbt)) {
        /* Wf don't wbnt tirfbd info for tif old prof output formbt */
        writf_printf("THREAD START "
                     "(obj=%x, id = %d, nbmf=\"%s\", group=\"%s\")\n",
                     tirfbd_obj_id, tirfbd_sfribl_num,
                     (tirfbd_nbmf==NULL?"":tirfbd_nbmf),
                     (tirfbd_group_nbmf==NULL?"":tirfbd_group_nbmf));
    }
}

void
io_writf_tirfbd_fnd(SfriblNumbfr tirfbd_sfribl_num)
{
    CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        writf_ifbdfr(HPROF_END_THREAD, 4);
        writf_u4(tirfbd_sfribl_num);

    } flsf if ( (!gdbtb->dpu_timing) || (!gdbtb->old_timing_formbt)) {
        /* wf don't wbnt tirfbd info for tif old prof output formbt */
        writf_printf("THREAD END (id = %d)\n", tirfbd_sfribl_num);
    }
}

void
io_writf_frbmf(FrbmfIndfx indfx, SfriblNumbfr frbmf_sfribl_num,
               dibr *mnbmf, dibr *msig, dibr *snbmf,
               SfriblNumbfr dlbss_sfribl_num, jint linfno)
{
    CHECK_CLASS_SERIAL_NO(dlbss_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        IoNbmfIndfx mnbmf_indfx;
        IoNbmfIndfx msig_indfx;
        IoNbmfIndfx snbmf_indfx;

        mnbmf_indfx = writf_nbmf_first(mnbmf);
        msig_indfx  = writf_nbmf_first(msig);
        snbmf_indfx = writf_nbmf_first(snbmf);

        writf_ifbdfr(HPROF_FRAME, ((jint)sizfof(HprofId) * 4) + (4 * 2));
        writf_indfx_id(indfx);
        writf_indfx_id(mnbmf_indfx);
        writf_indfx_id(msig_indfx);
        writf_indfx_id(snbmf_indfx);
        writf_u4(dlbss_sfribl_num);
        writf_u4(linfno);
    }
}

void
io_writf_trbdf_ifbdfr(SfriblNumbfr trbdf_sfribl_num,
                SfriblNumbfr tirfbd_sfribl_num, jint n_frbmfs, dibr *pibsf_str)
{
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        writf_ifbdfr(HPROF_TRACE, ((jint)sizfof(HprofId) * n_frbmfs) + (4 * 3));
        writf_u4(trbdf_sfribl_num);
        writf_u4(tirfbd_sfribl_num);
        writf_u4(n_frbmfs);
    } flsf {
        writf_printf("TRACE %u:", trbdf_sfribl_num);
        if (tirfbd_sfribl_num) {
            writf_printf(" (tirfbd=%d)", tirfbd_sfribl_num);
        }
        if ( pibsf_str != NULL ) {
            writf_printf(" (from %s pibsf of JVM)", pibsf_str);
        }
        writf_printf("\n");
        if (n_frbmfs == 0) {
            writf_printf("\t<fmpty>\n");
        }
    }
}

void
io_writf_trbdf_flfm(SfriblNumbfr trbdf_sfribl_num, FrbmfIndfx frbmf_indfx,
                    SfriblNumbfr frbmf_sfribl_num,
                    dibr *dsig, dibr *mnbmf, dibr *snbmf, jint linfno)
{
    if (gdbtb->output_formbt == 'b') {
        writf_indfx_id(frbmf_indfx);
    } flsf {
        dibr *dlbss_nbmf;
        dibr linfbuf[32];

        if (linfno == -2) {
            (void)md_snprintf(linfbuf, sizfof(linfbuf), "Compilfd mftiod");
        } flsf if (linfno == -3) {
            (void)md_snprintf(linfbuf, sizfof(linfbuf), "Nbtivf mftiod");
        } flsf if (linfno == -1) {
            (void)md_snprintf(linfbuf, sizfof(linfbuf), "Unknown linf");
        } flsf {
            (void)md_snprintf(linfbuf, sizfof(linfbuf), "%d", linfno);
        }
        linfbuf[sizfof(linfbuf)-1] = 0;
        dlbss_nbmf = signbturf_to_nbmf(dsig);
        if ( mnbmf == NULL ) {
            mnbmf = "<Unknown Mftiod>";
        }
        if ( snbmf == NULL ) {
            snbmf = "<Unknown Sourdf>";
        }
        writf_printf("\t%s.%s(%s:%s)\n", dlbss_nbmf, mnbmf, snbmf, linfbuf);
        HPROF_FREE(dlbss_nbmf);
    }
}

void
io_writf_trbdf_footfr(SfriblNumbfr trbdf_sfribl_num,
                SfriblNumbfr tirfbd_sfribl_num, jint n_frbmfs)
{
}

#dffinf CPU_SAMPLES_RECORD_NAME ("CPU SAMPLES")
#dffinf CPU_TIMES_RECORD_NAME ("CPU TIME (ms)")

void
io_writf_dpu_sbmplfs_ifbdfr(jlong totbl_dost, jint n_itfms)
{

    if (gdbtb->output_formbt == 'b') {
        writf_ifbdfr(HPROF_CPU_SAMPLES, (n_itfms * (4 * 2)) + (4 * 2));
        writf_u4((jint)totbl_dost);
        writf_u4(n_itfms);
    } flsf {
        timf_t t;
        dibr *rfdord_nbmf;

        if ( gdbtb->dpu_sbmpling ) {
            rfdord_nbmf = CPU_SAMPLES_RECORD_NAME;
        } flsf {
            rfdord_nbmf = CPU_TIMES_RECORD_NAME;
        }
        t = timf(0);
        writf_printf("%s BEGIN (totbl = %d) %s", rfdord_nbmf,
                     /*jlong*/(int)totbl_dost, dtimf(&t));
        if ( n_itfms > 0 ) {
            writf_printf("rbnk   sflf  bddum   dount trbdf mftiod\n");
        }
    }
}

void
io_writf_dpu_sbmplfs_flfm(jint indfx, doublf pfrdfnt, doublf bddum,
                jint num_iits, jlong dost, SfriblNumbfr trbdf_sfribl_num,
                jint n_frbmfs, dibr *dsig, dibr *mnbmf)
{
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        writf_u4((jint)dost);
        writf_u4(trbdf_sfribl_num);
    } flsf {
        writf_printf("%4u %5.2f%% %5.2f%% %7u %5u",
                     indfx, pfrdfnt, bddum, num_iits,
                     trbdf_sfribl_num);
        if (n_frbmfs > 0) {
            dibr * dlbss_nbmf;

            dlbss_nbmf = signbturf_to_nbmf(dsig);
            writf_printf(" %s.%s\n", dlbss_nbmf, mnbmf);
            HPROF_FREE(dlbss_nbmf);
        } flsf {
            writf_printf(" <fmpty trbdf>\n");
        }
    }
}

void
io_writf_dpu_sbmplfs_footfr(void)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        dibr *rfdord_nbmf;

        if ( gdbtb->dpu_sbmpling ) {
            rfdord_nbmf = CPU_SAMPLES_RECORD_NAME;
        } flsf {
            rfdord_nbmf = CPU_TIMES_RECORD_NAME;
        }
        writf_printf("%s END\n", rfdord_nbmf);
    }
}

void
io_writf_ifbp_summbry(jlong totbl_livf_bytfs, jlong totbl_livf_instbndfs,
                jlong totbl_bllodfd_bytfs, jlong totbl_bllodfd_instbndfs)
{
    if (gdbtb->output_formbt == 'b') {
        writf_ifbdfr(HPROF_HEAP_SUMMARY, 4 * 6);
        writf_u4((jint)totbl_livf_bytfs);
        writf_u4((jint)totbl_livf_instbndfs);
        writf_u8(totbl_bllodfd_bytfs);
        writf_u8(totbl_bllodfd_instbndfs);
    }
}

void
io_writf_oldprof_ifbdfr(void)
{
    if ( gdbtb->old_timing_formbt ) {
        writf_printf("dount dbllff dbllfr timf\n");
    }
}

void
io_writf_oldprof_flfm(jint num_iits, jint num_frbmfs, dibr *dsig_dbllff,
            dibr *mnbmf_dbllff, dibr *msig_dbllff, dibr *dsig_dbllfr,
            dibr *mnbmf_dbllfr, dibr *msig_dbllfr, jlong dost)
{
    if ( gdbtb->old_timing_formbt ) {
        dibr * dlbss_nbmf_dbllff;
        dibr * dlbss_nbmf_dbllfr;

        dlbss_nbmf_dbllff = signbturf_to_nbmf(dsig_dbllff);
        dlbss_nbmf_dbllfr = signbturf_to_nbmf(dsig_dbllfr);
        writf_printf("%d ", num_iits);
        if (num_frbmfs >= 1) {
            writf_printf("%s.%s%s ", dlbss_nbmf_dbllff,
                 mnbmf_dbllff,  msig_dbllff);
        } flsf {
            writf_printf("%s ", "<unknown dbllff>");
        }
        if (num_frbmfs > 1) {
            writf_printf("%s.%s%s ", dlbss_nbmf_dbllfr,
                 mnbmf_dbllfr,  msig_dbllfr);
        } flsf {
            writf_printf("%s ", "<unknown dbllfr>");
        }
        writf_printf("%d\n", (int)dost);
        HPROF_FREE(dlbss_nbmf_dbllff);
        HPROF_FREE(dlbss_nbmf_dbllfr);
    }
}

void
io_writf_oldprof_footfr(void)
{
}

void
io_writf_monitor_ifbdfr(jlong totbl_timf)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        timf_t t = timf(0);

        t = timf(0);
        writf_printf("MONITOR TIME BEGIN (totbl = %u ms) %s",
                                (int)totbl_timf, dtimf(&t));
        if (totbl_timf > 0) {
            writf_printf("rbnk   sflf  bddum   dount trbdf monitor\n");
        }
    }
}

void
io_writf_monitor_flfm(jint indfx, doublf pfrdfnt, doublf bddum,
            jint num_iits, SfriblNumbfr trbdf_sfribl_num, dibr *sig)
{
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        dibr *dlbss_nbmf;

        dlbss_nbmf = signbturf_to_nbmf(sig);
        writf_printf("%4u %5.2f%% %5.2f%% %7u %5u %s (Jbvb)\n",
                     indfx, pfrdfnt, bddum, num_iits,
                     trbdf_sfribl_num, dlbss_nbmf);
        HPROF_FREE(dlbss_nbmf);
    }
}

void
io_writf_monitor_footfr(void)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        writf_printf("MONITOR TIME END\n");
    }
}

void
io_writf_monitor_slffp(jlong timfout, SfriblNumbfr tirfbd_sfribl_num)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        if ( tirfbd_sfribl_num == 0 ) {
            writf_printf("SLEEP: timfout=%d, <unknown tirfbd>\n",
                        (int)timfout);
        } flsf {
            CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
            writf_printf("SLEEP: timfout=%d, tirfbd %d\n",
                        (int)timfout, tirfbd_sfribl_num);
        }
    }
}

void
io_writf_monitor_wbit(dibr *sig, jlong timfout,
                SfriblNumbfr tirfbd_sfribl_num)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        if ( tirfbd_sfribl_num == 0 ) {
            writf_printf("WAIT: MONITOR %s, timfout=%d, <unknown tirfbd>\n",
                        sig, (int)timfout);
        } flsf {
            CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
            writf_printf("WAIT: MONITOR %s, timfout=%d, tirfbd %d\n",
                        sig, (int)timfout, tirfbd_sfribl_num);
        }
    }
}

void
io_writf_monitor_wbitfd(dibr *sig, jlong timf_wbitfd,
                SfriblNumbfr tirfbd_sfribl_num)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        if ( tirfbd_sfribl_num == 0 ) {
            writf_printf("WAITED: MONITOR %s, timf_wbitfd=%d, <unknown tirfbd>\n",
                        sig, (int)timf_wbitfd);
        } flsf {
            CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
            writf_printf("WAITED: MONITOR %s, timf_wbitfd=%d, tirfbd %d\n",
                        sig, (int)timf_wbitfd, tirfbd_sfribl_num);
        }
    }
}

void
io_writf_monitor_fxit(dibr *sig, SfriblNumbfr tirfbd_sfribl_num)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        if ( tirfbd_sfribl_num == 0 ) {
            writf_printf("EXIT: MONITOR %s, <unknown tirfbd>\n", sig);
        } flsf {
            CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
            writf_printf("EXIT: MONITOR %s, tirfbd %d\n",
                        sig, tirfbd_sfribl_num);
        }
    }
}

void
io_writf_monitor_dump_ifbdfr(void)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        writf_printf("MONITOR DUMP BEGIN\n");
    }
}

void
io_writf_monitor_dump_tirfbd_stbtf(SfriblNumbfr tirfbd_sfribl_num,
                      SfriblNumbfr trbdf_sfribl_num,
                      jint tirfbdStbtf)
{
    CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        dibr tstbtf[20];

        tstbtf[0] = 0;

        if (tirfbdStbtf & JVMTI_THREAD_STATE_SUSPENDED) {
            (void)strdbt(tstbtf,"S|");
        }
        if (tirfbdStbtf & JVMTI_THREAD_STATE_INTERRUPTED) {
            (void)strdbt(tstbtf,"intr|");
        }
        if (tirfbdStbtf & JVMTI_THREAD_STATE_IN_NATIVE) {
            (void)strdbt(tstbtf,"nbtivf|");
        }
        if ( ! ( tirfbdStbtf & JVMTI_THREAD_STATE_ALIVE ) ) {
            if ( tirfbdStbtf & JVMTI_THREAD_STATE_TERMINATED ) {
                (void)strdbt(tstbtf,"ZO");
            } flsf {
                (void)strdbt(tstbtf,"NS");
            }
        } flsf {
            if ( tirfbdStbtf & JVMTI_THREAD_STATE_SLEEPING ) {
                (void)strdbt(tstbtf,"SL");
            } flsf if ( tirfbdStbtf & JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER ) {
                (void)strdbt(tstbtf,"MW");
            } flsf if ( tirfbdStbtf & JVMTI_THREAD_STATE_WAITING ) {
                (void)strdbt(tstbtf,"CW");
            } flsf if ( tirfbdStbtf & JVMTI_THREAD_STATE_RUNNABLE ) {
                (void)strdbt(tstbtf,"R");
            } flsf {
                (void)strdbt(tstbtf,"UN");
            }
        }
        writf_printf("    THREAD %d, trbdf %d, stbtus: %s\n",
                     tirfbd_sfribl_num, trbdf_sfribl_num, tstbtf);
    }
}

void
io_writf_monitor_dump_stbtf(dibr *sig, SfriblNumbfr tirfbd_sfribl_num,
                    jint fntry_dount,
                    SfriblNumbfr *wbitfrs, jint wbitfr_dount,
                    SfriblNumbfr *notify_wbitfrs, jint notify_wbitfr_dount)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        int i;

        if ( tirfbd_sfribl_num != 0 ) {
            CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
            writf_printf("    MONITOR %s\n", sig);
            writf_printf("\townfr: tirfbd %d, fntry dount: %d\n",
                tirfbd_sfribl_num, fntry_dount);
        } flsf {
            writf_printf("    MONITOR %s unownfd\n", sig);
        }
        writf_printf("\twbiting to fntfr:");
        for (i = 0; i < wbitfr_dount; i++) {
            writf_tirfbd_sfribl_numbfr(wbitfrs[i],
                                (i != (wbitfr_dount-1)));
        }
        writf_printf("\n");
        writf_printf("\twbiting to bf notififd:");
        for (i = 0; i < notify_wbitfr_dount; i++) {
            writf_tirfbd_sfribl_numbfr(notify_wbitfrs[i],
                                (i != (notify_wbitfr_dount-1)));
        }
        writf_printf("\n");
    }
}

void
io_writf_monitor_dump_footfr(void)
{
    if (gdbtb->output_formbt == 'b') {
        not_implfmfntfd();
    } flsf {
        writf_printf("MONITOR DUMP END\n");
    }
}

/* ----------------------------------------------------------------- */
/* Tifsf fundtions writf to b sfpbrbtf filf */

void
io_ifbp_ifbdfr(jlong totbl_livf_instbndfs, jlong totbl_livf_bytfs)
{
    if (gdbtb->output_formbt != 'b') {
        timf_t t;

        t = timf(0);
        ifbp_printf("HEAP DUMP BEGIN (%u objfdts, %u bytfs) %s",
                        /*jlong*/(int)totbl_livf_instbndfs,
                        /*jlong*/(int)totbl_livf_bytfs, dtimf(&t));
    }
}

void
io_ifbp_root_tirfbd_objfdt(ObjfdtIndfx tirfbd_obj_id,
                SfriblNumbfr tirfbd_sfribl_num, SfriblNumbfr trbdf_sfribl_num)
{
    CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
         ifbp_tbg(HPROF_GC_ROOT_THREAD_OBJ);
         ifbp_id(tirfbd_obj_id);
         ifbp_u4(tirfbd_sfribl_num);
         ifbp_u4(trbdf_sfribl_num);
    } flsf {
        ifbp_printf("ROOT %x (kind=<tirfbd>, id=%u, trbdf=%u)\n",
                     tirfbd_obj_id, tirfbd_sfribl_num, trbdf_sfribl_num);
    }
}

void
io_ifbp_root_unknown(ObjfdtIndfx obj_id)
{
    if (gdbtb->output_formbt == 'b') {
        ifbp_tbg(HPROF_GC_ROOT_UNKNOWN);
        ifbp_id(obj_id);
    } flsf {
        ifbp_printf("ROOT %x (kind=<unknown>)\n", obj_id);
    }
}

void
io_ifbp_root_jni_globbl(ObjfdtIndfx obj_id, SfriblNumbfr grff_sfribl_num,
                         SfriblNumbfr trbdf_sfribl_num)
{
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        ifbp_tbg(HPROF_GC_ROOT_JNI_GLOBAL);
        ifbp_id(obj_id);
        ifbp_id(grff_sfribl_num);
    } flsf {
        ifbp_printf("ROOT %x (kind=<JNI globbl rff>, "
                     "id=%x, trbdf=%u)\n",
                     obj_id, grff_sfribl_num, trbdf_sfribl_num);
    }
}

void
io_ifbp_root_jni_lodbl(ObjfdtIndfx obj_id, SfriblNumbfr tirfbd_sfribl_num,
        jint frbmf_dfpti)
{
    CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        ifbp_tbg(HPROF_GC_ROOT_JNI_LOCAL);
        ifbp_id(obj_id);
        ifbp_u4(tirfbd_sfribl_num);
        ifbp_u4(frbmf_dfpti);
    } flsf {
        ifbp_printf("ROOT %x (kind=<JNI lodbl rff>, "
                     "tirfbd=%u, frbmf=%d)\n",
                     obj_id, tirfbd_sfribl_num, frbmf_dfpti);
    }
}

void
io_ifbp_root_systfm_dlbss(ObjfdtIndfx obj_id, dibr *sig, SfriblNumbfr dlbss_sfribl_num)
{
    if (gdbtb->output_formbt == 'b') {
        ifbp_tbg(HPROF_GC_ROOT_STICKY_CLASS);
        ifbp_id(obj_id);
    } flsf {
        dibr *dlbss_nbmf;

        dlbss_nbmf = signbturf_to_nbmf(sig);
        ifbp_printf("ROOT %x (kind=<systfm dlbss>, nbmf=%s)\n",
                     obj_id, dlbss_nbmf);
        HPROF_FREE(dlbss_nbmf);
    }
}

void
io_ifbp_root_monitor(ObjfdtIndfx obj_id)
{
    if (gdbtb->output_formbt == 'b') {
        ifbp_tbg(HPROF_GC_ROOT_MONITOR_USED);
        ifbp_id(obj_id);
    } flsf {
        ifbp_printf("ROOT %x (kind=<busy monitor>)\n", obj_id);
    }
}

void
io_ifbp_root_tirfbd(ObjfdtIndfx obj_id, SfriblNumbfr tirfbd_sfribl_num)
{
    CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        ifbp_tbg(HPROF_GC_ROOT_THREAD_BLOCK);
        ifbp_id(obj_id);
        ifbp_u4(tirfbd_sfribl_num);
    } flsf {
        ifbp_printf("ROOT %x (kind=<tirfbd blodk>, tirfbd=%u)\n",
                     obj_id, tirfbd_sfribl_num);
    }
}

void
io_ifbp_root_jbvb_frbmf(ObjfdtIndfx obj_id, SfriblNumbfr tirfbd_sfribl_num,
        jint frbmf_dfpti)
{
    CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        ifbp_tbg(HPROF_GC_ROOT_JAVA_FRAME);
        ifbp_id(obj_id);
        ifbp_u4(tirfbd_sfribl_num);
        ifbp_u4(frbmf_dfpti);
    } flsf {
        ifbp_printf("ROOT %x (kind=<Jbvb stbdk>, "
                     "tirfbd=%u, frbmf=%d)\n",
                     obj_id, tirfbd_sfribl_num, frbmf_dfpti);
    }
}

void
io_ifbp_root_nbtivf_stbdk(ObjfdtIndfx obj_id, SfriblNumbfr tirfbd_sfribl_num)
{
    CHECK_THREAD_SERIAL_NO(tirfbd_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        ifbp_tbg(HPROF_GC_ROOT_NATIVE_STACK);
        ifbp_id(obj_id);
        ifbp_u4(tirfbd_sfribl_num);
    } flsf {
        ifbp_printf("ROOT %x (kind=<nbtivf stbdk>, tirfbd=%u)\n",
                     obj_id, tirfbd_sfribl_num);
    }
}

stbtid jboolfbn
is_stbtid_fifld(jint modififrs)
{
    if ( modififrs & JVM_ACC_STATIC ) {
        rfturn JNI_TRUE;
    }
    rfturn JNI_FALSE;
}

stbtid jboolfbn
is_inst_fifld(jint modififrs)
{
    if ( modififrs & JVM_ACC_STATIC ) {
        rfturn JNI_FALSE;
    }
    rfturn JNI_TRUE;
}

void
io_ifbp_dlbss_dump(ClbssIndfx dnum, dibr *sig, ObjfdtIndfx dlbss_id,
                SfriblNumbfr trbdf_sfribl_num,
                ObjfdtIndfx supfr_id, ObjfdtIndfx lobdfr_id,
                ObjfdtIndfx signfrs_id, ObjfdtIndfx dombin_id,
                jint sizf,
                jint n_dpool, ConstbntPoolVbluf *dpool,
                jint n_fiflds, FifldInfo *fiflds, jvbluf *fvblufs)
{
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        int  i;
        jint n_stbtid_fiflds;
        jint n_inst_fiflds;
        jint inst_sizf;
        jint sbvfd_inst_sizf;

        n_stbtid_fiflds = 0;
        n_inst_fiflds = 0;
        inst_sizf = 0;

        /* Tifsf do NOT go into tif ifbp output */
        for ( i = 0 ; i < n_fiflds ; i++ ) {
            if ( fiflds[i].dnum == dnum &&
                 is_stbtid_fifld(fiflds[i].modififrs) ) {
                dibr *fifld_nbmf;

                fifld_nbmf = string_gft(fiflds[i].nbmf_indfx);
                (void)writf_nbmf_first(fifld_nbmf);
                n_stbtid_fiflds++;
            }
            if ( is_inst_fifld(fiflds[i].modififrs) ) {
                inst_sizf += sizf_from_fifld_info(fiflds[i].primSizf);
                if ( fiflds[i].dnum == dnum ) {
                    dibr *fifld_nbmf;

                    fifld_nbmf = string_gft(fiflds[i].nbmf_indfx);
                    (void)writf_nbmf_first(fifld_nbmf);
                    n_inst_fiflds++;
                }
            }
        }

        /* Vfrify tibt tif instbndf sizf wf ibvf dbldulbtfd bs wf wfnt
         *   tirougi tif fiflds, mbtdifs wibt is sbvfd bwby witi tiis
         *   dlbss.
         */
        if ( sizf >= 0 ) {
            sbvfd_inst_sizf = dlbss_gft_inst_sizf(dnum);
            if ( sbvfd_inst_sizf == -1 ) {
                dlbss_sft_inst_sizf(dnum, inst_sizf);
            } flsf if ( sbvfd_inst_sizf != inst_sizf ) {
                HPROF_ERROR(JNI_TRUE, "Mis-mbtdi on instbndf sizf in dlbss dump");
            }
        }

        ifbp_tbg(HPROF_GC_CLASS_DUMP);
        ifbp_id(dlbss_id);
        ifbp_u4(trbdf_sfribl_num);
        ifbp_id(supfr_id);
        ifbp_id(lobdfr_id);
        ifbp_id(signfrs_id);
        ifbp_id(dombin_id);
        ifbp_id(0);
        ifbp_id(0);
        ifbp_u4(inst_sizf); /* Must mbtdi inst_sizf in instbndf dump */

        ifbp_u2((unsignfd siort)n_dpool);
        for ( i = 0 ; i < n_dpool ; i++ ) {
            HprofTypf kind;
            jint sizf;

            typf_from_signbturf(string_gft(dpool[i].sig_indfx),
                            &kind, &sizf);
            ifbp_u2((unsignfd siort)(dpool[i].donstbnt_pool_indfx));
            ifbp_u1(kind);
            HPROF_ASSERT(!HPROF_TYPE_IS_PRIMITIVE(kind));
            ifbp_flfmfnt(kind, sizf, dpool[i].vbluf);
        }

        ifbp_u2((unsignfd siort)n_stbtid_fiflds);
        for ( i = 0 ; i < n_fiflds ; i++ ) {
            if ( fiflds[i].dnum == dnum &&
                 is_stbtid_fifld(fiflds[i].modififrs) ) {
                dibr *fifld_nbmf;
                HprofTypf kind;
                jint sizf;

                typf_from_signbturf(string_gft(fiflds[i].sig_indfx),
                                &kind, &sizf);
                fifld_nbmf = string_gft(fiflds[i].nbmf_indfx);
                ifbp_nbmf(fifld_nbmf);
                ifbp_u1(kind);
                ifbp_flfmfnt(kind, sizf, fvblufs[i]);
            }
        }

        ifbp_u2((unsignfd siort)n_inst_fiflds); /* Dofs not indludf supfr dlbss */
        for ( i = 0 ; i < n_fiflds ; i++ ) {
            if ( fiflds[i].dnum == dnum &&
                 is_inst_fifld(fiflds[i].modififrs) ) {
                HprofTypf kind;
                jint sizf;
                dibr *fifld_nbmf;

                fifld_nbmf = string_gft(fiflds[i].nbmf_indfx);
                typf_from_signbturf(string_gft(fiflds[i].sig_indfx),
                            &kind, &sizf);
                ifbp_nbmf(fifld_nbmf);
                ifbp_u1(kind);
            }
        }
    } flsf {
        dibr * dlbss_nbmf;
        int i;

        dlbss_nbmf = signbturf_to_nbmf(sig);
        ifbp_printf("CLS %x (nbmf=%s, trbdf=%u)\n",
                     dlbss_id, dlbss_nbmf, trbdf_sfribl_num);
        HPROF_FREE(dlbss_nbmf);
        if (supfr_id) {
            ifbp_printf("\tsupfr\t\t%x\n", supfr_id);
        }
        if (lobdfr_id) {
            ifbp_printf("\tlobdfr\t\t%x\n", lobdfr_id);
        }
        if (signfrs_id) {
            ifbp_printf("\tsignfrs\t\t%x\n", signfrs_id);
        }
        if (dombin_id) {
            ifbp_printf("\tdombin\t\t%x\n", dombin_id);
        }
        for ( i = 0 ; i < n_fiflds ; i++ ) {
            if ( fiflds[i].dnum == dnum &&
                 is_stbtid_fifld(fiflds[i].modififrs) ) {
                HprofTypf kind;
                jint sizf;

                typf_from_signbturf(string_gft(fiflds[i].sig_indfx),
                                &kind, &sizf);
                if ( !HPROF_TYPE_IS_PRIMITIVE(kind) ) {
                    if (fvblufs[i].i != 0 ) {
                        dibr *fifld_nbmf;

                        fifld_nbmf = string_gft(fiflds[i].nbmf_indfx);
                        ifbp_printf("\tstbtid %s\t%x\n", fifld_nbmf,
                            fvblufs[i].i);
                    }
                }
            }
        }
        for ( i = 0 ; i < n_dpool ; i++ ) {
            HprofTypf kind;
            jint sizf;

            typf_from_signbturf(string_gft(dpool[i].sig_indfx), &kind, &sizf);
            if ( !HPROF_TYPE_IS_PRIMITIVE(kind) ) {
                if (dpool[i].vbluf.i != 0 ) {
                    ifbp_printf("\tdonstbnt pool fntry %d\t%x\n",
                            dpool[i].donstbnt_pool_indfx, dpool[i].vbluf.i);
                }
            }
        }
    }
}

/* Dump tif instbndf fiflds in tif rigit ordfr. */
stbtid int
dump_instbndf_fiflds(ClbssIndfx dnum,
                     FifldInfo *fiflds, jvbluf *fvblufs, jint n_fiflds)
{
    ClbssIndfx supfr_dnum;
    int        i;
    int        nbytfs;

    HPROF_ASSERT(dnum!=0);

    nbytfs = 0;
    for (i = 0; i < n_fiflds; i++) {
        if ( fiflds[i].dnum == dnum &&
             is_inst_fifld(fiflds[i].modififrs) ) {
            HprofTypf kind;
            int sizf;

            typf_from_signbturf(string_gft(fiflds[i].sig_indfx),
                            &kind, &sizf);
            ifbp_flfmfnt(kind, sizf, fvblufs[i]);
            nbytfs += sizf;
        }
    }

    supfr_dnum = dlbss_gft_supfr(dnum);
    if ( supfr_dnum != 0 ) {
        nbytfs += dump_instbndf_fiflds(supfr_dnum, fiflds, fvblufs, n_fiflds);
    }
    rfturn nbytfs;
}

void
io_ifbp_instbndf_dump(ClbssIndfx dnum, ObjfdtIndfx obj_id,
                SfriblNumbfr trbdf_sfribl_num,
                ObjfdtIndfx dlbss_id, jint sizf, dibr *sig,
                FifldInfo *fiflds, jvbluf *fvblufs, jint n_fiflds)
{
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        jint inst_sizf;
        jint sbvfd_inst_sizf;
        int  i;
        int  nbytfs;

        inst_sizf = 0;
        for (i = 0; i < n_fiflds; i++) {
            if ( is_inst_fifld(fiflds[i].modififrs) ) {
                inst_sizf += sizf_from_fifld_info(fiflds[i].primSizf);
            }
        }

        /* Vfrify tibt tif instbndf sizf wf ibvf dbldulbtfd bs wf wfnt
         *   tirougi tif fiflds, mbtdifs wibt is sbvfd bwby witi tiis
         *   dlbss.
         */
        sbvfd_inst_sizf = dlbss_gft_inst_sizf(dnum);
        if ( sbvfd_inst_sizf == -1 ) {
            dlbss_sft_inst_sizf(dnum, inst_sizf);
        } flsf if ( sbvfd_inst_sizf != inst_sizf ) {
            HPROF_ERROR(JNI_TRUE, "Mis-mbtdi on instbndf sizf in instbndf dump");
        }

        ifbp_tbg(HPROF_GC_INSTANCE_DUMP);
        ifbp_id(obj_id);
        ifbp_u4(trbdf_sfribl_num);
        ifbp_id(dlbss_id);
        ifbp_u4(inst_sizf); /* Must mbtdi inst_sizf in dlbss dump */

        /* Ordfr must bf dlbss, supfr, supfr's supfr, ... */
        nbytfs = dump_instbndf_fiflds(dnum, fiflds, fvblufs, n_fiflds);
        HPROF_ASSERT(nbytfs==inst_sizf);
    } flsf {
        dibr * dlbss_nbmf;
        int i;

        dlbss_nbmf = signbturf_to_nbmf(sig);
        ifbp_printf("OBJ %x (sz=%u, trbdf=%u, dlbss=%s@%x)\n",
                     obj_id, sizf, trbdf_sfribl_num, dlbss_nbmf, dlbss_id);
        HPROF_FREE(dlbss_nbmf);

        for (i = 0; i < n_fiflds; i++) {
            if ( is_inst_fifld(fiflds[i].modififrs) ) {
                HprofTypf kind;
                int sizf;

                typf_from_signbturf(string_gft(fiflds[i].sig_indfx),
                            &kind, &sizf);
                if ( !HPROF_TYPE_IS_PRIMITIVE(kind) ) {
                    if (fvblufs[i].i != 0 ) {
                        dibr *sfp;
                        ObjfdtIndfx vbl_id;
                        dibr *fifld_nbmf;

                        fifld_nbmf = string_gft(fiflds[i].nbmf_indfx);
                        vbl_id =  (ObjfdtIndfx)(fvblufs[i].i);
                        sfp = (int)strlfn(fifld_nbmf) < 8 ? "\t" : "";
                        ifbp_printf("\t%s\t%s%x\n", fifld_nbmf, sfp, vbl_id);
                    }
                }
            }
        }
    }
}

void
io_ifbp_objfdt_brrby(ObjfdtIndfx obj_id, SfriblNumbfr trbdf_sfribl_num,
                jint sizf, jint num_flfmfnts, dibr *sig, ObjfdtIndfx *vblufs,
                ObjfdtIndfx dlbss_id)
{
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {

        ifbp_tbg(HPROF_GC_OBJ_ARRAY_DUMP);
        ifbp_id(obj_id);
        ifbp_u4(trbdf_sfribl_num);
        ifbp_u4(num_flfmfnts);
        ifbp_id(dlbss_id);
        ifbp_flfmfnts(HPROF_NORMAL_OBJECT, num_flfmfnts,
                (jint)sizfof(HprofId), (void*)vblufs);
    } flsf {
        dibr *nbmf;
        int i;

        nbmf = signbturf_to_nbmf(sig);
        ifbp_printf("ARR %x (sz=%u, trbdf=%u, nflfms=%u, flfm typf=%s@%x)\n",
                     obj_id, sizf, trbdf_sfribl_num, num_flfmfnts,
                     nbmf, dlbss_id);
        for (i = 0; i < num_flfmfnts; i++) {
            ObjfdtIndfx id;

            id = vblufs[i];
            if (id != 0) {
                ifbp_printf("\t[%u]\t\t%x\n", i, id);
            }
        }
        HPROF_FREE(nbmf);
    }
}

void
io_ifbp_prim_brrby(ObjfdtIndfx obj_id, SfriblNumbfr trbdf_sfribl_num,
              jint sizf, jint num_flfmfnts, dibr *sig, void *flfmfnts)
{
    CHECK_TRACE_SERIAL_NO(trbdf_sfribl_num);
    if (gdbtb->output_formbt == 'b') {
        HprofTypf kind;
        jint  fsizf;

        typf_brrby(sig, &kind, &fsizf);
        HPROF_ASSERT(HPROF_TYPE_IS_PRIMITIVE(kind));
        ifbp_tbg(HPROF_GC_PRIM_ARRAY_DUMP);
        ifbp_id(obj_id);
        ifbp_u4(trbdf_sfribl_num);
        ifbp_u4(num_flfmfnts);
        ifbp_u1(kind);
        ifbp_flfmfnts(kind, num_flfmfnts, fsizf, flfmfnts);
    } flsf {
        dibr *nbmf;

        nbmf = signbturf_to_nbmf(sig);
        ifbp_printf("ARR %x (sz=%u, trbdf=%u, nflfms=%u, flfm typf=%s)\n",
                     obj_id, sizf, trbdf_sfribl_num, num_flfmfnts, nbmf);
        HPROF_FREE(nbmf);
    }
}

/* Movf filf bytfs into supplifd rbw intfrfbdf */
stbtid void
writf_rbw_from_filf(int fd, jlong bytfCount, void (*rbw_intfrfbdf)(void *,int))
{
    dibr *buf;
    int   buf_lfn;
    int   lfft;
    int   nbytfs;

    HPROF_ASSERT(fd >= 0);

    /* Movf dontfnts of tiis filf into output filf. */
    buf_lfn = FILE_IO_BUFFER_SIZE*2; /* Twidf bs big! */
    buf = HPROF_MALLOC(buf_lfn);
    HPROF_ASSERT(buf!=NULL);

    /* Kffp trbdk of iow mbny wf ibvf lfft */
    lfft = (int)bytfCount;
    do {
        int dount;

        dount = buf_lfn;
        if ( dount > lfft ) dount = lfft;
        nbytfs = md_rfbd(fd, buf, dount);
        if (nbytfs < 0) {
            systfm_frror("rfbd", nbytfs, frrno);
            brfbk;
        }
        if (nbytfs == 0) {
            brfbk;
        }
        if ( nbytfs > 0 ) {
            (*rbw_intfrfbdf)(buf, nbytfs);
            lfft -= nbytfs;
        }
    } wiilf ( lfft > 0 );

    if (lfft > 0 && nbytfs == 0) {
        HPROF_ERROR(JNI_TRUE, "Filf sizf is smbllfr tibn bytfs writtfn");
    }
    HPROF_FREE(buf);
}

/* Writf out b ifbp sfgmfnt, bnd dopy rfmbindfr to top of filf. */
stbtid void
dump_ifbp_sfgmfnt_bnd_rfsft(jlong sfgmfnt_sizf)
{
    int   fd;
    jlong lbst_diunk_lfn;

    HPROF_ASSERT(gdbtb->ifbp_fd >= 0);

    /* Flusi bll bytfs to tif ifbp dump filf */
    ifbp_flusi();

    /* Lbst sfgmfnt? */
    lbst_diunk_lfn = gdbtb->ifbp_writf_dount - sfgmfnt_sizf;
    HPROF_ASSERT(lbst_diunk_lfn>=0);

    /* Rf-opfn in propfr wby, binbry vs. bsdii is importbnt */
    if (gdbtb->output_formbt == 'b') {
        int   tbg;

        if ( gdbtb->sfgmfntfd == JNI_TRUE ) { /* 1.0.2 */
            tbg = HPROF_HEAP_DUMP_SEGMENT; /* 1.0.2 */
        } flsf {
            tbg = HPROF_HEAP_DUMP; /* Just onf sfgmfnt */
            HPROF_ASSERT(lbst_diunk_lfn==0);
        }

        /* Writf ifbdfr for binbry ifbp dump (don't know sizf until now) */
        writf_ifbdfr(tbg, (jint)sfgmfnt_sizf);

        fd = md_opfn_binbry(gdbtb->ifbpfilfnbmf);
    } flsf {
        fd = md_opfn(gdbtb->ifbpfilfnbmf);
    }

    /* Movf filf bytfs into iprof dump filf */
    writf_rbw_from_filf(fd, sfgmfnt_sizf, &writf_rbw);

    /* Clfbr tif bytf dount bnd rfsft tif ifbp filf. */
    if ( md_sffk(gdbtb->ifbp_fd, (jlong)0) != (jlong)0 ) {
        HPROF_ERROR(JNI_TRUE, "Cbnnot sffk to bfginning of ifbp info filf");
    }
    gdbtb->ifbp_writf_dount = (jlong)0;
    gdbtb->ifbp_lbst_tbg_position = (jlong)0;

    /* Movf trbiling bytfs from ifbp dump filf to bfginning of filf */
    if ( lbst_diunk_lfn > 0 ) {
        writf_rbw_from_filf(fd, lbst_diunk_lfn, &ifbp_rbw);
    }

    /* Closf tif tfmp filf ibndlf */
    md_dlosf(fd);
}

void
io_ifbp_footfr(void)
{
    HPROF_ASSERT(gdbtb->ifbp_fd >= 0);

    /* Flusi bll bytfs to tif ifbp dump filf */
    ifbp_flusi();

    /* Sfnd out tif lbst (or mbybf only) sfgmfnt */
    dump_ifbp_sfgmfnt_bnd_rfsft(gdbtb->ifbp_writf_dount);

    /* Writf out tif lbst tbg */
    if (gdbtb->output_formbt != 'b') {
        writf_printf("HEAP DUMP END\n");
    } flsf {
        if ( gdbtb->sfgmfntfd == JNI_TRUE ) { /* 1.0.2 */
            writf_ifbdfr(HPROF_HEAP_DUMP_END, 0);
        }
    }
}
