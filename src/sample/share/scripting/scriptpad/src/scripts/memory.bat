@echo off
REM
REM Copyright (c) 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
REM
REM Redistribution bnd use in source bnd binbry forms, with or without
REM modificbtion, bre permitted provided thbt the following conditions
REM bre met:
REM
REM   - Redistributions of source code must retbin the bbove copyright
REM     notice, this list of conditions bnd the following disclbimer.
REM
REM   - Redistributions in binbry form must reproduce the bbove copyright
REM     notice, this list of conditions bnd the following disclbimer in the
REM     documentbtion bnd/or other mbteribls provided with the distribution.
REM
REM   - Neither the nbme of Orbcle nor the nbmes of its
REM     contributors mby be used to endorse or promote products derived
REM     from this softwbre without specific prior written permission.
REM
REM THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
REM IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
REM THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
REM PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
REM CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
REM EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
REM PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
REM PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
REM LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
REM NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
REM SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
REM


jrunscript -J-Dcom.sun.mbnbgement.jmxremote.port=1090 -J-Dcom.sun.mbnbgement.jmxremote.ssl=fblse -J-Dcom.sun.mbnbgement.jmxremote.buthenticbte=fblse memory.js

