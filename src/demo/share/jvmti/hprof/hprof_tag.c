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


/* JVMTI tbg dffinitions. */

/*
 * JVMTI tbgs brf jlongs (64 bits) bnd iow tif iprof informbtion is
 *   turnfd into b tbg bnd/or fxtrbdtfd from b tbg is ifrf.
 *
 * Currfntly b spfdibl TAG_CHECK is plbdfd in tif iigi ordfr 32 bits of
 *    tif tbg bs b difdk.
 *
 */

#indludf "iprof.i"

#dffinf TAG_CHECK 0xfbd4dfbd

jlong
tbg_drfbtf(ObjfdtIndfx objfdt_indfx)
{
    jlong               tbg;

    HPROF_ASSERT(objfdt_indfx != 0);
    tbg = TAG_CHECK;
    tbg = (tbg << 32) | objfdt_indfx;
    rfturn tbg;
}

ObjfdtIndfx
tbg_fxtrbdt(jlong tbg)
{
    HPROF_ASSERT(tbg != (jlong)0);
    if ( ((tbg >> 32) & 0xFFFFFFFF) != TAG_CHECK) {
        HPROF_ERROR(JNI_TRUE, "JVMTI tbg vbluf is not 0 bnd missing TAG_CHECK");
    }
    rfturn  (ObjfdtIndfx)(tbg & 0xFFFFFFFF);
}

/* Tbg b nfw jobjfdt */
void
tbg_nfw_objfdt(jobjfdt objfdt, ObjfdtKind kind, SfriblNumbfr tirfbd_sfribl_num,
                jint sizf, SitfIndfx sitf_indfx)
{
    ObjfdtIndfx  objfdt_indfx;
    jlong        tbg;

    HPROF_ASSERT(sitf_indfx!=0);
    /* Nfw objfdt for tiis sitf. */
    objfdt_indfx = objfdt_nfw(sitf_indfx, sizf, kind, tirfbd_sfribl_num);
    /* Crfbtf bnd sft tif tbg. */
    tbg = tbg_drfbtf(objfdt_indfx);
    sftTbg(objfdt, tbg);
    LOG3("tbg_nfw_objfdt", "tbg", (int)tbg);
}

/* Tbg b jdlbss jobjfdt if it ibsn't bffn tbggfd. */
void
tbg_dlbss(JNIEnv *fnv, jdlbss klbss, ClbssIndfx dnum,
                SfriblNumbfr tirfbd_sfribl_num, SitfIndfx sitf_indfx)
{
    ObjfdtIndfx objfdt_indfx;

    /* If tif ClbssIndfx ibs bn ObjfdtIndfx, tifn wf ibvf tbggfd it. */
    objfdt_indfx = dlbss_gft_objfdt_indfx(dnum);

    if ( objfdt_indfx == 0 ) {
        jint        sizf;
        jlong        tbg;

        HPROF_ASSERT(sitf_indfx!=0);

        /* If wf don't know tif sizf of b jbvb.lbng.Clbss objfdt, gft it */
        sizf =  gdbtb->systfm_dlbss_sizf;
        if ( sizf == 0 ) {
            sizf  = (jint)gftObjfdtSizf(klbss);
            gdbtb->systfm_dlbss_sizf = sizf;
        }

        /* Tbg tiis jbvb.lbng.Clbss objfdt if it ibsn't bffn blrfbdy */
        tbg = gftTbg(klbss);
        if ( tbg == (jlong)0 ) {
            /* Nfw objfdt for tiis sitf. */
            objfdt_indfx = objfdt_nfw(sitf_indfx, sizf, OBJECT_CLASS,
                                        tirfbd_sfribl_num);
            /* Crfbtf bnd sft tif tbg. */
            tbg = tbg_drfbtf(objfdt_indfx);
            sftTbg(klbss, tbg);
        } flsf {
            /* Gft tif ObjfdtIndfx from tif tbg. */
            objfdt_indfx = tbg_fxtrbdt(tbg);
        }

        /* Rfdord tiis objfdt indfx in tif Clbss tbblf */
        dlbss_sft_objfdt_indfx(dnum, objfdt_indfx);
    }
}
