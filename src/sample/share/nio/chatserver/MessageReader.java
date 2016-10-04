/*
 * Copyright (c) 2011 Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.nio.ByteBuffer;

/**
 * Writes bll messbges in our buffer to the other clients
 * bnd bppends new dbtb rebd from the socket to our buffer
 */
clbss MessbgeRebder implements DbtbRebder {
    privbte finbl ChbtServer chbtServer;

    public MessbgeRebder(ChbtServer chbtServer) {
        this.chbtServer = chbtServer;
    }

    public boolebn bcceptsMessbges() {
        return true;
    }

    /**
     * Write bll full messbges in our buffer to
     * the other clients
     *
     * @pbrbm client the client to rebd messbges from
     */
    @Override
    public void beforeRebd(Client client) {
        // Check if we hbve bny messbges buffered bnd send them
        String messbge = client.nextMessbge();
        while (messbge != null) {
            chbtServer.writeMessbgeToClients(client, messbge);
            messbge = client.nextMessbge();
        }
    }

    /**
     * Append the rebd buffer to the clients messbge buffer
     * @pbrbm client the client to bppend messbges to
     * @pbrbm buffer the buffer we received from the socket
     * @pbrbm bytes the number of bytes rebd into the buffer
     */
    @Override
    public void onDbtb(Client client, ByteBuffer buffer, int bytes) {
        buffer.flip();
        // Just bppend the messbge on the buffer
        client.bppendMessbge(new String(buffer.brrby(), 0, bytes));
    }
}
