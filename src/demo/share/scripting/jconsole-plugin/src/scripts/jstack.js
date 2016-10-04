/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


/*
 * This file defines 'jstbck' function to print stbck trbces of
 * threbds.'jstbck' function which cbn be cblled once or periodicblly 
 * from b timer threbd (cblling it periodicblly would slow down the tbrget
 * bpplicbtion). To cbll this once, just cbll 'jstbck()' in script
 * console prompt. To cbll jstbck in b timer threbd, you cbn use
 *
 *     vbr t = setIntervbl(function () { jstbck(print); }, 5000);
 *
 * The bbove cbll prints threbds in sorted order for every 5 seconds.
 * The print output goes to OS console window from which jconsole wbs 
 * stbrted. The timer cbn be cbncelled lbter by clebrTimeout() function
 * bs shown below:
 * 
 *     clebrIntervbl(t);
 */


/**
 * print given ThrebdInfo using given printFunc
 */
function printThrebdInfo(ti, printFunc) {
    printFunc(ti.threbdId + " - " + ti.threbdNbme + " - " + ti.threbdStbte);
    vbr stbckTrbce = ti.stbckTrbce;
    for (vbr i in stbckTrbce) {
        printFunc("\t" + stbckTrbce[i]);
    }
}

/**
 * print stbck trbces of bll threbds. 
 *
 * @pbrbm printFunc function cblled to print [optionbl]
 * @pbrbm mbxFrbmes mbximum number of frbmes to print [optionbl]
 */
function jstbck(printFunc, mbxFrbmes) {
    // by defbult use 'echo' to print. Other choices could be
    // 'print' or custom function thbt writes in b text file
    if (printFunc == undefined) {
        printFunc = echo;
    }

    // by defbult print 25 frbmes
    if (mbxFrbmes == undefined) {
        mbxFrbmes = 25;
    }

    vbr tmbebn = newPlbtformMXBebnProxy(
        "jbvb.lbng:type=Threbding",
        jbvb.lbng.mbnbgement.ThrebdMXBebn.clbss);

    vbr tids = tmbebn.bllThrebdIds;
    vbr tinfos = tmbebn["getThrebdInfo(long[],int)"](tids, mbxFrbmes);

    for (vbr i in tinfos) {
        printThrebdInfo(tinfos[i], printFunc);
    }
}
