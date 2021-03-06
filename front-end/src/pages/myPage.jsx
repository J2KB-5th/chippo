import React from 'react';
import { useNavigate } from 'react-router-dom';

import { connect } from "react-redux";

import { Center, VStack, Button, Box, Flex, Spacer } from "@chakra-ui/react";
import { MdPerson } from "react-icons/md";

function MyPage({ logined }){
    const navigator = useNavigate();

    const goMyWirtePage = () => navigator("myWritePage");
    const goMyLikePage = () => navigator("myLikePage");
    const goMyWithdrawlPage = () => navigator("myWithdrawlPage")
    
    if (logined === false) navigator("/login");

    return (
        <Center>
            <VStack spacing={16} w = "70%">
                <Flex w = "100%">
                    <MdPerson size = "2.2em"/>
                    <Box pl = "20px" pr = "100px" fontSize= "24px">
                        {/* 로그인 유저 데이터 넣기 */}
                        황준승
                    </Box>
                
                    <Button variant="primary"> 닉네임 바꾸기 </Button>
                </Flex>

                <Flex w = "100%">
                    
                        <Center w = "300px" h = "150px"
                            bgGradient='linear(to-r, #E6F0FF, #5078E7)' color = "black"
                            onClick = {goMyWirtePage}
                            cursor = "pointer"
                        >
                            내가 작성한 게시물
                        </Center>

                    <Spacer />
                    
                    <Center w = "300px" h = "150px"
                        bgGradient='linear(to-r, #E6F0FF, #5078E7)' color = "black"
                        onClick = {goMyLikePage}
                        cursor = "pointer"
                    >
                        내가 좋아요 한 게시물
                    </Center>
                </Flex>

                <Button variant="danger" onClick={goMyWithdrawlPage}> 
                    탈퇴하기 
                </Button>
            </VStack>

            
        
        </Center>
    )
}

const mapStateToProps = ({ logined }) => {
    return {
        logined : logined.login
    }
}

export default connect(mapStateToProps)(MyPage);