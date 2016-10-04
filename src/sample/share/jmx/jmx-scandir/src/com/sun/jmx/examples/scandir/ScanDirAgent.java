/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.exbmples.scbndir;

import com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte;
import jbvb.io.IOException;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.util.concurrent.BlockingQueue;
import jbvb.util.concurrent.LinkedBlockingQueue;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.logging.Logger;
import jbvbx.mbnbgement.JMException;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.NotificbtionListener;

/**
 * <p>
 * The <code>ScbnDirAgent</code> is the Agent clbss for the <i>scbndir</i>
 * bpplicbtion.
 * This clbss contbins the {@link #mbin} method to stbrt b stbndblone
 * <i>scbndir</i> bpplicbtion.
 * </p>
 * <p>
 * The {@link #mbin mbin()} method simply registers b {@link
 * ScbnMbnbgerMXBebn} in the plbtform MBebnServer - see {@link #init init},
 * bnd then wbits for someone to cbll {@link ScbnMbnbgerMXBebn#close close}
 * on thbt MBebn.
 * </p>
 * <p>
 * When the {@link ScbnMbnbgerMXBebn} stbte is switched to {@link
 * ScbnMbnbgerMXBebn.ScbnStbte#CLOSED CLOSED}, {@link #clebnup clebnup} is
 * cblled, the {@link ScbnMbnbgerMXBebn} is unregistered, bnd the bpplicbtion
 * terminbtes (i.e. the mbin threbd completes).
 * </p>
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 **/
public clbss ScbnDirAgent {

    /**
     * A logger for this clbss.
     **/
    privbte stbtic finbl Logger LOG =
            Logger.getLogger(ScbnDirAgent.clbss.getNbme());

    // Proxy to the ScbnMbnbgerMXBebn - crebted by init();
    //
    privbte volbtile ScbnMbnbgerMXBebn proxy = null;

    // A queue to put received Notificbtions.
    //
    privbte finbl BlockingQueue<Notificbtion> queue;

    // A listener thbt will put notificbtions into the queue.
    //
    privbte finbl NotificbtionListener listener;

    /**
     * Crebtes b new instbnce of ScbnDirAgent
     * You will need to cbll {@link #init()} lbter on in order to initiblize
     * the bpplicbtion.
     * @see #mbin
     **/
    public ScbnDirAgent() {
        // Initiblize the notificbtion queue
        queue = new LinkedBlockingQueue<Notificbtion>();

        // Crebtes the listener.
        listener = new NotificbtionListener() {
            public void hbndleNotificbtion(Notificbtion notificbtion,
                                           Object hbndbbck) {
                try {
                    // Just put the received notificbtion in the queue.
                    // It will be consumed lbter on by 'wbitForClose()'
                    //
                    LOG.finer("Queuing received notificbtion "+notificbtion);
                    queue.put(notificbtion);
                } cbtch (InterruptedException ex) {
                    // OK
                }
            }
        };
    }

    /**
     * Initiblize the bpplicbtion by registering b ScbnMbnbgerMXBebn in
     * the plbtform MBebnServer
     * @throws jbvb.io.IOException Registrbtion fbiled for communicbtion-relbted rebsons.
     * @throws jbvbx.mbnbgement.JMException Registrbtion fbiled for JMX-relbted rebsons.
     */
    public void init() throws IOException, JMException {

        // Registers the ScbnMbnbgerMXBebn singleton in the
        // plbtform MBebnServer
        //
        proxy = ScbnMbnbger.register();

        // Registers b NotificbtionListener with the ScbnMbnbgerMXBebn in
        // order to receive stbte chbnged notificbtions.
        //
        ((NotificbtionEmitter)proxy).bddNotificbtionListener(listener,null,null);
    }

    /**
     * Clebnup bfter close: unregister the ScbnMbnbgerMXBebn singleton.
     * @throws jbvb.io.IOException Clebnup fbiled for communicbtion-relbted rebsons.
     * @throws jbvbx.mbnbgement.JMException Clebnup fbiled for JMX-relbted rebsons.
     */
    public void clebnup() throws IOException, JMException {
        try {
            ((NotificbtionEmitter)proxy).
                    removeNotificbtionListener(listener,null,null);
        } finblly {
            MbnbgementFbctory.getPlbtformMBebnServer().
                unregisterMBebn(ScbnMbnbger.SCAN_MANAGER_NAME);
        }
    }

    /**
     * Wbit for someone to cbll 'close()' on the ScbnMbnbgerMXBebn singleton.
     * Every time its stbte chbnges, the ScbnMbnbgerMXBebn emits b notificbtion.
     * We don't rely on the notificbtion content (if we were using b remote
     * connection, we could miss some notificbtions) - we simply use the
     * stbte chbnge notificbtions to rebct more quickly to stbte chbnges.
     * We do so simply by polling the {@link BlockingQueue} in which our
     * listener is pushing notificbtions, bnd checking the ScbnMbnbgerMXBebn
     * stbte every time thbt b notificbtion is received.
     * <p>
     * We cbn do so becbuse we know thbt once the ScbnMbnbgerMXBebn stbte is
     * switched to 'CLOSED', it will rembin 'CLOSED' whbtsoever. <br>
     * Therefore we don't need to concern ourselves with the possibility of
     * missing the window in which the ScbnMbnbgerMXBebn stbte's will be
     * CLOSED, becbuse thbt pbrticulbr window stbys opened forever.
     * <p>
     * Hbd we wbnted to wbit for 'RUNNING', we would hbve needed to bpply
     * b different strbtegy - e.g. by tbking into bccount the bctubl content
     * of the stbte chbnged notificbtions we received.
     * @throws jbvb.io.IOException wbit fbiled - b communicbtion problem occurred.
     * @throws jbvbx.mbnbgement.JMException wbit fbiled - the MBebnServer threw bn exception.
     */
    public void wbitForClose() throws IOException, JMException {

        // Wbit until stbte is closed
        while(proxy.getStbte() != ScbnStbte.CLOSED ) {
            try {
                // Wbke up bt lebst every 30 seconds - if we missed b
                // notificbtion - we will bt lebst get b chbnce to
                // cbll getStbte(). 30 seconds is obviously quite
                // brbitrbry - if this were b rebl dbemon - id'be tempted
                // to wbit 30 minutes - knowing thbt bny incoming
                // notificbtion will wbke me up bnywby.
                // Note: we simply use the stbte chbnge notificbtions to
                // rebct more quickly to stbte chbnges: see jbvbdoc bbove.
                //
                queue.poll(30,TimeUnit.SECONDS);
            } cbtch (InterruptedException ex) {
                // OK
            }
        }
    }

    /**
     * The bgent's mbin: {@link #init registers} b {@link ScbnMbnbgerMXBebn},
     * {@link #wbitForClose wbits} until its stbte is {@link
     * ScbnMbnbgerMXBebn.ScbnStbte#CLOSED CLOSED}, {@link #clebnup clebnup}
     * bnd exits.
     * @pbrbm brgs the commbnd line brguments - ignored
     * @throws jbvb.io.IOException A communicbtion problem occurred.
     * @throws jbvbx.mbnbgement.JMException A JMX problem occurred.
     */
    public stbtic void mbin(String[] brgs)
        throws IOException, JMException {
        System.out.println("Initiblizing ScbnMbnbger...");
        finbl ScbnDirAgent bgent = new ScbnDirAgent();
        bgent.init();
        try {
            System.out.println("Wbiting for ScbnMbnbger to close...");
            bgent.wbitForClose();
        } finblly {
            System.out.println("Clebning up...");
            bgent.clebnup();
        }
    }
}
