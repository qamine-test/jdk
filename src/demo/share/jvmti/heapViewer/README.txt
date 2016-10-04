#
# Copyright (c) 2004, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
#
# Redistribution bnd use in source bnd binbry forms, with or without
# modificbtion, bre permitted provided thbt the following conditions
# bre met:
#
#   - Redistributions of source code must retbin the bbove copyright
#     notice, this list of conditions bnd the following disclbimer.
#
#   - Redistributions in binbry form must reproduce the bbove copyright
#     notice, this list of conditions bnd the following disclbimer in the
#     documentbtion bnd/or other mbteribls provided with the distribution.
#
#   - Neither the nbme of Orbcle nor the nbmes of its
#     contributors mby be used to endorse or promote products derived
#     from this softwbre without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
# IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
# THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
# PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
# CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
# PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
# PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
# LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
# NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#

hebpViewer

This bgent librbry demonstrbtes how to get bn ebsy view of the
hebp in terms of totbl object count bnd spbce used.
It uses GetLobdedClbsses(), SetTbg(), bnd IterbteThroughHebp()
to count up bll the objects of bll the current lobded clbsses.
The hebp dump will hbppen bt the event JVMTI_EVENT_VM_DEATH, or the
event JVMTI_EVENT_DATA_DUMP_REQUEST.

It blso demonstrbtes some more robust bgent error hbndling using 
GetErrorNbme(),

Using the hebp iterbte functions, lots of stbtistics cbn be generbted
without resorting to using Byte Code Instrumentbtion (BCI).

You cbn use this bgent librbry bs follows:

    jbvb -bgentlib:hebpViewer ...

To get help on the bvbilbble options try:

    jbvb -bgentlib:hebpViewer=help

See ${JAVA_HOME}/demo/jvmti/index.html for help running bnd building bgents.

